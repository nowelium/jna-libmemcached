package libmemcached;

import libmemcached.memcached.memcached_st;
import libmemcached.types.memcached_server_function;
import libmemcached.types.memcached_server_instance_st;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

public interface server {
    public static class memcached_server_st extends Structure {
        private static final int NI_MAXHOST = 1025;
        
        public static class options extends Structure {
            // C type: bool
            public boolean is_allocated;
            // C type: bool
            public boolean is_initialized;
            // C type: bool
            public boolean sockaddr_inited;
            // C type: bool
            public boolean is_shutting_down;
            public options() {
                super();
            }
            public options(boolean is_allocated, boolean is_initialized, boolean sockaddr_inited, boolean is_shutting_down) {
                super();
                this.is_allocated = is_allocated;
                this.is_initialized = is_initialized;
                this.sockaddr_inited = sockaddr_inited;
                this.is_shutting_down = is_shutting_down;
            }
            public static class ByReference extends options implements Structure.ByReference {
                
            }
            public static class ByValue extends options implements Structure.ByValue {
                
            }
        }
        
        public static class state extends Structure {
            // C type: bool
            public boolean is_corked;
            // C type: bool
            public boolean is_dead;
            public state() {
                super();
            }
            public state(boolean is_corked, boolean is_dead) {
                super();
                this.is_corked = is_corked;
                this.is_dead = is_dead;
            }
            public static class ByReference extends state implements Structure.ByReference {
                
            }
            public static class ByValue extends state implements Structure.ByValue {
                
            }
        }
        
        public static class io_wait_count extends Structure {
            // C type: uint32_t
            public int read;
            // C type: uint32_t
            public int write;
            public io_wait_count() {
                super();
            }
            public io_wait_count(int read, int write) {
                super();
                this.read = read;
                this.write = write;
            }
            public static class ByReference extends io_wait_count implements Structure.ByReference {
                
            }
            public static class ByValue extends io_wait_count implements Structure.ByValue {
                
            }
        }
        
        // C type: options
        public options options;
        // C type: uint32_t
        public int number_of_hosts;
        // C type: uint32_t
        public int cursor_active;
        // C type: in_port_t(uint16_t)
        public int port;
        // C type: int
        public int cached_errno;
        // C type: fd
        public int fd;
        // C type: uint32_t
        public int io_bytes_sent;
        // C type: uint32_t
        public int server_failure_counter;
        // C type: uint32_t
        public int weight;
        // C type: state
        public state state;
        // C type: io_wait_count
        public io_wait_count io_wait_count;
        // C type: uint8_t
        public byte major_version;
        // C type: uint8_t
        public byte micro_version;
        // C type: uint8_t
        public byte minor_version;
        // C type: memcached_connection_t in java:enum:constants.memcached_connection_t
        public int type;
        // C type: char*
        public Pointer read_ptr;
        // C type: char*
        public Pointer cached_server_error;
        // C type: size_t
        public NativeLong read_buffer_length;
        // C type: size_t
        public NativeLong read_data_length;
        // C type: size_t
        public NativeLong write_buffer_offset;
        // C type : addrinfo*
        public addrinfo address_info;
        // C type: time_t
        public int next_retry;
        // C type: memcached_st*
        public memcached_st.ByReference root;
        // C type: uint64
        public long limit_maxbytes;
        // C type : char[MEMCACHED_MAX_BUFFER]
        public byte[] read_buffer = new byte[constants.MEMCACHED_MAX_BUFFER];
        // C type : char[MEMCACHED_MAX_BUFFER]
        public byte[] write_buffer = new byte[constants.MEMCACHED_MAX_BUFFER];
        // C type : char[NI_MAXHOST]
        public byte[] hostname = new byte[(NI_MAXHOST)];
        
        public static class ByReference extends memcached_server_st implements Structure.ByReference {

        }
        public static class ByValue extends memcached_server_st implements Structure.ByValue {

        }
    }
    
    public static class addrinfo extends PointerType {
        public addrinfo(){
            super();
        }
        public addrinfo(Pointer pointer){
            super(pointer);
        }
    }
    
    /**
     * C func: memcached_return_t memcached_server_cursor(
     *  const memcached_st *ptr,
     *  const memcached_server_fn *callback,
     *  void *context,
     *  uint32_t number_of_callbacks
     * );
     * @return
     */
    int memcached_server_cursor(
        memcached_st ptr,
        memcached_server_function callback,
        Pointer context,
        int number_of_callbacks
    );

    /**
     * C func: memcached_server_instance_st memcached_server_by_key(
     *  const memcached_st *ptr,
     *  const char *key,
     *  size_t key_length,
     *  memcached_return_t *error
     * );
     */
    memcached_server_instance_st memcached_server_by_key(
        memcached_st ptr,
        String key,
        NativeLong key_length,
        IntByReference error
    );

    /**
     * C func: void memcached_server_error_reset(memcached_server_st *ptr)
     */
    void memcached_server_error_reset(memcached_server_st ptr);

    /**
     * C func: void memcached_server_free(memcached_server_st *ptr)
     */
    void memcached_server_free(memcached_server_st ptr);

    /**
     * C func: memcached_server_st *memcached_server_clone(
     *  memcached_server_st *destination,
     *  const memcached_server_st *source
     * );
     */
    memcached_server_st memcached_server_clone(
        memcached_server_st destination,
        memcached_server_st source
    );

    /**
     * C func: memcached_server_instance_st memcached_server_get_last_disconnect(const memcached_st *ptr);
     */
    memcached_server_instance_st memcached_server_get_last_disconnect(memcached_st ptr);

    /**
     * C func: memcached_return_t memcached_server_add_udp(
     *  memcached_st *ptr,
     *  const char *hostname,
     *  in_port_t port
     * );
     */
    int memcached_server_add_udp(
        memcached_st ptr,
        String hostname,
        int port
    );
    
    /**
     * C func: memcached_return_t memcached_server_add_unix_socket(
     *  memcached_st *ptr,
     *  const char *filename
     * );
     */
    int memcached_server_add_unix_socket(
        memcached_st ptr,
        String filename
    );
    
    /**
     * C func: memcached_return_t memcached_server_add(
     *  memcached_st *ptr,
     *  const char *hostname,
     *  in_port_t port
     * );
     */
    int memcached_server_add(
        memcached_st ptr,
        String hostname,
        int port
    );

    /**
     * C func: memcached_return_t memcached_server_add_udp_with_weight(
     *  memcached_st *ptr,
     *  const char *hostname,
     *  in_port_t port,
     *  uint32_t weight
     * );
     */
    int memcached_server_add_udp_with_weight(
        memcached_st ptr,
        String hostname,
        int port,
        int weight
    );

    /**
     * C func: memcached_return_t memcached_server_add_unix_socket_with_weight(
     *  memcached_st *ptr,
     *  const char *filename,
     *  uint32_t weight
     * );
     */
    int memcached_server_add_unix_socket_with_weight(
        memcached_st ptr,
        String filename,
        int weight
    );
    
    /**
     * C func: memcached_return_t memcached_server_add_with_weight(
     *  memcached_st *ptr,
     *  const char *hostname,
     *  in_port_t port,
     *  uint32_t weight
     * );
     */
    int memcached_server_add_with_weight(
        memcached_st ptr,
        String hostname,
        int port,
        int weight
    );

    /**
     * C func: uint32_t memcached_server_response_count(memcached_server_instance_st self);
     */
    int memcached_server_response_count(memcached_server_instance_st self);

    /**
     * C func: const char *memcached_server_name(memcached_server_instance_st self);
     */
    String memcached_server_name(memcached_server_instance_st self);

    /**
     * C func: in_port_t memcached_server_port(memcached_server_instance_st self)
     */
    int memcached_server_port(memcached_server_instance_st self);

    /**
     * C func: const char *memcached_server_error(memcached_server_instance_st ptr)
     */
    String memcached_server_error(memcached_server_instance_st ptr);
}
