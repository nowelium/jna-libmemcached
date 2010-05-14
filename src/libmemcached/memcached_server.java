package libmemcached;

import libmemcached.memcached.memcached_st;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.Structure;

public interface memcached_server {
    
    public static class memcached_server_st extends Structure {
        private static final int NI_MAXHOST = 1025;
        
        // C type: options_struct
        public options_struct options;
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
        // C type: state_struct
        public state_struct state;
        // C type: uint8_t
        public byte major_version;
        // C type: uint8_t
        public byte micro_version;
        // C type: uint8_t
        public byte minor_version;
        // C type: memcached_connection in java:enum:memcached_constants.memcached_connection
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
        /// C type : char[MEMCACHED_MAX_BUFFER]
        public byte[] read_buffer = new byte[memcached_constants.MEMCACHED_MAX_BUFFER];
        /// C type : char[MEMCACHED_MAX_BUFFER]
        public byte[] write_buffer = new byte[memcached_constants.MEMCACHED_MAX_BUFFER];
        /// C type : char[NI_MAXHOST]
        public byte[] hostname = new byte[(NI_MAXHOST)];
        
        public static class options_struct extends Structure {
            public boolean is_allocated;
            public boolean is_initialized;
            public boolean sockaddr_inited;
            public boolean is_shutting_down;
            public options_struct() {
                super();
            }
            public options_struct(boolean is_allocated, boolean is_initialized, boolean sockaddr_inited, boolean is_shutting_down) {
                super();
                this.is_allocated = is_allocated;
                this.is_initialized = is_initialized;
                this.sockaddr_inited = sockaddr_inited;
                this.is_shutting_down = is_shutting_down;
            }
            public static class ByReference extends options_struct implements Structure.ByReference {
                
            }
            public static class ByValue extends options_struct implements Structure.ByValue {
                
            }
        }
        public static class state_struct extends Structure {
            public boolean is_corked;
            public boolean is_dead;
            public state_struct() {
                super();
            }
            public state_struct(boolean is_corked, boolean is_dead) {
                super();
                this.is_corked = is_corked;
                this.is_dead = is_dead;
            }
            public static class ByReference extends state_struct implements Structure.ByReference {
                
            }
            public static class ByValue extends state_struct implements Structure.ByValue {
                
            }
        }
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
}
