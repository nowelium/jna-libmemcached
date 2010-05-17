package libmemcached;

import com.sun.jna.ptr.IntByReference;

import libmemcached.memcached.memcached_st;
import libmemcached.server.memcached_server_st;
import libmemcached.types.memcached_server_list_st;

public interface server_list {
    /**
     * C func: void memcached_server_list_free(memcached_server_list_st ptr)
     */
    public void memcached_server_list_free(memcached_server_list_st ptr);

    /**
     * C func: memcached_return_t memcached_server_push(memcached_st *ptr, const memcached_server_list_st list)
     * @return
     */
    public int memcached_server_push(memcached_st ptr, memcached_server_list_st list);

    /**
     * C func: memcached_server_list_st memcached_server_list_append(
     *  memcached_server_list_st ptr,
     *  String hostname,
     *  in_port_t port,
     *  memcached_return_t *error
     * );
     */
    public memcached_server_list_st memcached_server_list_append(
        memcached_server_list_st ptr,
        String hostname,
        int port,
        IntByReference error
    );
    
    /**
     * C func: memcached_server_list_st memcached_server_list_append_with_weight(
     *  memcached_server_list_st ptr,
     *  const char *hostname,
     *  in_port_t port,
     *  uint32_t weight,
     *  memcached_return_t *error
     * );
     */
    public memcached_server_list_st memcached_server_list_append_with_weight(
        memcached_server_list_st ptr,
        String hostname,
        int port,
        int weight,
        IntByReference error
    );
    
    /**
     * C func: uint32_t memcached_server_list_count(const memcached_server_list_st ptr)
     */
    public int memcached_server_list_count(memcached_server_list_st ptr);

    /**
     * C func: uint32_t memcached_servers_set_count(memcached_server_list_st servers, uint32_t count);
     */
    public int memcached_servers_set_count(memcached_server_list_st servers, int count);

    /**
     * C func: memcached_server_st *memcached_server_list(const memcached_st *)
     */
    public memcached_server_st memcached_server_list(memcached_st ptr);

    /**
     * C func: void memcached_server_list_set(memcached_st *self, memcached_server_list_st list)
     */
    public void memcached_server_list_set(memcached_st self, memcached_server_list_st list);

}
