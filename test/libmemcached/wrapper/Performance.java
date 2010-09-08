package libmemcached.wrapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import libmemcached.exception.LibMemcachedException;
import libmemcached.exception.MaximumPoolException;
import libmemcached.wrapper.type.BehaviorType;

public class Performance {
    
    private static final String[] keys = new String[1000];
    static {
        for(int i = 0; i < 1000; ++i){
            keys[i] = Integer.toString(i);
        }
    }
    private static final BehaviorType[] behaviors = {
        BehaviorType.BUFFER_REQUESTS,
        BehaviorType.TCP_NODELAY,
        BehaviorType.TCP_KEEPALIVE,
    };
    
    public static void bench(String message, long[] times){
        List<Long> r = new ArrayList<Long>();
        for(long time: times){
            r.add(Long.valueOf(time));
        }
        Long max = Collections.max(r);
        Long min = Collections.min(r);
        
        BigDecimal decimal = new BigDecimal(0);
        for(long time: times){
            decimal = decimal.add(new BigDecimal(time));
        }
        double avg = decimal.divide(new BigDecimal(times.length), RoundingMode.HALF_UP).doubleValue();
        System.out.println("------" + message + "------");
        System.out.println("max:" + max + "ms");
        System.out.println("min:" + min + "ms");
        System.out.println("avg:" + avg + "ms");
    }
    
    public static void main(String...args) throws LibMemcachedException, MaximumPoolException {
        get_multi_spinup();
        
        final int count = 100;
        {
            long[] times = new long[count];
            for(int i = 0; i < count; ++i){
                times[i] = get_multi_with_fetcher();
            }
            bench("get_multi_with_fetcher", times);
        }
        {
            long[] times = new long[count];
            for(int i = 0; i < count; ++i){
                times[i] = get_multi_without_fetcher();
            }
            bench("get_multi_without_fetcher", times);
        }
        {
            MemcachedClient client = new MemcachedClient();
            client.getServerList().parse("localhost:11211").push();
            
            long[] times = new long[count];
            for(int i = 0; i < count; ++i){
                times[i] = get_multi_without_fetcher_connection(client);
            }
            bench("get_multi_without_fetcher_connection", times);
        }
        {
            long[] times = new long[count];
            for(int i = 0; i < count; ++i){
                times[i] = get_multi_without_fetcher_behavior(behaviors);
            }
            bench("get_multi_without_fetcher_behavior", times);
        }
        {
            MemcachedClient client = new MemcachedClient();
            try {
                client.getServerList().parse("localhost:11211").push();
                for(BehaviorType type: behaviors){
                    client.getBehavior().set(type, 1);
                }
    
                long[] times = new long[count];
                for(int i = 0; i < count; ++i){
                    times[i] = get_multi_without_fetcher_behavior_connection(client);
                }
                bench("get_multi_without_fetcher_behavior_connection", times);
            } finally {
                client.free();
            }
        }
        {
            MemcachedClient client = new MemcachedClient();
            try {
                client.getServerList().parse("localhost:11211").push();
                
                MemcachedPool pool = client.createPool(count, count);
                for(BehaviorType type: behaviors){
                    pool.setBehavior(type, 1);
                }
                
                long[] times = new long[count];
                for(int i = 0; i < count; ++i){
                    times[i] = get_multi_without_fetcher_behavior_pool(pool);
                }
                bench("get_multi_without_fetcher_behavior_pool", times);
            } finally {
                client.free();
            }
        }
        {
            MemcachedClient client = new MemcachedClient();
            try {
                client.getServerList().parse("localhost:11211").push();
                
                MemcachedPool pool = client.createPool(count, count);
                for(BehaviorType type: behaviors){
                    pool.setBehavior(type, 1);
                }
                
                long[] times = new long[count];
                for(int i = 0; i < count; ++i){
                    times[i] = get_multi_without_fetcher_behavior_pool_nonblock(pool);
                }
                bench("get_multi_without_fetcher_behavior_pool_nonblock", times);
            } finally {
                client.free();
            }
        }
    }
    
    public static void get_multi_spinup() throws LibMemcachedException {
        MemcachedClient client = new MemcachedClient();
        client.addServer("localhost", 11211);
        
        MemcachedStorage storage = client.getStorage();
        storage.getMulti(keys);
        storage.getMulti(new Fetcher(){
            public void fetch(SimpleResult result) {
                // hoge
            }
        }, keys);
    }
    
    public static long get_multi_with_fetcher() throws LibMemcachedException {
        long elapsed = System.currentTimeMillis();
        
        MemcachedClient client = new MemcachedClient();
        try {
            client.getServerList().parse("localhost:11211").push();
            
            MemcachedStorage storage = client.getStorage();
            storage.getMulti(new Fetcher(){
                public void fetch(SimpleResult result) {
                    // hoge
                }
            }, keys);
            
            return System.currentTimeMillis() - elapsed;
        } finally {
            client.free();
        }
    }
    
    public static long get_multi_without_fetcher() throws LibMemcachedException {
        long elapsed = System.currentTimeMillis();
        
        MemcachedClient client = new MemcachedClient();
        try {
            client.getServerList().parse("localhost:11211").push();
            
            MemcachedStorage storage = client.getStorage();
            storage.getMulti(keys);
            while(storage.fetch() != null){
                // hoge
            }
            
            return System.currentTimeMillis() - elapsed;
        } finally {
            client.free();
        }
    }
    
    public static long get_multi_without_fetcher_connection(MemcachedClient client) throws LibMemcachedException {
        long elapsed = System.currentTimeMillis();
        
        MemcachedStorage storage = client.getStorage();
        storage.getMulti(keys);
        while(storage.fetch() != null){
            // hoge
        }
        
        return System.currentTimeMillis() - elapsed;
    }
    
    public static long get_multi_without_fetcher_behavior(BehaviorType[] types) throws LibMemcachedException {
        long elapsed = System.currentTimeMillis();
        
        MemcachedClient client = new MemcachedClient();
        try {
            client.getServerList().parse("localhost:11211").push();
            for(BehaviorType type: types){
                client.getBehavior().set(type, 1);
            }
            
            MemcachedStorage storage = client.getStorage();
            storage.getMulti(keys);
            while(storage.fetch() != null){
                // hoge
            }
            
            return System.currentTimeMillis() - elapsed;
        } finally {
            client.free();
        }
    }
    
    public static long get_multi_without_fetcher_behavior_connection(MemcachedClient client) throws LibMemcachedException {
        long elapsed = System.currentTimeMillis();
        
        MemcachedStorage storage = client.getStorage();
        storage.getMulti(keys);
        while(storage.fetch() != null){
            // hoge
        }
        
        return System.currentTimeMillis() - elapsed;
    }
    
    public static long get_multi_without_fetcher_behavior_pool(MemcachedPool pool) throws LibMemcachedException, MaximumPoolException {
        long elapsed = System.currentTimeMillis();
        
        MemcachedClient client = pool.pop(true);
        try {
            MemcachedStorage storage = client.getStorage();
            storage.getMulti(keys);
            while(storage.fetch() != null){
                // hoge
            }
            //pool.push(client);
            
            return System.currentTimeMillis() - elapsed;
        } finally {
            client.free();
        }
    }
    
    public static long get_multi_without_fetcher_behavior_pool_nonblock(MemcachedPool pool) throws LibMemcachedException, MaximumPoolException {
        long elapsed = System.currentTimeMillis();
        
        MemcachedClient client = pool.pop(false);
        try {
            MemcachedStorage storage = client.getStorage();
            storage.getMulti(keys);
            while(storage.fetch() != null){
                // hoge
            }
            //pool.push(client);
            
            return System.currentTimeMillis() - elapsed;
        } finally {
            client.free();
        }
    }
    
}
