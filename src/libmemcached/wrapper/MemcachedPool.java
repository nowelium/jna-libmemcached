package libmemcached.wrapper;

import static libmemcached.wrapper.function.Pool.memcached_pool_behavior_get;
import static libmemcached.wrapper.function.Pool.memcached_pool_behavior_set;
import static libmemcached.wrapper.function.Pool.memcached_pool_destroy;
import static libmemcached.wrapper.function.Pool.memcached_pool_pop;
import static libmemcached.wrapper.function.Pool.memcached_pool_push;
import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.util.pool.memcached_pool_st;
import libmemcached.wrapper.type.BehaviorType;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedPool {
    
    protected final MemcachedClient memcached;
    
    protected final memcached_pool_st pool_st;
    
    protected MemcachedPool(MemcachedClient memcached, memcached_pool_st pool_st){
        this.memcached = memcached;
        this.pool_st = pool_st;
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        memcached_pool_destroy(pool_st);
    }
    
    public MemcachedClient pop(boolean block) throws LibMemcachedException {
        memcached_st mmc = memcached_pool_pop(pool_st, block);
        return memcached.newInstance(mmc);
    }
    
    public ReturnType push(MemcachedClient memcached){
        return memcached_pool_push(pool_st, memcached.memcached_st);
    }
    
    public ReturnType setBehavior(BehaviorType type, long data){
        return memcached_pool_behavior_set(pool_st, type, data);
    }
    
    public long getBehavior(BehaviorType type) throws LibMemcachedException {
        return memcached_pool_behavior_get(pool_st, type);
    }

}
