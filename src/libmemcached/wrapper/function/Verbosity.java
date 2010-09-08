package libmemcached.wrapper.function;

import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.ReturnType;

public class Verbosity extends Function {
    
    public static ReturnType memcached_verbosity(memcached_st ptr, int verbosity){
        final int rc = getMemcached().memcached_verbosity(ptr, verbosity);
        return ReturnType.get(rc);
    }

}
