package libmemcached.wrapper.function;

import libmemcached.compat.size_t;
import libmemcached.compat.time_t;
import libmemcached.memcached.memcached_st;
import libmemcached.result.memcached_result_st;
import libmemcached.wrapper.type.ReturnType;

public class Result extends Function {
    
    public static void memcached_result_free(memcached_result_st result){
        getMemcached().memcached_result_free(result);
    }
    
    public static void memcached_result_reset(memcached_result_st ptr){
        getMemcached().memcached_result_reset(ptr);
    }
    
    public static memcached_result_st memcached_result_create(memcached_st ptr){
        return memcached_result_create(ptr, null);
    }
    
    public static memcached_result_st memcached_result_create(memcached_st ptr, memcached_result_st result){
        return getMemcached().memcached_result_create(ptr, result);
    }
    
    public static String memcached_result_key_value(memcached_result_st self){
        return getMemcached().memcached_result_key_value(self);
    }
    
    public static long memcached_result_key_length(memcached_result_st self){
        final size_t size = getMemcached().memcached_result_key_length(self);
        return size.longValue();
    }
    
    public static String memcached_result_value(memcached_result_st self){
        return getMemcached().memcached_result_value(self);
    }
    
    public static long memcached_result_length(memcached_result_st self){
        final size_t size = getMemcached().memcached_result_length(self);
        return size.longValue();
    }
    
    public static int memcached_result_flags(memcached_result_st self){
        return getMemcached().memcached_result_flags(self);
    }
    
    public static long memcached_result_cas(memcached_result_st self){
        return getMemcached().memcached_result_cas(self);
    }
    
    public static ReturnType memcached_result_set_value(memcached_result_st ptr, String value){
        final size_t length = new size_t(value.getBytes().length);
        final int rc = getMemcached().memcached_result_set_value(ptr, value, length);
        return ReturnType.get(rc);
    }
    
    public static void memcached_result_set_flags(memcached_result_st self, int flags){
        getMemcached().memcached_result_set_flags(self, flags);
    }
    
    public static void memcached_result_set_expiration(memcached_result_st self, int expiration){
        final time_t expire = new time_t(expiration);
        getMemcached().memcached_result_set_expiration(self, expire);
    }

}
