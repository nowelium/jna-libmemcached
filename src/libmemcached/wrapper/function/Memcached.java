package libmemcached.wrapper.function;

import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.ReturnType;

public class Memcached extends Function {
    
    public static memcached_st memcached_create(){
        return memcached_create(null);
    }
    
    public static memcached_st memcached_create(memcached_st ptr){
        return getMemcached().memcached_create(ptr);
    }
    
    public static void memcached_free(memcached_st ptr){
        getMemcached().memcached_free(ptr);
    }
    
    public static memcached_st memcached_clone(memcached_st destination, memcached_st source){
        return getMemcached().memcached_clone(destination, source);
    }
    
    public static ReturnType memcached_push(memcached_st destination, memcached_st source){
        final int rc = getMemcached().memcached_push(destination, source);
        return ReturnType.get(rc);
    }

}
