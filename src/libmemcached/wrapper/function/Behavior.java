package libmemcached.wrapper.function;

import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.BehaviorType;
import libmemcached.wrapper.type.DistributionType;
import libmemcached.wrapper.type.HashType;
import libmemcached.wrapper.type.ReturnType;

public class Behavior extends Function {
    
    public static ReturnType memcached_behavior_set(memcached_st ptr, BehaviorType flag, long data){
        final int rc = getMemcached().memcached_behavior_set(ptr, flag.getValue(), data);
        return ReturnType.get(rc);
    }
    
    public static long memcached_behavior_get(memcached_st ptr, BehaviorType flag){
        return getMemcached().memcached_behavior_get(ptr, flag.getValue());
    }
    
    public static ReturnType memcached_behavior_set_distribution(memcached_st ptr, DistributionType type){
        final int rc = getMemcached().memcached_behavior_set_distribution(ptr, type.getValue());
        return ReturnType.get(rc);
    }
    
    public static DistributionType memcached_behavior_get_distribution(memcached_st ptr){
        final int dt = getMemcached().memcached_behavior_get_distribution(ptr);
        return DistributionType.get(dt);
    }
    
    public static ReturnType memcached_behavior_set_key_hash(memcached_st ptr, HashType type){
        final int rc = getMemcached().memcached_behavior_set_key_hash(ptr, type.getValue());
        return ReturnType.get(rc);
    }
    
    public static HashType memcached_behavior_get_key_hash(memcached_st ptr){
        final int ht = getMemcached().memcached_behavior_get_key_hash(ptr);
        return HashType.get(ht);
    }
    
    public static ReturnType memcached_behavior_set_distribution_hash(memcached_st ptr, HashType type){
        final int rc = getMemcached().memcached_behavior_set_distribution_hash(ptr, type.getValue());
        return ReturnType.get(rc);
    }
    
    public static HashType memcached_behavior_get_distribution_hash(memcached_st ptr){
        final int ht = getMemcached().memcached_behavior_get_distribution_hash(ptr);
        return HashType.get(ht);
    }

}
