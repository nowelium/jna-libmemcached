package libmemcached.wrapper.function;

import libmemcached.LibMemcached;
import libmemcached.memcached;
import libmemcached.util.pool;

abstract class Function {
    
    protected static memcached getMemcached(){
        return LibMemcached.getMemcached();
    }
    
    protected static pool getPool(){
        return LibMemcached.getPool();
    }

}
