package libmemcached;

import libmemcached.memcached.memcached_st;

import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.ptr.IntByReference;

public interface callback {
    
    /*
    public static class memcached_callback_st extends Structure {
        // C type: memcached_execute_fn* in memcached_execute_function
        public Pointer callback;
        // C type: void*
        public Pointer context;
        // C type: uint32_t
        public int number_of_callback;
        
        public memcached_callback_st(){
            super();
            setAutoSynch(false);
        }
        
        public static class ByReference extends memcached_callback_st implements Structure.ByReference {
            
        }
        public static class ByValue extends memcached_callback_st implements Structure.ByValue {
            
        }
    }
    */
    
    public static class memcached_callback_st extends PointerType {
        // ignore
    }
    
    /**
     * C func: memcached_return_t memcached_callback_set(
     *  memcached_st *ptr,
     *  const memcached_callback_t flag,
     *  void *data
     *  );
     */
    public int memcached_callback_set(memcached_st ptr, int flag, Pointer data);
    
    /**
     * C func: void *memcached_callback_get(
     *  memcached_st *ptr,
     *  const memcached_callback_t flag, 
     *  memcached_return_t *error
     * );
     */
    public Pointer memcached_callback_get(memcached_st ptr, int flag, IntByReference error);

}
