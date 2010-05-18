package libmemcached.wrapper;

import com.sun.jna.ptr.IntByReference;

import libmemcached.memcached;
import libmemcached.analyze.memcached_analysis_st;
import libmemcached.exception.LibMemcachedException;
import libmemcached.stats.memcached_stat_st;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedStats {

    protected final memcached handler;
    
    protected final MemcachedClient memcached;
    
    protected final memcached_stat_st stat_st;
    
    protected MemcachedStats(MemcachedClient memcached, memcached_stat_st stat_st) {
        this.handler = MemcachedClient.handler;
        this.memcached = memcached;
        this.stat_st = stat_st;
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        handler.memcached_stat_free(memcached.memcached_st, stat_st);
    }
    
    public MemcachedAnalyze createAnalyze() throws LibMemcachedException {
        IntByReference error = new IntByReference();
        memcached_analysis_st analysis_st = handler.memcached_analyze(memcached.memcached_st, stat_st, error);
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached.error(rc));
        }
        return new MemcachedAnalyze(analysis_st);
    }
    
    public ReturnType serverName(String args, String hostname, int port){
        int rc = handler.memcached_stat_servername(stat_st, args, hostname, port);
        return ReturnType.get(rc);
    }

    public String getValue(String key) throws LibMemcachedException {
        IntByReference error = new IntByReference();
        String result = handler.memcached_stat_get_value(memcached.memcached_st, stat_st, key, error);
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached.error(rc));
        }
        return result;
    }
    
    public String[] getKeys() throws LibMemcachedException {
        IntByReference error = new IntByReference();
        String[] results = handler.memcached_stat_get_keys(memcached.memcached_st, stat_st, error);
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached.error(rc));
        }
        return results;
    }
}
