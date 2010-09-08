package libmemcached.wrapper.function;

import libmemcached.compat.size_t;
import libmemcached.compat.time_t;
import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.ReturnType;

public class Storage extends Function {
    
    public static ReturnType memcached_set(memcached_st ptr, String key, String value, int expiration, int flags){
        final size_t key_length = new size_t(key.getBytes().length);
        final size_t value_length = new size_t(value.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_set(ptr, key, key_length, value, value_length, expire, flags);
        return ReturnType.get(rc);
    }

    public static ReturnType memcached_set_by_key(memcached_st ptr, String master_key, String key, String value, int expiration, int flags){
        final size_t master_key_length = new size_t(master_key.getBytes().length);
        final size_t key_length = new size_t(key.getBytes().length);
        final size_t value_length = new size_t(value.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_set_by_key(ptr, master_key, master_key_length, key, key_length, value, value_length, expire, flags);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_add(memcached_st ptr, String key, String value, int expiration, int flags){
        final size_t key_length = new size_t(key.getBytes().length);
        final size_t value_length = new size_t(value.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_add(ptr, key, key_length, value, value_length, expire, flags);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_add_by_key(memcached_st ptr, String master_key, String key, String value, int expiration, int flags){
        final size_t master_key_length = new size_t(master_key.getBytes().length);
        final size_t key_length = new size_t(key.getBytes().length);
        final size_t value_length = new size_t(value.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_add_by_key(ptr, master_key, master_key_length, key, key_length, value, value_length, expire, flags);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_replace(memcached_st ptr, String key, String value, int expiration, int flags){
        final size_t key_length = new size_t(key.getBytes().length);
        final size_t value_length = new size_t(value.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_replace(ptr, key, key_length, value, value_length, expire, flags);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_replace_by_key(memcached_st ptr, String master_key, String key, String value, int expiration, int flags){
        final size_t master_key_length = new size_t(master_key.getBytes().length);
        final size_t key_length = new size_t(key.getBytes().length);
        final size_t value_length = new size_t(value.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_replace_by_key(ptr, master_key, master_key_length, key, key_length, value, value_length, expire, flags);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_append(memcached_st ptr, String key, String value, int expiration, int flags){
        final size_t key_length = new size_t(key.getBytes().length);
        final size_t value_length = new size_t(value.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_append(ptr, key, key_length, value, value_length, expire, flags);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_append_by_key(memcached_st ptr, String master_key, String key, String value, int expiration, int flags){
        final size_t master_key_length = new size_t(master_key.getBytes().length);
        final size_t key_length = new size_t(key.getBytes().length);
        final size_t value_length = new size_t(value.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_append_by_key(ptr, master_key, master_key_length, key, key_length, value, value_length, expire, flags);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_prepend(memcached_st ptr, String key, String value, int expiration, int flags){
        final size_t key_length = new size_t(key.getBytes().length);
        final size_t value_length = new size_t(value.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_prepend(ptr, key, key_length, value, value_length, expire, flags);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_prepend_by_key(memcached_st ptr, String master_key, String key, String value, int expiration, int flags){
        final size_t master_key_length = new size_t(master_key.getBytes().length);
        final size_t key_length = new size_t(key.getBytes().length);
        final size_t value_length = new size_t(value.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_prepend_by_key(ptr, master_key, master_key_length, key, key_length, value, value_length, expire, flags);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_cas(memcached_st ptr, String key, String value, int expiration, int flags, long cas){
        final size_t key_length = new size_t(key.getBytes().length);
        final size_t value_length = new size_t(value.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_cas(ptr, key, key_length, value, value_length, expire, flags, cas);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_cas_by_key(memcached_st ptr, String master_key, String key, String value, int expiration, int flags, long cas){
        final size_t master_key_length = new size_t(master_key.getBytes().length);
        final size_t key_length = new size_t(key.getBytes().length);
        final size_t value_length = new size_t(value.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_cas_by_key(ptr, master_key, master_key_length, key, key_length, value, value_length, expire, flags, cas);
        return ReturnType.get(rc);
    }

}
