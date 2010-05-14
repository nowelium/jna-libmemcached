package libmemcached;

import libmemcached.memcached.memcached_st;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class LibMemcached {
    
    public static final String JNA_LIBRARY_NAME = "memcached";
    
    public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(JNA_LIBRARY_NAME);
    
    public static final memcached memcached = (memcached) Native.loadLibrary(JNA_LIBRARY_NAME, memcached.class);
    
    public LibMemcached(){
        // nop
    }
    
    public static String getLibraryVersion(){
        return memcached.memcached_lib_version();
    }
    
    public static String error(memcached_st ptr, int rc){
        return memcached.memcached_strerror(ptr, rc);
    }
    
}
