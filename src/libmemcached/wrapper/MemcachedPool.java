package libmemcached.wrapper;

import static libmemcached.wrapper.function.Pool.memcached_pool_behavior_get;
import static libmemcached.wrapper.function.Pool.memcached_pool_behavior_set;
import static libmemcached.wrapper.function.Pool.memcached_pool_destroy;
import static libmemcached.wrapper.function.Pool.memcached_pool_pop;
import static libmemcached.wrapper.function.Pool.memcached_pool_push;
import libmemcached.exception.LibMemcachedException;
import libmemcached.exception.LibMemcachedRuntimeException;
import libmemcached.exception.MaximumPoolException;
import libmemcached.memcached.memcached_st;
import libmemcached.util.pool.memcached_pool_st;
import libmemcached.wrapper.type.BehaviorType;
import libmemcached.wrapper.type.DistributionType;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedPool {
    
    protected final memcached_pool_st pool_st;
    
//    @SuppressWarnings("unused")
//    private final Object finalizer = new Object(){
//        @Override
//        protected void finalize() throws Throwable {
//            memcached_pool_destroy(pool_st);
//            super.finalize();
//        }
//    };
    
    protected MemcachedPool(memcached_pool_st pool_st){
        this.pool_st = pool_st;
    }
    
    public MemcachedClient pop(boolean block) throws LibMemcachedException, MaximumPoolException {
        memcached_st mmc = memcached_pool_pop(pool_st, block);
        return MemcachedClient.newInstance(mmc);
    }
    
    public ReturnType push(MemcachedClient memcached){
        return memcached_pool_push(pool_st, memcached.memcached_st);
    }
    
    public ReturnType setBehavior(BehaviorType type, long data){
        return memcached_pool_behavior_set(pool_st, type, data);
    }
    
    public ReturnType setBehavior(BehaviorType type, boolean data){
        return setBehavior(type, data ? 1 : 0);
    }
    
    public ReturnType setBehavior(BehaviorType type, DistributionType data){
        if(!BehaviorType.DISTRIBUTION.equals(type)){
            throw new LibMemcachedRuntimeException("behavior type not BehaviorType.DISTRIBUTION " + type);
        }
        return setBehavior(type, data.getValue());
    }
    
    public long getBehavior(BehaviorType type) throws LibMemcachedException {
        return memcached_pool_behavior_get(pool_st, type);
    }
    
    public void destroy(){
        memcached_pool_destroy(pool_st);
    }

}
