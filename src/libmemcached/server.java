package libmemcached;

import libmemcached.memcached.memcached_analysis_st;
import libmemcached.memcached.memcached_st;
import libmemcached.memcached.memcached_stat_st;
import libmemcached.types.memcached_server_function;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

public interface server {
    
    
    public static class memcached_server_st extends Structure {
        private static final int NI_MAXHOST = 1025;
        
        public int number_of_hosts;
        public int cursor_active;
        public int port;
        public int cached_errno;
        public int fd;
        public int io_bytes_sent; /* # bytes sent since last read */
        public int server_failure_counter;
        public int weight;
        public byte major_version;
        public byte micro_version;
        public byte minor_version;
        public int type;
        public Pointer read_ptr;
        public Pointer cached_server_error;
        public NativeLong read_buffer_length;
        public NativeLong read_data_length;
        public NativeLong write_buffer_offset;
        public Pointer address_info;
        public int next_retry;
        public memcached_st.ByReference root;
        public long limit_maxbytes;
        public byte[] read_buffer = new byte[constants.MEMCACHED_MAX_BUFFER];
        public byte[] write_buffer = new byte[constants.MEMCACHED_MAX_BUFFER];
        public byte[] hostname = new byte[NI_MAXHOST];
        
        public static class ByReference extends memcached_server_st implements Structure.ByReference {
            
        }
        public static class ByValue extends memcached_server_st implements Structure.ByValue {
            
        }
    }
    
//    public static class memcached_server_st extends Structure {
//        // C type: bool
//        public boolean is_allocated;
//        // C type: bool
//        public boolean sockaddr_inited;
//        // C type: uint16_t
//        public int count;
//        // C type: unsigned int
//        public int cursor_active;
//        // C type: unsigned int
//        public int port;
//        // C type: int
//        public int cached_errno;
//        // C type: int
//        public int fd;
//        // C type: uint32_t
//        public int io_bytes_sent; /* # bytes sent since last read */
//        // C type: uint32_t
//        public int server_failure_counter;
//        // C type: uint32_t
//        public int weight;
//        // C type: uint8_t
//        public byte major_version;
//        // C type: uint8_t
//        public byte micro_version;
//        // C type: uint8_t
//        public byte minor_version;
//        // C type: memcached_connection in java:enum:memcached_constants.memcached_connection
//        public int type;
//        // C type: char*
//        public String read_ptr;
//        // C type: size_t
//        public NativeLong read_buffer_length;
//        // C type: size_t
//        public NativeLong read_data_length;
//        // C type: size_t
//        public NativeLong write_buffer_offset;
//        // C type : addrinfo*
//        public PointerByReference address_info;
//        // C type: time_t
//        public int next_retry;
//        // C type: memcached_st*
//        public memcached_st.ByReference root;
//        // C type: uint64
//        public long limit_maxbytes;
//        // C type : char[MEMCACHED_MAX_BUFFER]
//        public byte[] read_buffer = new byte[constants.MEMCACHED_MAX_BUFFER];
//        // C type : char[MEMCACHED_MAX_BUFFER]
//        public byte[] write_buffer = new byte[constants.MEMCACHED_MAX_BUFFER];
//        // C type : char[NI_MAXHOST]
//        public byte[] hostname = new byte[constants.MEMCACHED_MAX_HOST_SORT_LENGTH];
//        
//        public static class ByReference extends memcached_server_st implements Structure.ByReference {
//
//        }
//        public static class ByValue extends memcached_server_st implements Structure.ByValue {
//
//        }
//    }
    
    /**
     * C func: memcached_return memcached_server_cursor(
     *  memcached_st *ptr,
     *  memcached_server_function *callback,
     *  void *context,
     *  unsigned int number_of_callbacks);
     */
    public int memcached_server_cursor(
        memcached_st ptr, 
        memcached_server_function callback,
        Pointer context,
        int number_of_callbacks
    );

    /**
     * C func: memcached_server_st *memcached_server_by_key(
     *  memcached_st *ptr,
     *  const char *key,
     *  size_t key_length,
     *  memcached_return *error
     * );
     */
    public memcached_server_st memcached_server_by_key(
        memcached_st ptr,
        String key, 
        NativeLong key_length,
        IntByReference error
    );

    /**
     * C func: memcached_server_st *memcached_server_create(memcached_st *memc, memcached_server_st *ptr)
     */
    public memcached_server_st memcached_server_create(memcached_st memc, memcached_server_st ptr);

    /**
     * C func: memcached_server_st *memcached_server_create_with(
     *  memcached_st *memc,
     *  memcached_server_st *host,
     *  const char *hostname,
     *  unsigned int port,
     *  uint32_t weight,
     *  memcached_connection type
     * );
     */
    public memcached_server_st memcached_server_create_with(
        memcached_st memc,
        memcached_server_st host,
        String hostname,
        int port, 
        int weight,
        int type
    );

    /**
     * C func: void memcached_server_free(memcached_server_st *ptr)
     */
    public void memcached_server_free(memcached_server_st ptr);

    /**
     * C func: memcached_server_st *memcached_server_clone(memcached_server_st *clone, memcached_server_st *ptr);
     */
    public memcached_server_st memcached_server_clone(memcached_server_st clone, memcached_server_st ptr);

    /**
     * C func: memcached_analysis_st *memcached_analyze(
     *  memcached_st *memc,
     *  memcached_stat_st *stat,
     *  memcached_return *error
     * );
     */
    public memcached_analysis_st memcached_analyze(
        memcached_st memc,
        memcached_stat_st stat,
        IntByReference error
    );

    /**
     * C func: memcached_return memcached_server_remove(memcached_server_st *st_ptr)
     */
    public int memcached_server_remove(memcached_server_st st_ptr);
}
