package libmemcached.wrapper.function;

import static libmemcached.wrapper.function.StrError.memcached_strerror;
import libmemcached.exception.LibMemcachedException;
import libmemcached.exception.MaximumPoolException;
import libmemcached.memcached.memcached_st;
import libmemcached.util.pool.memcached_pool_st;
import libmemcached.wrapper.type.BehaviorType;
import libmemcached.wrapper.type.ReturnType;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

public class Pool extends Function {
    
    public static memcached_pool_st memcached_pool_create(memcached_st mmc, int initial, int max){
        return getPool().memcached_pool_create(mmc, initial, max);
    }
    
    public static memcached_st memcached_pool_destroy(memcached_pool_st pool){
        return getPool().memcached_pool_destroy(pool);
    }
    
    public static memcached_st memcached_pool_pop(memcached_pool_st pool, boolean block) throws LibMemcachedException, MaximumPoolException {
        final IntByReference error = new IntByReference();
        final memcached_st mmc = getPool().memcached_pool_pop(pool, block, error);
        final int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(rc));
        }
        if(null == mmc){
            throw new MaximumPoolException("reached maxium pool");
        }
        return mmc;
    }
    
    public static ReturnType memcached_pool_push(memcached_pool_st pool, memcached_st mmc){
        final int rc = getPool().memcached_pool_push(pool, mmc);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_pool_behavior_set(memcached_pool_st ptr, BehaviorType flag, long data){
        final int rc = getPool().memcached_pool_behavior_set(ptr, flag.getValue(), data);
        return ReturnType.get(rc);
    }
    
    public static long memcached_pool_behavior_get(memcached_pool_st pool, BehaviorType flag) throws LibMemcachedException {
        final LongByReference value = new LongByReference();
        final int rc = getPool().memcached_pool_behavior_get(pool, flag.getValue(), value);
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(rc));
        }
        return value.getValue();
    }

}
