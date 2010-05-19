package libmemcached.wrapper.function;

import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.ReturnType;

public class StrError extends Function {
    
    public static String memcached_strerror(int rc){
        return memcached_strerror(null, rc);
    }
    
    public static String memcached_strerror(ReturnType rc){
        return memcached_strerror(null, rc.getValue());
    }
    
    public static String memcached_strerror(memcached_st ptr, int rc){
        return getMemcached().memcached_strerror(ptr, rc);
    }
    
}
