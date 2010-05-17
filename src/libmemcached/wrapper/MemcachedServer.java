package libmemcached.wrapper;

import libmemcached.memcached;
import libmemcached.exception.LibMemcachedRuntimeException;
import libmemcached.server.memcached_server_st;

public class MemcachedServer {
    
    protected final memcached handler;
    protected final MemcachedClient memcached;
    protected final memcached_server_st server_st;
    
    protected MemcachedServer(MemcachedClient memcached, memcached_server_st server_st){
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
        MemcachedServer newServer = memcached.createServer();
        if(null == handler.memcached_server_clone(newServer.server_st, server_st)){
            throw new LibMemcachedRuntimeException("memcached_server_clone() failed");
        }
        return newServer;
    }


}
