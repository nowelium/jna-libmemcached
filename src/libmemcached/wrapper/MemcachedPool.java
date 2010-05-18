package libmemcached.wrapper;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

import libmemcached.memcached;
import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.util.pool.memcached_pool_st;
import libmemcached.wrapper.type.BehaviorType;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedPool {
    
    protected final memcached handler;
    
    protected final MemcachedClient memcached;
    
    protected final memcached_pool_st pool_st;
    
    protected MemcachedPool(MemcachedClient memcached, memcached_pool_st pool_st){
        this.handler = MemcachedClient.handler;
        this.memcached = memcached;
        this.pool_st = pool_st;
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        handler.memcached_pool_destroy(pool_st);
    }
    
    public MemcachedClient pop(boolean block) throws LibMemcachedException {
        IntByReference error = new IntByReference();
        memcached_st mmc = handler.memcached_pool_pop(pool_st, block, error);
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached.error(rc));
        }
        return memcached.newInstance(mmc);
    }
    
    public ReturnType push(MemcachedClient memcached){
        int rc = handler.memcached_pool_push(pool_st, memcached.memcached_st);
        return ReturnType.get(rc);
    }
    
    public ReturnType setBehavior(BehaviorType type, long data){
        int rc = handler.memcached_pool_behavior_set(pool_st, type.getValue(), data);
        return ReturnType.get(rc);
    }
    
    public long getBehavior(BehaviorType type) throws LibMemcachedException {
        LongByReference value = new LongByReference();
        int rc = handler.memcached_pool_behavior_get(pool_st, type.getValue(), value);
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached.error(rc));
        }
        return value.getValue();
    }

}
