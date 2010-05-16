package libmemcached;

import libmemcached.memcached.memcached_st;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;

public interface result {
    public static class memcached_result_st extends Structure {
        public static class options extends Structure {
            // C type: bool
            public boolean is_allocated;
            // C type: bool
            public boolean is_initialized;
            
            public options() {
                super();
            }
            public options(boolean is_allocated, boolean is_initialized) {
                super();
                this.is_allocated = is_allocated;
                this.is_initialized = is_initialized;
            }
            public static class ByReference extends options implements Structure.ByReference {

            }
            public static class ByValue extends options implements Structure.ByValue {

            }
        }
        
        // C type: uint32_t
        public int item_flags;
        // C type: time_t
        public int item_expiration;
        // C type: size_t
        public NativeLong key_length;
        // C type: uint64
        public long item_cas;
        // C type: const memcached_st*
        public memcached_st.ByReference root;
        // C type: char[MEMCACHED_MAX_KEY]
        public byte[] item_key = new byte[constants.MEMCACHED_MAX_KEY];
        // C type: options_struct
        public options options;
        
        public memcached_result_st(){
            super();
        }
        public memcached_result_st(int item_flags, NativeLong key_length, long item_cas, memcached_st.ByReference root, byte[] item_key, options options) {
            super();
            if (item_key.length != this.item_key.length) {
                throw new java.lang.IllegalArgumentException("Wrong array size !");
            }
            
            this.item_flags = item_flags;
            this.key_length = key_length;
            this.item_cas = item_cas;
            this.root = root;
            this.item_key = item_key;
            this.options = options;
        }
        
        public static class ByReference extends memcached_result_st implements Structure.ByReference {

        }
        public static class ByValue extends memcached_result_st implements Structure.ByValue {

        }
    }
    
    /**
     * C func: void memcached_result_free(memcached_result_st *result);
     */
    public void memcached_result_free(memcached_result_st result);

    /**
     * C func: void memcached_result_reset(memcached_result_st *ptr);
     */
    public void memcached_result_reset(memcached_result_st ptr);

    /**
     * C func: memcached_result_st *memcached_result_create(
     *  const memcached_st *ptr,
     *  memcached_result_st *result
     * );
     */
    public memcached_result_st memcached_result_create(
        memcached_st ptr,
        memcached_result_st result
    );

    /**
     * C func: const char *memcached_result_key_value(const memcached_result_st *self);
     */
    public String memcached_result_key_value(memcached_result_st self);

    /**
     * C func: size_t memcached_result_key_length(const memcached_result_st *self)
     */
    public NativeLong memcached_result_key_length(memcached_result_st self);

    /**
     * C func: const char *memcached_result_value(const memcached_result_st *self)
     */
    public String memcached_result_value(memcached_result_st self);

    /**
     * C func: size_t memcached_result_length(const memcached_result_st *self)
     */
    public NativeLong memcached_result_length(memcached_result_st self);

    /**
     * C func: uint32_t memcached_result_flags(const memcached_result_st *self)
     */
    public int memcached_result_flags(memcached_result_st self);

    /**
     * C func: uint64_t memcached_result_cas(const memcached_result_st *self)
     */
    public long memcached_result_cas(memcached_result_st self);

    /**
     * C func: memcached_return_t memcached_result_set_value(
     *  memcached_result_st *ptr,
     *  const char *value,
     *  size_t length
     * )
     */
    public int memcached_result_set_value(
        memcached_result_st ptr,
        String value,
        NativeLong length
    );

    /**
     * C func: void memcached_result_set_flags(memcached_result_st *self, uint32_t flags)
     */
    public void memcached_result_set_flags(memcached_result_st self, int flags);

    /**
     * C func: void memcached_result_set_expiration(memcached_result_st *self, time_t expiration)
     */
    public void memcached_result_set_expiration(memcached_result_st self, int expiration);
}
