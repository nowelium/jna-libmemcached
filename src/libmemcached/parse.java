package libmemcached;

import libmemcached.types.memcached_server_list_st;

public interface parse {
    /**
     * C func: memcached_server_list_st memcached_servers_parse(const char *server_strings);
     */
    public memcached_server_list_st memcached_servers_parse(String server_strings);
}
