package libmemcached.wrapper;

import static libmemcached.wrapper.function.Behavior.memcached_behavior_get;
import static libmemcached.wrapper.function.Behavior.memcached_behavior_get_distribution;
import static libmemcached.wrapper.function.Behavior.memcached_behavior_get_distribution_hash;
import static libmemcached.wrapper.function.Behavior.memcached_behavior_get_key_hash;
import static libmemcached.wrapper.function.Behavior.memcached_behavior_set;
import static libmemcached.wrapper.function.Behavior.memcached_behavior_set_distribution;
import static libmemcached.wrapper.function.Behavior.memcached_behavior_set_distribution_hash;
import static libmemcached.wrapper.function.Behavior.memcached_behavior_set_key_hash;
import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.BehaviorType;
import libmemcached.wrapper.type.DistributionType;
import libmemcached.wrapper.type.HashType;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedBehavior {
    
    protected final memcached_st memcached_st;
    
    protected MemcachedBehavior(memcached_st memcached_st){
        this.memcached_st = memcached_st;
    }
    
    public ReturnType set(BehaviorType type, long data){
        return memcached_behavior_set(memcached_st, type, data);
    }
    
    public ReturnType set(BehaviorType type, boolean data){
        return set(type, data ? 1 : 0);
    }
    
    public long get(BehaviorType type){
        return memcached_behavior_get(memcached_st, type);
    }
    
    public ReturnType setDistribution(DistributionType type){
        return memcached_behavior_set_distribution(memcached_st, type);
    }
    
    public DistributionType getDistribution(){
        return memcached_behavior_get_distribution(memcached_st);
    }
    
    public ReturnType setKeyHash(HashType type){
        return memcached_behavior_set_key_hash(memcached_st, type);
    }
    
    public HashType getKeyHash(){
        return memcached_behavior_get_key_hash(memcached_st);
    }
    
    public ReturnType setDistributionHash(HashType type){
        return memcached_behavior_set_distribution_hash(memcached_st, type);
    }
    
    public HashType getDistributionHash(){
        return memcached_behavior_get_distribution_hash(memcached_st);
    }

}
