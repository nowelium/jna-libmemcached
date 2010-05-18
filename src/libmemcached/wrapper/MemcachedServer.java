package libmemcached.wrapper;

import libmemcached.memcached;
import libmemcached.exception.LibMemcachedRuntimeException;
import libmemcached.types.memcached_server_instance_st;

public class MemcachedServer {
    
    protected final memcached handler;
    protected final MemcachedClient memcached;
    protected final memcached_server_instance_st server_st;
    
    protected MemcachedServer(MemcachedClient memcached, memcached_server_instance_st server_st){
        this.handler = MemcachedClient.handler;
        this.memcached = memcached;
        this.server_st = server_st;
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        handler.memcached_server_free(server_st);
    }

    @Override
    protected MemcachedServer clone() throws CloneNotSupportedException {
        memcached_server_instance_st new_server_st = handler.memcached_server_clone(null, server_st);
        if(null == server_st){
            throw new LibMemcachedRuntimeException("memcached_server_clone() failed");
        }
        return new MemcachedServer(memcached, new_server_st);
    }
    
    public int getResponseCount(){
        return handler.memcached_server_response_count(server_st);
    }

    public String getHostName(){
        return handler.memcached_server_name(server_st);
    }
    
    public int getPort(){
        return handler.memcached_server_port(server_st);
    }
    
    public String getError(){
        return handler.memcached_server_error(server_st);
    }
    
    public void quit(boolean io_death){
        handler.memcached_quit_server(server_st, io_death);
    }
    
}
