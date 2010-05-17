package libmemcached;

import libmemcached.exception.LibMemcachedRuntimeException;
import libmemcached.memcached.memcached_st;
import libmemcached.constants.memcached_return;

import com.sun.jna.NativeLong;

public class Example {
    public static void main(String...args){
        memcached memcached = LibMemcached.memcached;
        
        System.out.println(memcached.memcached_lib_version());
        memcached_st mmc = memcached.memcached_create(null);
        
        memcached.memcached_server_add(mmc, "localhost", 11211);
        {
            String key = "hoge";
            NativeLong keylen = new NativeLong();
            keylen.setValue(key.length());
            String value = "1234";
            NativeLong valuelen = new NativeLong();
            valuelen.setValue(value.length());
            
            int rc = memcached.memcached_set(mmc, key, keylen, value, valuelen, 10, 0);
            if (memcached_return.MEMCACHED_SUCCESS != rc && memcached_return.MEMCACHED_BUFFERED != rc) {
                memcached.memcached_free(mmc);
                throw new LibMemcachedRuntimeException(memcached.memcached_strerror(mmc, rc));
            }
        }
        memcached.memcached_free(mmc);
    }
}
