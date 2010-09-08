package libmemcached.wrapper.function;

import libmemcached.compat.hashkit_st;
import libmemcached.compat.size_t;
import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.HashType;
import libmemcached.wrapper.type.ReturnType;

public class Hash extends Function {
    
    public static int memcached_generate_hash_value(String key, HashType hash_algorithm){
        final size_t key_length = new size_t(key.length());
        return getMemcached().memcached_generate_hash_value(key, key_length, hash_algorithm.getValue());
    }
    
    public static hashkit_st memcached_get_hashkit(memcached_st ptr){
        return getMemcached().memcached_get_hashkit(ptr);
    }
    
    public static ReturnType memcached_set_hashkit(memcached_st ptr, hashkit_st hashk){
        final int rc = getMemcached().memcached_set_hashkit(ptr, hashk);
        return ReturnType.get(rc);
    }
    
    public static int memcached_generate_hash(memcached_st ptr, String key){
        final size_t key_length = new size_t(key.length());
        return getMemcached().memcached_generate_hash(ptr, key, key_length);
    }
    
    public static void memcached_autoeject(memcached_st ptr){
        getMemcached().memcached_autoeject(ptr);
    }

}
