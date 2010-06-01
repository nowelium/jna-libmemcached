package libmemcached.util;

import libmemcached.memcached.memcached_st;

import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

public interface pool {
    
    public static class memcached_pool_st extends PointerType {
        public memcached_pool_st(){
            super();
        }
        public memcached_pool_st(Pointer pointer){
            super(pointer);
        }
    }

    /**
     * C func: memcached_pool_st *memcached_pool_create(
     *  memcached_st* mmc,
     *  uint32_t initial,
     *  uint32_t max
     * );
     */
    public memcached_pool_st memcached_pool_create(
        memcached_st mmc,
        int initial,
        int max
    );

    /**
     * C func: memcached_st* memcached_pool_destroy(memcached_pool_st* pool)
     */
    public memcached_st memcached_pool_destroy(memcached_pool_st pool);

    /**
     * C func: memcached_st* memcached_pool_pop(
     *  memcached_pool_st* pool,
     *  bool block,
     *  memcached_return_t* rc
     * );
     */
    public memcached_st memcached_pool_pop(
        memcached_pool_st pool,
        boolean block,
        IntByReference rc
    );
    
    /**
     * C func: memcached_return_t memcached_pool_push(
     *  memcached_pool_st* pool,
     *  memcached_st* mmc
     * );
     */
    public int memcached_pool_push(
        memcached_pool_st pool,
        memcached_st mmc
    );

    /**
     * C func: memcached_return_t memcached_pool_behavior_set(
     *  memcached_pool_st *ptr,
     *  memcached_behavior_t flag,
     *  uint64_t data
     * );
     */
    public int memcached_pool_behavior_set(
        memcached_pool_st ptr,
        int flag,
        long data
    );

    /**
     * C func: memcached_return_t memcached_pool_behavior_get(
     *  memcached_pool_st *ptr,
     *  memcached_behavior_t flag,
     *  uint64_t *value
     * );
     */
    public int memcached_pool_behavior_get(
        memcached_pool_st ptr,
        int flag,
        LongByReference value
    );

}
