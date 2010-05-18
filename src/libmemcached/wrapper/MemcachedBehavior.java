package libmemcached.wrapper;

import libmemcached.memcached;
import libmemcached.wrapper.type.BehaviorType;
import libmemcached.wrapper.type.DistributionType;
import libmemcached.wrapper.type.HashType;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedBehavior {
    
    protected final memcached handler;
    
    protected final MemcachedClient memcached;
    
    protected MemcachedBehavior(MemcachedClient memcached){
        this.handler = MemcachedClient.handler;
        this.memcached = memcached;
    }
    
    public ReturnType set(BehaviorType type, long data){
        int rc = handler.memcached_behavior_set(memcached.memcached_st, type.getValue(), data);
        return ReturnType.get(rc);
    }
    
    public long get(BehaviorType type){
        return handler.memcached_behavior_get(memcached.memcached_st, type.getValue());
    }
    
    public ReturnType setDistribution(DistributionType type){
        int rc = handler.memcached_behavior_get_distribution_hash(memcached.memcached_st);
        return ReturnType.get(rc);
    }
    
    public DistributionType getDistribution(){
        int distribution = handler.memcached_behavior_get_distribution(memcached.memcached_st);
        return DistributionType.get(distribution);
    }
    
    public ReturnType setKeyHash(HashType type){
        int rc = handler.memcached_behavior_set_key_hash(memcached.memcached_st, type.getValue());
        return ReturnType.get(rc);
    }
    
    public HashType getKeyHash(){
        int hashType = handler.memcached_behavior_get_key_hash(memcached.memcached_st);
        return HashType.get(hashType);
    }
    
    public ReturnType setDistributionHash(HashType type){
        int rc = handler.memcached_behavior_set_distribution_hash(memcached.memcached_st, type.getValue());
        return ReturnType.get(rc);
    }
    
    public HashType getDistributionHash(){
        int hashType = handler.memcached_behavior_get_distribution_hash(memcached.memcached_st);
        return HashType.get(hashType);
    }

}
