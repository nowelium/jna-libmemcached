package libmemcached;

import libmemcached.memcached.memcached_st;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;

public interface string {

    public static class memcached_string_st extends Structure {
        // C type: memcached_st*
        public memcached_st.ByReference root;
        // C type: char*
        public String end;
        // C type: char+
        public String string;
        // C type: size_t
        public NativeLong currentSize;
        // C type: size_t
        public NativeLong blockSize;
        // C type: bool
        public boolean is_allocated;
        
        public static class ByReference extends memcached_string_st implements Structure.ByReference {
            
        }
        public static class ByValue extends memcached_string_st implements Structure.ByValue {

        }
    }
    
    /**
     * C func: memcached_string_st *memcached_string_create(
     *  memcached_st *ptr,
     *  memcached_string_st *string,
     *  size_t initial_size
     * );
     */
    public memcached_string_st memcached_string_create(
        memcached_st ptr,
        memcached_string_st string,
        NativeLong initial_size
    );

    /**
     * C func: memcached_return memcached_string_check(memcached_string_st *string, size_t need);
     */
    public int memcached_string_check(memcached_string_st string, NativeLong need);

    /**
     * C func: char *memcached_string_c_copy(memcached_string_st *string)
     */
    public String memcached_string_c_copy(memcached_string_st string);

    /**
     * C func: memcached_return memcached_string_append_character(
     *  memcached_string_st *string,
     *  char character
     * );
     */
    public int memcached_string_append_character(memcached_string_st string, byte character);

    /**
     * C func: memcached_return memcached_string_append(
     *  memcached_string_st *string,
     *  char *value, size_t length
     * );
     */
    public int memcached_string_append(memcached_string_st string, String value, NativeLong length);
    
    /**
     * C func: memcached_return memcached_string_reset(memcached_string_st *string)
     */
    public int memcached_string_reset(memcached_string_st string);

    /**
     * C func: void memcached_string_free(memcached_string_st *string);
     */
    public void memcached_string_free(memcached_string_st string);
}
