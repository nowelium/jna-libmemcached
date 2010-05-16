package libmemcached;

import libmemcached.memcached.memcached_st;
import libmemcached.types.memcached_execute_function;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public interface callback {
    
    public static class memcached_callback_st {
        // C type: memcached_execute_fn *callback
        public memcached_execute_function callback;
        // C type: void *context
        public Pointer context;
        // C type: uint32_t
        public int number_of_callback;
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
