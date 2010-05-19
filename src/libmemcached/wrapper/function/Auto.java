package libmemcached.wrapper.function;

import libmemcached.compat.size_t;
import libmemcached.compat.time_t;
import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.ReturnType;

public class Auto extends Function {
    
    public static ReturnType memcached_decrement(memcached_st ptr, String key, int offset, long value){
        size_t key_length = new size_t(key.length());
        int rc = getMemcached().memcached_decrement(ptr, key, key_length, offset, value);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_decrement_with_initial(memcached_st ptr, String key, long offset, long initial, int expiration, long value){
        size_t key_length = new size_t(key.length());
        time_t expire = new time_t(expiration);
        int rc = getMemcached().memcached_decrement_with_initial(ptr, key, key_length, offset, initial, expire, value);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_increment(memcached_st ptr, String key, int offset, long value){
        size_t key_length = new size_t(key.length());
        int rc = getMemcached().memcached_increment(ptr, key, key_length, offset, value);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_increment_with_initial(memcached_st ptr, String key, long offset, long initial, int expiration, long value){
        size_t key_length = new size_t(key.length());
        time_t expire = new time_t(expiration);
        int rc = getMemcached().memcached_increment_with_initial(ptr, key, key_length, offset, initial, expire, value);
        return ReturnType.get(rc);
    }
}
