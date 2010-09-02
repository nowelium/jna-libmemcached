package libmemcached;

import libmemcached.compat.sasl_callback_t;
import libmemcached.memcached.memcached_st;

import com.sun.jna.PointerType;

public interface sasl {
    
    /*
    public static class memcached_sasl_st extends Structure {
        // C type: IF LIBMEMCACHED_WITH_SASL_SUPPORT THEN const sasl_callback_t* ELSE const void *
        public Pointer callbacks;
        // C type: bool
        public boolean is_allocated;
        
        public memcached_sasl_st() {
            super();
            setAutoSynch(false);
        }
        
        public static class ByReference extends memcached_sasl_st implements Structure.ByReference {
            
        }
        public static class ByValue extends memcached_sasl_st implements Structure.ByValue {
            
        }
    }
    */
    
    public static class memcached_sasl_st extends PointerType {
        // ignore
    }
    
    /**
     * C func: void memcached_set_sasl_callbacks(
     *  memcached_st *ptr,
     *  const sasl_callback_t *callbacks
     * );
     */
    public void memcached_set_sasl_callbacks(
        memcached_st ptr,
        sasl_callback_t callbacks
    );

    /**
     * C func: memcached_return_t  memcached_set_sasl_auth_data(
     *  memcached_st *ptr,
     *  const char *username,
     *  const char *password
     * );
     */
    public int memcached_set_sasl_auth_data(
        memcached_st ptr,
        String username,
        String password
    );

    /**
     * C func: memcached_return_t memcached_destroy_sasl_auth_data(memcached_st *ptr)
     */
    public int memcached_destroy_sasl_auth_data(memcached_st ptr);

    /**
     * C func: const sasl_callback_t *memcached_get_sasl_callbacks(memcached_st *ptr)
     */
    public sasl_callback_t memcached_get_sasl_callbacks(memcached_st ptr);


}
