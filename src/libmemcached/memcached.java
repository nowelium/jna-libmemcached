package libmemcached;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

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
    sasl {
    
    /**
     * C func: memcached_st* memcached_create(memcached_st* ptr);
     */
    public memcached_st memcached_create(memcached_st ptr);
    
    /**
     * C func: void memcached_free(memcached_st* ptr);
     */
    public void memcached_free(memcached_st ptr);
    
    public memcached_server_st.ByReference memcached_servers_parse(String strings);
    
    public memcached_server_st.ByReference memcached_server_list_append(memcached_server_st ptr, String hostname, int port, IntByReference error);
    
    public void memcached_server_list_free(memcached_server_st ptr);
    
    /**
     * C func: memcached_return memcached_set(
     *     memcached_st* ptr,
     *     const char* key, size_t key_length,
     *     const char* value, size_t value_length,
     *     time_t expiration,
     *     uint32_t flags
     * )
     */
    public int memcached_set(
        memcached_st ptr,
        String key, NativeLong key_length,
        String value, NativeLong value_length,
        int expiration,
        int flags
    );
    
    public static class memcached_st extends Structure {
        // C type: uint8_t
        public byte purging;
        // C type: bool
        public boolean is_allocated;
        // C type: uint8_t
        public byte distribution;
        // C type: uint8_t
        public byte hash;
        // C type: uint32_t
        public int continuum_points_counter;
        // C type: memcached_server_st*
        public memcached_server_st.ByReference hosts;
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
        public int number_of_hosts;
        // C type: uint32_t
        public int cursor_server;
        // C type: int
        public int cached_errno;
        // C type: uint32_t
        public int flags;
        // C type: int32_t
        public int poll_timeout;
        // C type: int32_t
        public int connect_timeout;
        // C type: int32_t
        public int retry_timeout;
        // C type: uint32_t
        public int continuum_count;
        // C type: int
        public int send_size;
        // C type: int
        public int recv_size;
        // C type: void*
        public Pointer user_data;
        // C type: time_t
        public int next_distribution_rebuild;
        // C type: size_t
        public NativeLong prefix_key_length;
        // C type: memcached_hash in java:enum:memcached_hash
        public int hash_continuum;
        // C type: memcached_result_st
        public memcached_result_st result;
        // C type: memcached_continuum_item_st*
        public memcached_continuum_item_st.ByReference continuum;
        // C type: memcached_clone_func
        public memcached_clone_func on_clone;
        // C type: memcached_cleanup_func
        public memcached_cleanup_func on_cleanup;
        // C type: memcached_free_function
        public memcached_free_function call_free;
        // C type: memcached_malloc_function
        public memcached_malloc_function call_malloc;
        // C type: memcached_realloc_function
        public memcached_realloc_function call_realloc;
        // C type: memcached_calloc_function
        public memcached_calloc_function call_calloc;
        // C type: memcached_trigger_key
        public memcached_trigger_key get_key_failure;
        // C type: memcached_trigger_delete_key
        public memcached_trigger_delete_key delete_trigger;
        // C type: char[MEMCACHED_PREFIX_KEY_MAX_SIZE]
        public byte[] prefix_key = new byte[MEMCACHED_VERSION_STRING_LENGTH];
        // C type: uint32_t
        public int number_of_replicas;
        
        public static class ByReference extends memcached_st implements Structure.ByReference {
            
        }
        public static class ByValue extends memcached_st implements Structure.ByValue {
            
        }
    }
    
    public static class memcached_continuum_item_st extends Structure {
        // C type: uint32_t
        public int index;
        // C type: uint32_t
        public int value;
        
        public memcached_continuum_item_st(){
            super();
        }
        public memcached_continuum_item_st(int index, int value){
            super();
            this.index = index;
            this.value = value;
        }
        
        public static class ByReference extends memcached_continuum_item_st implements Structure.ByReference {
            
        }
        public static class ByValue extends memcached_continuum_item_st implements Structure.ByValue {
            
        }
    }
    
}
