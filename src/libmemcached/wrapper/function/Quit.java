package libmemcached.wrapper.function;

import libmemcached.memcached.memcached_st;
import libmemcached.server.memcached_server_st;

public class Quit extends Function {

    public static void memcached_quit(memcached_st ptr){
        getMemcached().memcached_quit(ptr);
    }
    
    public static void memcached_quit_server(memcached_server_st ptr, boolean io_death){
        getMemcached().memcached_quit_server(ptr, io_death);
    }
}
