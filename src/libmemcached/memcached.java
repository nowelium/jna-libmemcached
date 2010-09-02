package libmemcached;

import libmemcached.util.pool;

import com.sun.jna.Pointer;
import com.sun.jna.PointerType;

public interface memcached extends
    visibility,
    configure,
    constants,
    types,
    string,
    stats,
    allocators,
    analyze,
    auto,
    behavior,
    callback,
    delete,
    dump,
    fetch,
    flush,
    flush_buffers,
    get,
    hash,
    parse,
    quit,
    result,
    server,
    server_list,
    storage,
    strerror,
    verbosity,
    version,
    sasl,
    pool {
    
    /*
    public static class memcached_st extends Structure {
        // C type: state
        public state_struct state;
        // C type: flags
        public flags_struct flags;
        // C type: memcached_server_distribution_t in java:enum:constants.memcached_server_distribution_t
        public int distribution;
        // C type: hashkit_st
        public hashkit_st hashkit;
        // C type: uint32_t
        public int continuum_points_counter; // Ketama
        // C type: uint32_t
        public int number_of_hosts;
        // C type: memcached_server_st*
        public memcached_server_st.ByReference servers;
        // C type: memcached_server_st*
        public memcached_server_st.ByReference last_disconnected_server;
        // C type: int32_t
        public int snd_timeout;
        // C type: int32_t
        public int rcv_timeout;
        // C type: uint32_t
        public int server_failure_limit;
        // C type: uint32_t
        public int io_msg_watermark;
        // C type: uint32_t
        public int io_bytes_watermark;
        // C type: uint32_t
        public int io_key_prefetch;
        // C type: uint32_t
        public int tcp_keepidle;
        // C type: int
        public int cached_errno;
        // C type: int32_t
        public int poll_timeout;
        // C type: int32_t
        public int connect_timeout;
        // C type: int32_t
        public int retry_timeout;
        // C type: uint32_t
        public int continuum_count; // Ketama
        // C type: int
        public int send_size;
        // C type: int
        public int recv_size;
        // C type: void*
        public Pointer user_data;
        // C type: time_t
        public time_t next_distribution_rebuild; // Ketama
        // C type: size_t
        public size_t prefix_key_length;
        // C type: uint32_t
        public int number_of_replicas;
        // C type: hashkit_st
        public hashkit_st distribution_hashkit;
        // C type: memcached_result_st
        public memcached_result_st result;
        // C type: memcached_continuum_item_st*
        public memcached_continuum_item_st.ByReference continuum; // Ketama
        // C type: _allocators_st
        public allocators_struct allocators_st;
        // C type: memcached_clone_fn
        public memcached_clone_function on_clone;
        // C type: memcached_cleanup_fn
        public memcached_cleanup_function on_cleanup;
        // C type: memcached_trigger_key_fn
        public memcached_trigger_key_function get_key_failure;
        // C type: memcached_trigger_delete_key_fn
        public memcached_trigger_delete_key_function delete_trigger;
        // C type: memcached_callback_st*
        public memcached_callback_st.ByReference callbacks;
        // C type: memcached_sasl_st*
        public memcached_sasl_st.ByReference sasl;
        // C type: char[MEMCACHED_PREFIX_KEY_MAX_SIZE]
        public byte[] prefix_key = new byte[constants.MEMCACHED_PREFIX_KEY_MAX_SIZE];
        // C type: options
        public options_struct options;
        
        public memcached_st(){
            super();
            setAutoSynch(false);
        }
        
        public static class ByReference extends memcached_st implements Structure.ByReference {
            
        }
        public static class ByValue extends memcached_st implements Structure.ByValue {
            
        }
        
        public static class state_struct extends Structure {
            // C type: bool
            public boolean is_purging;
            // C type: bool
            public boolean is_processing_input;
            // C type: bool
            public boolean is_time_for_rebuild;
            
            public state_struct(){
                super();
                setAutoSynch(false);
            }
            
            public static class ByReference extends state_struct implements Structure.ByReference {
                
            }
            public static class ByValue extends state_struct implements Structure.ByValue {
                
            }
        }
        public static class flags_struct extends Structure {
            // C type: bool
            public boolean auto_eject_hosts;
            // C type: bool
            public boolean binary_protocol;
            // C type: bool
            public boolean buffer_requests;
            // C type: bool
            public boolean cork;
            // C type: bool
            public boolean hash_with_prefix_key;
            // C type: bool
            public boolean ketama_weighted;
            // C type: bool
            public boolean no_block;
            // C type: bool
            public boolean no_reply;
            // C type: bool
            public boolean randomize_replica_read;
            // C type: bool
            public boolean reuse_memory;
            // C type: bool
            public boolean support_cas;
            // C type: bool
            public boolean tcp_nodelay;
            // C type: bool
            public boolean use_cache_lookups;
            // C type: bool
            public boolean use_sort_hosts;
            // C type: bool
            public boolean use_udp;
            // C type: bool
            public boolean verify_key;
            // C type: bool
            public boolean tcp_keepalive;
            
            public flags_struct(){
                super();
                setAutoSynch(false);
            }
            
            public static class ByReference extends flags_struct implements Structure.ByReference {
                
            }
            public static class ByValue extends flags_struct implements Structure.ByValue {
                
            }
        }
        
        public static class allocators_struct extends Structure {
            // C type: memcached_calloc_fn
            public memcached_calloc_function calloc;
            // C type: memcached_free_fn
            public memcached_free_function free;
            // C type: memcached_malloc_fn
            public memcached_malloc_function malloc;
            // C type: memcached_realloc_fn
            public memcached_realloc_function realloc;
            // C type: void*
            public Pointer context;
            
            public allocators_struct(){
                super();
                setAutoSynch(false);
            }
            
            public static class ByReference extends allocators_struct implements Structure.ByReference {
                
            }
            public static class ByValue extends allocators_struct implements Structure.ByValue {
                
            }
        }
        public static class options_struct extends Structure {
            // C type: bool
            public boolean is_allocated;
            
            public options_struct(){
                super();
                setAutoSynch(false);
            }
            
            public static class ByReference extends options_struct implements Structure.ByReference {
                
            };
            public static class ByValue extends options_struct implements Structure.ByValue {
                
            };
        }
    }
    */
    
    /*
    public static class memcached_continuum_item_st extends Structure {
        // C type: uint32_t
        public int index;
        // C type: uint32_t
        public int value;
        
        public memcached_continuum_item_st(){
            super();
            setAutoSynch(false);
        }
        
        public static class ByReference extends memcached_continuum_item_st implements Structure.ByReference {
            
        }
        public static class ByValue extends memcached_continuum_item_st implements Structure.ByValue {
            
        }
    }
    */

    public static class memcached_st extends PointerType {
        // ignore
    }
    
    public static class memcached_continuum_item_st extends PointerType {
        // ignore
    }
    
    /**
     * C func: void memcached_servers_reset(memcached_st *ptr)
     */
    public void memcached_servers_reset(memcached_st ptr);

    /**
     * C func: memcached_st *memcached_create(memcached_st *ptr);
     */
    public memcached_st memcached_create(memcached_st ptr);

    /**
     * C func: void memcached_free(memcached_st *ptr);
     */
    public void memcached_free(memcached_st ptr);

    /**
     * C func: void memcached_reset_last_disconnected_server(memcached_st *ptr)
     */
    public void memcached_reset_last_disconnected_server(memcached_st ptr);

    /**
     * C func: memcached_st *memcached_clone(memcached_st *clone, const memcached_st *ptr)
     */
    public memcached_st memcached_clone(memcached_st clone, memcached_st ptr);

    /**
     * C func: void *memcached_get_user_data(const memcached_st *ptr)
     */
    public void memcached_get_user_data(memcached_st ptr);

    /**
     * C func: void *memcached_set_user_data(memcached_st *ptr, void *data)
     */
    public void memcached_set_user_data(memcached_st ptr, Pointer data);

    /**
     * C func: memcached_return_t memcached_push(memcached_st *destination, const memcached_st *source)
     */
    public int memcached_push(memcached_st destination, memcached_st source);

    /**
     * C func: memcached_server_instance_st memcached_server_instance_by_position(
     *  const memcached_st *ptr,
     *  uint32_t server_key
     * )
     */
    public memcached_server_instance_st memcached_server_instance_by_position(
        memcached_st ptr,
        int server_key
    );

    /**
     * C func: uint32_t memcached_server_count(const memcached_st *)
     */
    public int memcached_server_count(memcached_st ptr);
    
}
