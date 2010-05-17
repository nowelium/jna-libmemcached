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

    public String getHostName(){
        return new String(server_st.hostname);
    }
    
    public int getPort(){
        return server_st.port;
    }
    
    public int getWeight(){
        return server_st.weight;
    }
    
    public byte getMajorVersion(){
        return server_st.major_version;
    }
    
    public byte getMicroVersion(){
        return server_st.micro_version;
    }
    
    public byte getMinorVersion(){
        return server_st.minor_version;
    }
    
    public ConnectionType getType(){
        return ConnectionType.get(server_st.type);
    }
    
    public ReturnType remove(){
        int rc = handler.memcached_server_remove(server_st);
        return ReturnType.get(rc);
    }

}
