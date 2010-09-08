package libmemcached.wrapper.function;

import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.ReturnType;

public class Version extends Function {
    
    public static ReturnType memcached_version(memcached_st ptr){
        final int rc = getMemcached().memcached_version(ptr);
        return ReturnType.get(rc);
    }
    
    public static String memcached_lib_version(){
        return getMemcached().memcached_lib_version();
    }

}
