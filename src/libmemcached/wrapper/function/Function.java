package libmemcached.wrapper.function;

import libmemcached.LibMemcached;
import libmemcached.memcached;
import libmemcached.util.pool;

abstract class Function {
    
    private static final memcached memcached = LibMemcached.memcached;
    
    private static final pool pool = LibMemcached.pool;
    
    protected static memcached getMemcached(){
        return memcached;
    }
    
    protected static pool getPool(){
        return pool;
    }

}
