package libmemcached.wrapper;

import libmemcached.LibMemcached;
import libmemcached.memcached.memcached_st;

public class Strerror {
    
    public static String error(memcached_st ptr, ReturnType rc){
        return LibMemcached.memcached.memcached_strerror(ptr, rc.getValue());
    }

}
