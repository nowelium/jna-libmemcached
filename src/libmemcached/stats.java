package libmemcached;

import libmemcached.memcached.memcached_st;

import com.sun.jna.PointerType;
import com.sun.jna.ptr.IntByReference;

public interface stats {
    
    /*
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
        // C type: char version[MEMCACHED_VERSION_STRING_LENGTH]
        public byte[] version = new byte[constants.MEMCACHED_VERSION_STRING_LENGTH];
        // C type: memcached_st*
        public memcached_st.ByReference root;
        
        public memcached_stat_st(){
            super();
            setAutoSynch(false);
        }
        
        public static class ByReference extends memcached_stat_st implements Structure.ByReference {

        }
        public static class ByValue extends memcached_stat_st implements Structure.ByValue {

        }
    }
    */
    
    public static class memcached_stat_st extends PointerType {
        // ignore
    }
    
    /**
     * C func: void memcached_stat_free(const memcached_st *, memcached_stat_st *)
     */
    public void memcached_stat_free(memcached_st ptr0, memcached_stat_st ptr1);

    /**
     * C func: memcached_stat_st *memcached_stat(memcached_st *ptr, char *args, memcached_return_t *error);
     */
    public memcached_stat_st memcached_stat(memcached_st ptr, String args, IntByReference error);

    /**
     * C func: memcached_return_t memcached_stat_servername(
     *  memcached_stat_st *memc_stat,
     *  char *args,
     *  const char *hostname,
     *  in_port_t port
     * );
     */
    public int memcached_stat_servername(
        memcached_stat_st memc_stat,
        String args,
        String hostname,
        int port
    );

    /**
     * C func: char *memcached_stat_get_value(
     *  const memcached_st *ptr,
     *  memcached_stat_st *memc_stat,
     *  const char *key,
     *  memcached_return_t *error
     * );
     */
    public String memcached_stat_get_value(
        memcached_st ptr,
        memcached_stat_st memc_stat,
        String key,
        IntByReference error
    );

    /**
     * C func: char ** memcached_stat_get_keys(
     *  const memcached_st *ptr,
     *  memcached_stat_st *memc_stat,
     *  memcached_return_t *error
     * );
     */
    public String[] memcached_stat_get_keys(
        memcached_st ptr,
        memcached_stat_st memc_stat,
        IntByReference error
    );

}
