package libmemcached;

import libmemcached.constants.memcached_return;
import libmemcached.exception.LibMemcachedRuntimeException;
import libmemcached.memcached.memcached_st;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;

public class Example {
    public static void main(String...args){
        memcached memcached = LibMemcached.memcached;
        
        System.out.println(memcached.memcached_lib_version());
        memcached_st mmc = memcached.memcached_create(null);
        
        memcached.memcached_server_add(mmc, "localhost", 11211);
        {
            String key = "hoge";
            NativeLong keylen = new NativeLong(key.length());
            String value = "1234";
            NativeLong valuelen = new NativeLong(value.length());
            
            int rc = memcached.memcached_set(mmc, key, keylen, value, valuelen, 10, 0);
            if (memcached_return.MEMCACHED_SUCCESS != rc && memcached_return.MEMCACHED_BUFFERED != rc) {
                memcached.memcached_free(mmc);
                throw new LibMemcachedRuntimeException(memcached.memcached_strerror(mmc, rc));
            }
            if(memcached_return.MEMCACHED_SUCCESS == rc){
                System.out.println("set success!");
            }
            
            String getKey = "hoge";
            NativeLong getKeyLength = new NativeLong(key.length());
            NativeLongByReference returnValueLength = new NativeLongByReference();
            IntByReference returnFlags = new IntByReference();
            IntByReference returnError = new IntByReference();
            String result = memcached.memcached_get(mmc, getKey, getKeyLength, returnValueLength, returnFlags, returnError);
            
            int error = returnError.getValue();
            if(memcached_return.MEMCACHED_SUCCESS == error){
                System.out.println("get success!");
            }
            System.out.println("result = " + result + ", length = " + returnValueLength.getValue().longValue() + ", flags = " + returnFlags.getValue() + "");
        }
        memcached.memcached_free(mmc);
    }
}
