package libmemcached.wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import libmemcached.exception.LibMemcachedException;
import libmemcached.exception.MaximumPoolException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class MemcachedPoolTest {
    
    @Test
    public void pool_serverlist_nonblock() throws LibMemcachedException {
        MemcachedClient client = new MemcachedClient();
        client.getServerList().parse("localhost:11211,localhost:11212").push();
        
        MemcachedPool pool = client.createPool(1, 5);
        int i = 0;
        try {
            for(; i < 10; ++i){
                pool.pop(false);
            }
        } catch(MaximumPoolException e){
        }
        Assert.assertTrue(i < 10);
    }
    
    @Test
    public void pool_serverlist_maximumPool_ex() throws LibMemcachedException {
        MemcachedClient client = new MemcachedClient();
        client.getServerList().parse("localhost:11211,localhost:11212").push();
        
        MemcachedPool pool = client.createPool(1, 1);
        try {
            pool.pop(false);
        } catch(MaximumPoolException e){
            Assert.fail();
        }
        
        try {
            pool.pop(false);
            Assert.fail();
        } catch(MaximumPoolException e){
            System.out.println(e);
        }
    }
    
    @Test
    public void pool_serverlist_block() throws LibMemcachedException {
        MemcachedClient client = new MemcachedClient();
        client.getServerList().parse("localhost:11211,localhost:11212").push();
        
        MemcachedPool pool = client.createPool(1, 10);
        int i = 0;
        try {
            for(; i < 10; ++i){
                pool.pop(true);
            }
        } catch(MaximumPoolException e){
        }
        Assert.assertEquals(i, 10);
    }
    
    @Test
    public void pool_serverlist_block_small_maxpool() throws LibMemcachedException {
        MemcachedClient client = new MemcachedClient();
        client.getServerList().parse("localhost:11211,localhost:11212").push();
        
        MemcachedPool pool = client.createPool(1, 5);
        int i = 0;
        try {
            for(; i < 10; ++i){
                MemcachedClient c = pool.pop(true);
                c.getStorage().set("key" + i, "value", 10, 0);
                pool.push(c);
            }
        } catch(MaximumPoolException e){
        }
        Assert.assertEquals(i, 10);
    }
    
    @Test
    public void pool_pop_push() throws LibMemcachedException, MaximumPoolException {
        MemcachedClient client = new MemcachedClient();
        client.getServerList().parse("localhost:11211,localhost:11212").push();
        
        MemcachedPool pool = client.createPool(1, 5);
        List<MemcachedClient> r = new ArrayList<MemcachedClient>();
        r.add(pool.pop(false));
        r.add(pool.pop(false));
        r.add(pool.pop(false));
        r.add(pool.pop(false));
        r.add(pool.pop(false));
        
        pool.push(r.get(0));
        try {
            pool.pop(false);
        } catch(MaximumPoolException e){
            Assert.fail();
        }
        
        try {
            pool.pop(false);
            Assert.fail();
        } catch(MaximumPoolException e){
            System.out.println(e);
        }
    }
    
    @Test
    public void pool_with_mt() throws LibMemcachedException, MaximumPoolException {
        MemcachedClient client = new MemcachedClient();
        client.getServerList().parse("localhost:11211").push();
        
        final MemcachedPool pool = client.createPool(1, 5);
        List<Thread> threads = new ArrayList<Thread>();
        threads.add(new Thread(){
            public void run() {
                try {
                    MemcachedClient c = pool.pop(false);
                    c.getStorage().set("test-1", "value-1", 10, 0);
                    pool.push(c);
                } catch(MaximumPoolException e){
                    e.printStackTrace();
                } catch(LibMemcachedException e){
                    e.printStackTrace();
                }
            }
        });
        threads.add(new Thread(){
            public void run() {
                try {
                    MemcachedClient c = pool.pop(false);
                    c.getStorage().set("test-2", "value-2", 10, 0);
                    pool.push(c);
                } catch(MaximumPoolException e){
                    e.printStackTrace();
                } catch(LibMemcachedException e){
                    e.printStackTrace();
                }
            }
        });
        threads.add(new Thread(){
            public void run() {
                try {
                    MemcachedClient c = pool.pop(false);
                    c.getStorage().set("test-3", "value-3", 10, 0);
                    pool.push(c);
                } catch(MaximumPoolException e){
                    e.printStackTrace();
                } catch(LibMemcachedException e){
                    e.printStackTrace();
                }
            }
        });
        for(Thread th: threads){
            th.start();
        }
        try {
            for(Thread th: threads){
                th.join();
            }
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        Assert.assertEquals(client.getStorage().get("test-1"), "value-1");
        Assert.assertEquals(client.getStorage().get("test-2"), "value-2");
        Assert.assertEquals(client.getStorage().get("test-3"), "value-3");
    }
    
    @Test
    public void pool_with_mt_blocking() throws LibMemcachedException, MaximumPoolException {
        MemcachedClient client = new MemcachedClient();
        client.getServerList().parse("localhost:11211").push();
        
        final MemcachedPool pool = client.createPool(1, 1);
        final BlockingQueue<MemcachedClient> release = new LinkedBlockingQueue<MemcachedClient>();
        final CountDownLatch latch = new CountDownLatch(4);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Runnable(){
            public void run() {
                latch.countDown();
                
                try {
                    latch.await();
                    
                    System.out.println("wait:1");
                    MemcachedClient c = pool.pop(true);
                    System.out.println("pop:1");
                    c.getStorage().set("test-1", "value-1", 10, 0);
                    release.offer(c);
                } catch(InterruptedException e){
                    e.printStackTrace();
                } catch(MaximumPoolException e){
                    e.printStackTrace();
                } catch(LibMemcachedException e){
                    e.printStackTrace();
                }
            }
        });
        executor.execute(new Runnable(){
            public void run() {
                latch.countDown();
                
                try {
                    latch.await();
                    
                    System.out.println("wait:2");
                    MemcachedClient c = pool.pop(true);
                    System.out.println("pop:2");
                    c.getStorage().set("test-2", "value-2", 10, 0);
                    release.offer(c);
                } catch(InterruptedException e){
                    e.printStackTrace();
                } catch(MaximumPoolException e){
                    e.printStackTrace();
                } catch(LibMemcachedException e){
                    e.printStackTrace();
                }
            }
        });
        executor.execute(new Runnable(){
            public void run() {
                latch.countDown();
                
                try {
                    latch.await();
                    
                    System.out.println("wait:3");
                    MemcachedClient c = pool.pop(true);
                    System.out.println("pop:3");
                    c.getStorage().set("test-3", "value-3", 10, 0);
                    release.offer(c);
                } catch(InterruptedException e){
                    e.printStackTrace();
                } catch(MaximumPoolException e){
                    e.printStackTrace();
                } catch(LibMemcachedException e){
                    e.printStackTrace();
                }
            }
        });
        executor.execute(new Runnable(){
            public void run() {
                latch.countDown();
                
                try {
                    latch.await();
                    
                    while(true){
                        MemcachedClient c = release.take();
                        pool.push(c);
                    }
                } catch(InterruptedException e){
                }
            }
        });
        try {
            latch.countDown();
            
            executor.awaitTermination(1, TimeUnit.SECONDS);
            executor.shutdownNow();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        Assert.assertEquals(client.getStorage().get("test-1"), "value-1");
        Assert.assertEquals(client.getStorage().get("test-2"), "value-2");
        Assert.assertEquals(client.getStorage().get("test-3"), "value-3");
        Assert.assertTrue(release.isEmpty());
    }
    
    @Test
    @Ignore
    public void pool_with_mt_blocking_() throws LibMemcachedException, MaximumPoolException {
        final MemcachedClient client = new MemcachedClient();
        client.getServerList().parse("localhost:11211").push();
        final MemcachedPool pool = client.createPool(2, 5);
        
        final BlockingQueue<MemcachedClient> release = new LinkedBlockingQueue<MemcachedClient>();
        final SynchronousQueue<Object> sync = new SynchronousQueue<Object>();
        final int SIZE = 100;
        final CountDownLatch latch = new CountDownLatch(SIZE + 1);
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0; i < SIZE; ++i){
            executor.execute(new T(i, latch, pool, release));
        }
        executor.execute(new Runnable(){
            public void run() {
                latch.countDown();
                
                try {
                    latch.await();
                    
                    int i = 0;
                    while(true){
                        if(SIZE < i++){
                            break;
                        }
                        MemcachedClient c = release.take();
                        pool.push(c);
                    }
                    sync.offer(new Object());
                } catch(InterruptedException e){
                }
            }
        });
        try {
            latch.countDown();
            
            sync.take();
            
            for(int i = 0; i < SIZE; ++i){
                Assert.assertEquals(client.getStorage().get("test-" + i), "value-" + i);
            }
            Assert.assertTrue(release.isEmpty());
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    @Test
    @Ignore
    public void pool_with_executor() throws LibMemcachedException, MaximumPoolException {
        MemcachedClient client = new MemcachedClient();
        client.getServerList().parse("localhost:11211").push();
        
        final MemcachedPool pool = client.createPool(1, 5);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Runnable() {
            public void run(){
                try {
                    MemcachedClient c = pool.pop(false);
                    c.getStorage().set("test-1", "value-1", 10, 0);
                    pool.push(c);
                } catch(MaximumPoolException e){
                    e.printStackTrace();
                } catch(LibMemcachedException e){
                    e.printStackTrace();
                }
            }
        });
        executor.execute(new Runnable() {
            public void run(){
                try {
                    MemcachedClient c = pool.pop(false);
                    c.getStorage().set("test-2", "value-2", 10, 0);
                    pool.push(c);
                } catch(MaximumPoolException e){
                    e.printStackTrace();
                } catch(LibMemcachedException e){
                    e.printStackTrace();
                }
            }
        });
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
            Assert.assertEquals(client.getStorage().get("test-1"), "value-1");
            Assert.assertEquals(client.getStorage().get("test-2"), "value-2");
        } catch(InterruptedException e){
        }
    }
    
    @Test
    @Ignore
    public void pool_shared() throws LibMemcachedException, MaximumPoolException {
        MemcachedClient client = new MemcachedClient();
        client.getServerList().parse("localhost:11211").push();
        
        final Shared s = new Shared(client);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Runnable() {
            public void run(){
                MemcachedClient c = s.pop();
                c.getStorage().set("test-1", "value-1", 10, 0);
                s.push(c);
            }
        });
        executor.execute(new Runnable() {
            public void run(){
                MemcachedClient c = s.pop();
                c.getStorage().set("test-2", "value-2", 10, 0);
                s.push(c);
            }
        });
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
            Assert.assertEquals(client.getStorage().get("test-1"), "value-1");
            Assert.assertEquals(client.getStorage().get("test-2"), "value-2");
        } catch(InterruptedException e){
        }
    }
    
    @Test
    @Ignore
    public void pool_access_() throws LibMemcachedException {
        MemcachedClient client = new MemcachedClient();
        client.getServerList().parse("localhost:11211").push();
        
        final MemcachedPool pool = client.createPool(1, 100);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Runnable() {
            public void run(){
                try {
                    for(int i = 0; i < 50; ++i){
                        MemcachedClient c = pool.pop(false);
                        c.getStorage().set("test-" + i, "value-" + i, 10, 0);
                        pool.push(c);
                    }
                } catch(MaximumPoolException e){
                    e.printStackTrace();
                } catch(LibMemcachedException e){
                    e.printStackTrace();
                }
            }
        });
        executor.execute(new Runnable() {
            public void run(){
                try {
                    for(int i = 50; i < 100; ++i){
                        MemcachedClient c = pool.pop(false);
                        c.getStorage().set("test-" + i, "value-" + i, 10, 0);
                        pool.push(c);
                    }
                } catch(MaximumPoolException e){
                    e.printStackTrace();
                } catch(LibMemcachedException e){
                    e.printStackTrace();
                }
            }
        });
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
            for(int i = 0; i < 100; ++i){
                Assert.assertEquals(client.getStorage().get("test-" + i), "value-" + i);
            }
        } catch(InterruptedException e){
        }
    }
    
    @Test
    @Ignore
    public void pool_(){
        MemcachedClient client = new MemcachedClient();
        client.getServerList().parse("localhost:11211").push();
        
        final MemcachedPool pool = client.createPool(1, 50);
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0; i < 100; ++i){
            executor.execute(new Runnable() {
                public void run(){
                    try {
                        MemcachedClient c = pool.pop(true);
                        pool.push(c);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch(InterruptedException e){
        }
    }
    
    protected static class T implements Runnable {
        private final int num;
        private final CountDownLatch latch;
        private final MemcachedPool pool;
        private final BlockingQueue<MemcachedClient> releaseQueue;
        public T(int num, CountDownLatch latch, MemcachedPool pool, BlockingQueue<MemcachedClient> releaseQueue){
            this.num = num;
            this.latch = latch;
            this.pool = pool;
            this.releaseQueue = releaseQueue;
        }
        public void run() {
            latch.countDown();
            
            try {
                latch.await();
                
                System.out.println("wait:" + num);
                MemcachedClient c = pool.pop(true);
                System.out.println("pop:" + num);
                c.getStorage().set("test-" + num, "value-" + num, 10, 0);
                releaseQueue.offer(c);
            } catch(InterruptedException e){
                e.printStackTrace();
            } catch(MaximumPoolException e){
                e.printStackTrace();
            } catch(LibMemcachedException e){
                e.printStackTrace();
            }
        }
    }
    
    protected static class Shared {
        private final MemcachedPool pool;
        private Shared(MemcachedClient client){
            this.pool = client.createPool(1, 5);
        }
        public MemcachedClient pop(){
            try {
                return pool.pop(false);
            } catch(MaximumPoolException e){
                e.printStackTrace();
            } catch(LibMemcachedException e){
                e.printStackTrace();
            }
            return null;
        }
        public void push(MemcachedClient c){
            pool.push(c);
        }
    }

}
