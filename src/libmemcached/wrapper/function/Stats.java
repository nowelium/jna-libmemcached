package libmemcached.wrapper.function;

import static libmemcached.wrapper.function.StrError.memcached_strerror;
import com.sun.jna.ptr.IntByReference;

import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.stats.memcached_stat_st;
import libmemcached.wrapper.type.ReturnType;

public class Stats extends Function {
    
    public static void memcached_stat_free(memcached_st ptr0, memcached_stat_st ptr1){
        getMemcached().memcached_stat_free(ptr0, ptr1);
    }
    
    public static memcached_stat_st memcached_stat(memcached_st ptr) throws LibMemcachedException {
        return memcached_stat(ptr, null);
    }
    
    public static memcached_stat_st memcached_stat(memcached_st ptr, String args) throws LibMemcachedException {
        final IntByReference error = new IntByReference();
        final memcached_stat_st stat_st = getMemcached().memcached_stat(ptr, args, error);
        final int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        return stat_st;
    }
    
    public static memcached_stat_st memcached_stat(memcached_st ptr, String args, String hostname, int port) throws LibMemcachedException {
        final memcached_stat_st memc_stat = memcached_stat(ptr, null);
        final int rc = getMemcached().memcached_stat_servername(memc_stat, args, hostname, port);
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        return memc_stat;
    }
    
    public static ReturnType memcached_stat_servername(memcached_stat_st memc_stat, String args, String hostname, int port){
        final int rc = getMemcached().memcached_stat_servername(memc_stat, args, hostname, port);
        return ReturnType.get(rc);
    }
    
    public static String memcached_stat_get_value(memcached_st ptr, memcached_stat_st memc_stat, String key) throws LibMemcachedException {
        final IntByReference error = new IntByReference();
        final String value = getMemcached().memcached_stat_get_value(ptr, memc_stat, key, error);
        final int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        return value;
    }
    
    public static String[] memcached_stat_get_keys(memcached_st ptr, memcached_stat_st memc_stat) throws LibMemcachedException {
        final IntByReference error = new IntByReference();
        final String[] keys = getMemcached().memcached_stat_get_keys(ptr, memc_stat, error);
        final int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        return keys;
    }

}
