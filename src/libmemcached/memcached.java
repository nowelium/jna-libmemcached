package libmemcached;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

public interface memcached extends
    configure,
    constants,
    types,
    get,
    server,
    string,
    result,
    stroage {
    
    public static final int MEMCACHED_VERSION_STRING_LENGTH = 24;
    
    public static final String LIBMEMCACHED_VERSION_STRING = "0.31";
    
    public static class memcached_analysis_st extends Structure {
        // C type: uint32_t
        public int average_item_size;
        // C type: uint32_t
        public int longest_uptime;
        // C type: uint32_t
        public int least_free_server;
        // C type: uint32_t
        public int most_consumed_server;
        // C type: uint32_t
        public int oldest_server;
        // C type: double
        public double pool_hit_ratio;
        // C type: uint64_t
        public long most_used_bytes;
        // C type: uint64_t
        public long least_remaining_bytes;
        
        public static class ByReference extends memcached_analysis_st implements Structure.ByReference {
            
        }
        public static class ByValue extends memcached_analysis_st implements Structure.ByValue {
            
        }
    }
    
    public static class memcached_stat_st extends Structure {
        // C type: uint32_t
        public int connection_structures;
        // C type: uint32_t
        public int curr_connections;
        // C type: uint32_t
        public int curr_items;
        // C type: uint32_t
        public int pid;
        // C type: uint32_t
        public int pointer_size;
        // C type: uint32_t
        public int rusage_system_microseconds;
        // C type: uint32_t
        public int rusage_system_seconds;
        // C type: uint32_t
        public int rusage_user_microseconds;
        // C type: uint32_t
        public int rusage_user_seconds;
        // C type: uint32_t
        public int threads;
        // C type: uint32_t
        public int time;
        // C type: uint32_t
        public int total_connections;
        // C type: uint32_t
        public int total_items;
        // C type: uint32_t
        public int uptime;
        // C type: uint64_t
        public long bytes;
        // C type: uint64_t
        public long bytes_read;
        // C type: uint64_t
        public long bytes_written;
        // C type: uint64_t
        public long cmd_get;
        // C type: uint64_t
        public long cmd_set;
        // C type: uint64_t
        public long evictions;
        // C type: uint64_t
        public long get_hits;
        // C type: uint64_t
        public long get_misses;
        // C type: uint64_t
        public long limit_maxbytes;
        // C type: char[MEMCACHED_VERSION_STRING_LENGTH]
        public byte[] version = new byte[MEMCACHED_VERSION_STRING_LENGTH];
        
        public static class ByReference extends memcached_stat_st implements Structure.ByReference {
            
        }
        public static class ByValue extends memcached_stat_st implements Structure.ByValue {
            
        }
    }
    
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
    
    /**
     * C func: memcached_return memcached_version(memcached_st* ptr);
     */
    public int memcached_version(memcached_st ptr);
    
    /**
     * C func: const char* memcached_lib_version(void);
     */
    public String memcached_lib_version();
    
    /**
     * C func: memcached_st* memcached_create(memcached_st* ptr);
     */
    public memcached_st memcached_create(memcached_st ptr);
    
    /**
     * C func: void memcached_free(memcached_st* ptr);
     */
    public void memcached_free(memcached_st ptr);
    
    /**
     * C func: memcached_st *memcached_clone(memcached_st *clone, memcached_st *ptr)
     */
    public memcached_st memcached_clone(memcached_st clone, memcached_st ptr);
    
    /**
     * C func: memcached_return memcached_delete(
     *  memcached_st *ptr,
     *  const char *key,
     *  size_t key_length,
     *  time_t expiration
     * )
     */
    public int memcached_delete(
        memcached_st ptr,
        String key,
        NativeLong key_length,
        int expiration
    );
    
    /**
     * C func: memcached_return memcached_increment(
     *  memcached_st *ptr,
     *  const char *key,
     *  size_t key_length,
     *  uint32_t offset,
     *  uint64_t *value
     * );
     */
    public int memcached_increment(
        memcached_st ptr, 
        String key,
        NativeLong key_length,
        int offset,
        long value
    );
    
    /**
     * C func: memcached_return memcached_decrement(
     *  memcached_st *ptr,
     *  const char *key,
     *  size_t key_length,
     *  uint32_t offset,
     *  uint64_t *value
     * );
     */
    public int memcached_decrement(
        memcached_st ptr, 
        String key,
        NativeLong key_length,
        int offset,
        long value
    );
    
    /**
     * C func: memcached_return memcached_increment_with_initial(
     *  memcached_st *ptr,
     *  const char *key,
     *  size_t key_length,
     *  uint64_t offset,
     *  uint64_t initial,
     *  time_t expiration,
     *  uint64_t *value
     * );
     */
    public int memcached_increment_with_initial(
        memcached_st ptr,
        String key,
        NativeLong key_length,
        long offset,
        long initial,
        int expiration,
        long value
    );
    
    /**
     * C func: memcached_return memcached_decrement_with_initial(
     *  memcached_st *ptr,
     *  const char *key,
     *  size_t key_length,
     *  uint64_t offset,
     *  uint64_t initial,
     *  time_t expiration,
     *  uint64_t *value
     * );
     */
    public int memcached_decrement_with_initial(
        memcached_st ptr,
        String key,
        NativeLong key_length,
        long offset,
        long initial,
        int expiration,
        long value
    );
    
    /**
     * C func: void memcached_stat_free(memcached_st *, memcached_stat_st *);
     */
    public void memcached_stat_free(memcached_st arg0, memcached_stat_st arg2);
    
    /**
     * C func: memcached_stat_st *memcached_stat(memcached_st *ptr, char *args, memcached_return *error);
     */
    public memcached_stat_st memcached_stat(memcached_st ptr, String args, IntByReference error);
    
    /**
     * C func: memcached_return memcached_stat_servername(
     *  memcached_stat_st *stat,
     *  char *args, 
     *  char *hostname,
     *  unsigned int port
     * );
     */
    public int memcached_stat_servername(memcached_stat_st stat, String args, String hostname, int port);
    
    /**
     * C func: memcached_return memcached_flush(memcached_st *ptr, time_t expiration);
     */
    public int memcached_flush(memcached_st ptr, int expiration);
    
    /**
     * C func: memcached_return memcached_verbosity(memcached_st *ptr, unsigned int verbosity);
     */
    public int memcached_verbosity(memcached_st ptr, int verbosity);
    
    /**
     * C func: void memcached_quit(memcached_st *ptr);
     */
    public void memcached_quit(memcached_st ptr);
    
    /**
     * C func: char* memcached_strerror(
     *  memcached_st* ptr,
     *  memcached_return rc
     * );
     */
    public String memcached_strerror(memcached_st ptr, int rc);
    
    /**
     * C func: memcached_return memcached_behavior_set(
     *   memcached_st* ptr,
     *   memcached_behavior flag,
     *   uint64_t data
     * );
     */
    public int memcached_behavior_set(memcached_st ptr, int flag, int data);
    
    /**
     * C func: uint64_t memcached_behavior_get(memcached_st *ptr, memcached_behavior flag)
     */
    public long memcached_behavior_get(memcached_st ptr, int flag);
    
    /**
     * C func: uint32_t memcached_generate_hash_value(const char *key, size_t key_length, memcached_hash hash_algorithm)
     */
    public int memcached_generate_hash_value(String key, NativeLong key_length, int hash_algorithm);
    
    /**
     * C func: uint32_t memcached_generate_hash(memcached_st *ptr, const char *key, size_t key_length)
     */
    public int memcached_generate_hash(memcached_st ptr, String key, NativeLong key_length);
    
    /**
     * C func: memcached_return memcached_flush_buffers(memcached_st *mem);
     */
    public int memcached_flush_buffers(memcached_st mem);

    /**
     * C func: memcached_return memcached_server_add_udp(
     *   memcached_st* ptr,
     *   const char* hostname,
     *   unsigned int port
     * );
     */
    public int memcached_server_add_udp(memcached_st ptr, String hostname, int port);
    
    /**
     * C func: memcached_return memcached_server_add_unix_socket(
     *   memcached_st* ptr,
     *   const char* filename
     * );
     */
    public int memcached_server_add_unix_socket(memcached_st ptr, String filename);
    
    /**
     * C func: memcached_return memcached_server_add(
     *   memcached_st* ptr,
     *   const char* hostname,
     *   unsigned int port
     * );
     */
    public int memcached_server_add(memcached_st ptr, String hostname, int port);
    
    /**
     * C func: memcached_return memcached_server_add_udp_with_weight(
     *  memcached_st *ptr, 
     *  const char *hostname,
     *  unsigned int port,
     *  uint32_t weight
     * )
     */
    public int memcached_server_add_udp_with_weight(
        memcached_st ptr,
        String hostname,
        int port,
        int weight
    );
    
    /**
     * C func: memcached_return memcached_server_add_unix_socket_with_weight(
     *  memcached_st *ptr,
     *  const char *filename,
     *  uint32_t weight
     * );
     */
    public int memcached_server_add_unix_socket_with_weight(
        memcached_st ptr, 
        String filename,
        int weight
    );
    
    /**
     * C func: memcached_return memcached_server_add_with_weight(
     *  memcached_st *ptr,
     *  const char *hostname,
     *  unsigned int port,
     *  uint32_t weight
     * );
     */
    public int memcached_server_add_with_weight(
        memcached_st ptr,
        String hostname, 
        int port,
        int weight
    );
    
    /**
     * C func: void memcached_server_list_free(memcached_server_st *ptr);
     */
    public void memcached_server_list_free(memcached_server_st ptr);
    
    /**
     * C func: memcached_return memcached_server_push(memcached_st *ptr, memcached_server_st *list)
     */
    public int memcached_server_push(memcached_st ptr, memcached_server_st list);
    
    /**
     * C func: memcached_server_st *memcached_server_list_append(
     *  memcached_server_st *ptr,
     *  const char *hostname,
     *  unsigned int port,
     *  memcached_return *error
     * );
     */
    public memcached_server_st memcached_server_list_append(
        memcached_server_st ptr, 
        String hostname, 
        int port, 
        IntByReference error
    );
    
    /**
     * C func: memcached_server_st *memcached_server_list_append_with_weight(
     *  memcached_server_st *ptr,
     *  const char *hostname,
     *  unsigned int port,
     *  uint32_t weight,
     *  memcached_return *error
     * );
     */
    public memcached_server_st memcached_server_list_append_with_weight(
        memcached_server_st ptr, 
        String hostname, 
        int port, 
        int weight,
        IntByReference error
    );
    
    /**
     * C func: unsigned int memcached_server_list_count(memcached_server_st *ptr);
     */
    public int memcached_server_list_count(memcached_server_st ptr);
    
    /**
     * C func: memcached_server_st *memcached_servers_parse(const char *server_strings)
     */
    public memcached_server_st memcached_servers_parse(String server_strings);
    
    /**
     * C func: char *memcached_stat_get_value(
     *  memcached_st *ptr,
     *  memcached_stat_st *stat,
     *  const char *key,
     *  memcached_return *error
     * )
     */
    public String memcached_stat_get_value(
        memcached_st ptr,
        memcached_stat_st stat,
        String key,
        IntByReference error
    );
    
    /**
     * C func: char ** memcached_stat_get_keys(
     *  memcached_st *ptr,
     *  memcached_stat_st *stat,
     *  memcached_return *error
     * );
     */
    public String[] memcached_stat_get_keys(
        memcached_st ptr,
        memcached_stat_st stat, 
        int error
    );
    
    /**
     * C func: memcached_return memcached_delete_by_key(
     *  memcached_st *ptr,
     *  const char *master_key,
     *  size_t master_key_length,
     *  const char *key,
     *  size_t key_length,
     *  time_t expiration
     * );
     */
    public int memcached_delete_by_key(
        memcached_st ptr, 
        String master_key,
        NativeLong master_key_length,
        String key,
        NativeLong key_length,
        int expiration
    );
    
    /**
     * C func: memcached_return memcached_fetch_execute(
     *  memcached_st *ptr,
     *  memcached_execute_function *callback,
     *  void *context,
     *  unsigned int number_of_callbacks
     * );
     */
    public int memcached_fetch_execute(
        memcached_st ptr, 
        memcached_execute_function callback,
        Pointer context,
        int number_of_callbacks
    );
    
    /**
     * C func: memcached_return memcached_callback_set(
     *  memcached_st *ptr,
     *  memcached_callback flag,
     *  void *data
     * );
     */
    public int memcached_callback_set(
        memcached_st ptr, 
        int flag, 
        Pointer data
    );
    
    /**
     * C func: void *memcached_callback_get(
     *  memcached_st *ptr,
     *  memcached_callback flag,
     *  memcached_return *error
     * );
     */
    public void memcached_callback_get(
        memcached_st ptr, 
        int flag,
        IntByReference error
    );
    
    /**
     * C func: memcached_return memcached_dump(memcached_st *ptr, memcached_dump_func *function, void *context, uint32_t number_of_callbacks)
     */
    public int memcached_dump(
        memcached_st ptr,
        memcached_dump_function function,
        Pointer context,
        int number_of_callbacks
    );
    
    /**
     * C func: memcached_return memcached_set_memory_allocators(
     *  memcached_st *ptr,
     *  memcached_malloc_function mem_malloc,
     *  memcached_free_function mem_free,
     *  memcached_realloc_function mem_realloc,
     *  memcached_calloc_function mem_calloc
     * );
     */
    public int memcached_set_memory_allocators(
        memcached_st ptr,
        memcached_malloc_function mem_malloc,
        memcached_free_function mem_free,
        memcached_realloc_function mem_realloc,
        memcached_calloc_function mem_calloc
    );
    
    /**
     * C func: void memcached_get_memory_allocators(
     *  memcached_st *ptr,
     *  memcached_malloc_function *mem_malloc,
     *  memcached_free_function *mem_free,
     *  memcached_realloc_function *mem_realloc,
     *  memcached_calloc_function *mem_calloc
     * );
     */
    public void memcached_get_memory_allocators(
        memcached_st ptr,
        memcached_malloc_function mem_malloc,
        memcached_free_function mem_free,
        memcached_realloc_function mem_realloc,
        memcached_calloc_function mem_calloc
    );
    
    /**
     * C func: void *memcached_get_user_data(memcached_st *ptr);
     */
    public void memcached_get_user_data(memcached_st ptr);
    
    /**
     * C func: void *memcached_set_user_data(memcached_st *ptr, void *data);
     */
    public void memcached_set_user_data(memcached_st ptr, Pointer data);
    
    /**
     * C func: memcached_return run_distribution(memcached_st *ptr);
     */
    public int run_distribution(memcached_st ptr);
    
}
