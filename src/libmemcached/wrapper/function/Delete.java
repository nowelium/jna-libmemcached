package libmemcached.wrapper.function;

import libmemcached.compat.size_t;
import libmemcached.compat.time_t;
import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.ReturnType;

public class Delete extends Function {
    
    public static ReturnType memcached_delete(memcached_st ptr, String key, int expiration){
        final size_t key_length = new size_t(key.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_delete(ptr, key, key_length, expire);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_delete_by_key(memcached_st ptr, String master_key, String key, int expiration){
        final size_t master_key_length = new size_t(master_key.getBytes().length);
        final size_t key_length = new size_t(key.getBytes().length);
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_delete_by_key(ptr, master_key, master_key_length, key, key_length, expire);
        return ReturnType.get(rc);
    }

}
