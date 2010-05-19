package libmemcached.wrapper.function;

import libmemcached.LibMemcached;
import libmemcached.memcached;

abstract class Function {
    
    private static final memcached memcached = LibMemcached.memcached;
    
    protected static memcached getMemcached(){
        return memcached;
    }

}
