package libmemcached;

import libmemcached.memcached.memcached_st;

public interface flush_buffers {
    /**
     * C func: memcached_return_t memcached_flush_buffers(memcached_st *mem)
     */
    public int memcached_flush_buffers(memcached_st mem);
}
