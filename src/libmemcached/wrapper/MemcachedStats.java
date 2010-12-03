package libmemcached.wrapper;

import static libmemcached.wrapper.function.Stats.memcached_stat_free;
import static libmemcached.wrapper.function.Stats.memcached_stat_get_keys;
import static libmemcached.wrapper.function.Stats.memcached_stat_get_value;

import java.util.concurrent.atomic.AtomicBoolean;

import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.stats.memcached_stat_st;

public class MemcachedStats {
    
    protected final AtomicBoolean free = new AtomicBoolean(false);
    
    protected final memcached_st memcached_st;

    protected final memcached_stat_st stat_st;

//    @SuppressWarnings("unused")
//    private final Object finalizer = new Object(){
//        @Override
//        protected void finalize() throws Throwable {
//            memcached_stat_free(memcached.memcached_st, stat_st);
//            super.finalize();
//        }
//    };
    
    protected MemcachedStats(memcached_st memcached_st, memcached_stat_st stat_st) {
        this.memcached_st = memcached_st;
        this.stat_st = stat_st;
    }
    
    public String getValue(String key) throws LibMemcachedException {
        return memcached_stat_get_value(memcached_st, stat_st, key);
    }
    
    public String[] getKeys() throws LibMemcachedException {
        return memcached_stat_get_keys(memcached_st, stat_st);
    }
    
    public void free(){
        if(free.getAndSet(true)){
            return ;
        }
        memcached_stat_free(memcached_st, stat_st);
    }
}
