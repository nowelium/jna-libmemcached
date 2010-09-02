package libmemcached.wrapper.function;

import libmemcached.compat.time_t;
import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.ReturnType;

public class Flush extends Function {
    
    public static ReturnType memcached_flush(memcached_st ptr, int expiration){
        final time_t expire = new time_t(expiration);
        final int rc = getMemcached().memcached_flush(ptr, expire);
        return ReturnType.get(rc);
    }

}
