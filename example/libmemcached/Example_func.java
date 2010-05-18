package libmemcached;

import libmemcached.compat.size_t;
import libmemcached.compat.time_t;
import libmemcached.constants.memcached_return;
import libmemcached.exception.LibMemcachedRuntimeException;
import libmemcached.memcached.memcached_st;

public class Example_func {
    public static void main(String...args){
        memcached memcached = LibMemcached.memcached;
        
        System.out.println(memcached.memcached_lib_version());
        memcached_st mmc = memcached.memcached_create(null);
        
        memcached.memcached_server_add(mmc, "localhost", 11211);
        {
            String key = "hoge";
            size_t keylen = new size_t(key.length());
            String value = "1234";
            size_t valuelen = new size_t(value.length());
            time_t expiration = new time_t(10);
            
            int rc = memcached.memcached_set(mmc, key, keylen, value, valuelen, expiration, 0);
            if (memcached_return.MEMCACHED_SUCCESS != rc && memcached_return.MEMCACHED_BUFFERED != rc) {
                memcached.memcached_free(mmc);
                throw new LibMemcachedRuntimeException(memcached.memcached_strerror(mmc, rc));
            }
        }
        memcached.memcached_free(mmc);
    }
}
