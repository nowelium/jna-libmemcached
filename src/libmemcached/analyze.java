package libmemcached;

import libmemcached.memcached.memcached_st;
import libmemcached.stats.memcached_stat_st;

import com.sun.jna.PointerType;
import com.sun.jna.ptr.IntByReference;

public interface analyze {

    /*
    public static class memcached_analysis_st extends Structure {
        // C type: memcached_st*
        public memcached_st.ByReference root;
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
        
        public memcached_analysis_st(){
            super();
            setAutoSynch(false);
        }
        
        public static class ByReference extends memcached_analysis_st implements Structure.ByReference {
            
        }
        public static class ByValue extends memcached_analysis_st implements Structure.ByValue {
            
        }
    }
    */
    
    public static class memcached_analysis_st extends PointerType {
        // ignore
    }
    
    /**
     * C func: memcached_analysis_st *memcached_analyze(
     *  memcached_st *memc,
     *  memcached_stat_st *memc_stat,
     *  memcached_return_t *error
     * );
     */
    public memcached_analysis_st memcached_analyze(
        memcached_st memc,
        memcached_stat_st memc_stat,
        IntByReference error
    );

    /**
     * C func: void memcached_analyze_free(memcached_analysis_st *);
     */
    public void memcached_analyze_free(memcached_analysis_st ptr);

}
