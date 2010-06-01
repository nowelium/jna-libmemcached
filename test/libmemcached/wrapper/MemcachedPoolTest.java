package libmemcached.wrapper;

import libmemcached.exception.LibMemcachedException;
import libmemcached.exception.MaximumPoolException;

import org.junit.Assert;
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

}
