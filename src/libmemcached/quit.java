package libmemcached;

import libmemcached.memcached.memcached_st;
import libmemcached.server.memcached_server_st;

public interface quit {

    /**
     * C func: void memcached_quit(memcached_st *ptr);
     */
    public void memcached_quit(memcached_st ptr);
    
    /**
     * C func: void memcached_quit_server(memcached_server_st *ptr, bool io_death);
     */
    public void memcached_quit_server(memcached_server_st ptr, boolean io_death);
}
