package libmemcached;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class LibMemcached {
    
    public static interface IF extends memcached, Library {
        // alias
    }
    
    public static final String JNA_LIBRARY_NAME = "memcached";
    
    public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(JNA_LIBRARY_NAME);
    
    public static final memcached memcached = (memcached) Native.loadLibrary(JNA_LIBRARY_NAME, IF.class);
    
    public LibMemcached(){
        // nop
    }
    
}
