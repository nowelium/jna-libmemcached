package libmemcached.wrapper.function;

import static libmemcached.wrapper.function.StrError.memcached_strerror;
import libmemcached.analyze.memcached_analysis_st;
import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.stats.memcached_stat_st;
import libmemcached.wrapper.type.ReturnType;

import com.sun.jna.ptr.IntByReference;

public class Analyze extends Function {
    
    public static memcached_analysis_st memcached_analyze(memcached_st ptr, memcached_stat_st memc_stat) throws LibMemcachedException {
        final IntByReference error = new IntByReference();
        final memcached_analysis_st analysis_st = getMemcached().memcached_analyze(ptr, memc_stat, error);
        final int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        return analysis_st;
    }
    
    public static void memcached_analyze_free(memcached_analysis_st ptr){
        getMemcached().memcached_analyze_free(ptr);
    }

}
