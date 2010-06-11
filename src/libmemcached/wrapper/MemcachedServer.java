package libmemcached.wrapper;

import static libmemcached.wrapper.function.Quit.memcached_quit_server;
import static libmemcached.wrapper.function.Server.memcached_server_by_key;
import static libmemcached.wrapper.function.Server.memcached_server_clone;
import static libmemcached.wrapper.function.Server.memcached_server_error;
import static libmemcached.wrapper.function.Server.memcached_server_free;
import static libmemcached.wrapper.function.Server.memcached_server_name;
import static libmemcached.wrapper.function.Server.memcached_server_port;
import static libmemcached.wrapper.function.Server.memcached_server_response_count;
import libmemcached.exception.LibMemcachedException;
import libmemcached.types.memcached_server_instance_st;

public class MemcachedServer {
    
    protected final MemcachedClient memcached;

    protected final memcached_server_instance_st server_st;
    
    @SuppressWarnings("unused")
    private final Object finalizer = new Object(){
        @Override
        protected void finalize() throws Throwable {
            memcached_server_free(server_st);
            super.finalize();
        }
    };
    
    protected MemcachedServer(MemcachedClient memcached, String key) throws LibMemcachedException {
        this(memcached, memcached_server_by_key(memcached.memcached_st, key));
    }
    
    protected MemcachedServer(MemcachedClient memcached, memcached_server_instance_st server_st){
        this.memcached = memcached;
        this.server_st = server_st;
    }
    
    @Override
    protected MemcachedServer clone() throws CloneNotSupportedException {
        memcached_server_instance_st new_server_st = memcached_server_clone(null, server_st);
        return new MemcachedServer(memcached, new_server_st);
    }
    
    public int getResponseCount(){
        return memcached_server_response_count(server_st);
    }

    public String getHostName(){
        return memcached_server_name(server_st);
    }
    
    public int getPort(){
        return memcached_server_port(server_st);
    }
    
    public String getError(){
        return memcached_server_error(server_st);
    }
    
    public void quit(boolean io_death){
        memcached_quit_server(server_st, io_death);
    }
    
}
