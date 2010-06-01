package libmemcached;

import libmemcached.util.pool;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class LibMemcached {
    
    public static interface IF extends memcached, pool, Library {
        //
    }
    
    public static final String JNA_LIBRARY_NAME = "memcached";
    
    public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(JNA_LIBRARY_NAME);
    
    public static final memcached memcached = (memcached) Native.loadLibrary(JNA_LIBRARY_NAME, IF.class);
    
    public static final String JNA_MEMCACHED_UTIL_LIBRARY_NAME = "memcachedutil";
    
    public static final pool pool = (pool) Native.loadLibrary(JNA_MEMCACHED_UTIL_LIBRARY_NAME, IF.class);
    
    public LibMemcached(){
        // nop
    }
    
}
