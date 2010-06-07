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
    
    public static final String JNA_MEMCACHED_UTIL_LIBRARY_NAME = "memcachedutil";
    
    public static final NativeLibrary JNA_MEMCACHED_UTIL_LIB = NativeLibrary.getInstance(JNA_MEMCACHED_UTIL_LIBRARY_NAME);
    
    public static final memcached memcached;
    
    public static final pool pool;
    
    static {
        final IF _memcached = (IF) Native.loadLibrary(JNA_LIBRARY_NAME, IF.class);
        memcached = (libmemcached.memcached) Native.synchronizedLibrary(_memcached);
        
        final IF _pool = (IF) Native.loadLibrary(JNA_MEMCACHED_UTIL_LIBRARY_NAME, IF.class);
        pool = (libmemcached.util.pool) Native.synchronizedLibrary(_pool);
    }
    
    /*
    private static final ThreadLocal<libmemcached.memcached> memcachedLocal = new ThreadLocal<libmemcached.memcached>(){
        @Override
        protected libmemcached.memcached initialValue() {
            return (libmemcached.memcached) Native.loadLibrary(JNA_LIBRARY_NAME, IF.class);
        }
    };
    */
    
    /*
    private static final ThreadLocal<libmemcached.util.pool> poolLocal = new ThreadLocal<libmemcached.util.pool>(){
        @Override
        protected libmemcached.util.pool initialValue() {
            //return (libmemcached.util.pool) Native.loadLibrary(JNA_MEMCACHED_UTIL_LIBRARY_NAME, IF.class);
            
            final IF pool = (IF) Native.loadLibrary(JNA_MEMCACHED_UTIL_LIBRARY_NAME, IF.class);
            return (pool) Native.synchronizedLibrary(pool);
        }
    };
    */
    
    protected LibMemcached(){
        // nop
    }
    
    public static memcached getMemcached(){
        return memcached;
    }
    
    public static pool getPool(){
        return pool;
    }
}
