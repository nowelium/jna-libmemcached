package libmemcached.wrapper;

import static libmemcached.wrapper.function.Stats.memcached_stat_free;
import static libmemcached.wrapper.function.Stats.memcached_stat_get_keys;
import static libmemcached.wrapper.function.Stats.memcached_stat_get_value;
import static libmemcached.wrapper.function.Stats.memcached_stat_servername;
import libmemcached.exception.LibMemcachedException;
import libmemcached.stats.memcached_stat_st;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedStats {

    protected final MemcachedClient memcached;
    
    protected final memcached_stat_st stat_st;
    
    protected MemcachedStats(MemcachedClient memcached, memcached_stat_st stat_st) {
        this.memcached = memcached;
        this.stat_st = stat_st;
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        memcached_stat_free(memcached.memcached_st, stat_st);
    }
    
    public ReturnType serverName(String args, String hostname, int port){
        return memcached_stat_servername(stat_st, args, hostname, port);
    }

    public String getValue(String key) throws LibMemcachedException {
        return memcached_stat_get_value(memcached.memcached_st, stat_st, key);
    }
    
    public String[] getKeys() throws LibMemcachedException {
        return memcached_stat_get_keys(memcached.memcached_st, stat_st);
    }
}