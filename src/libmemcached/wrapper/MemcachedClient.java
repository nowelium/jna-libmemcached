package libmemcached.wrapper;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;

import libmemcached.LibMemcached;
import libmemcached.memcached;
import libmemcached.exception.LibMemcachedException;
import libmemcached.exception.LibMemcachedRuntimeException;
import libmemcached.memcached.memcached_st;
import libmemcached.result.memcached_result_st;
import libmemcached.server.memcached_server_st;

public class MemcachedClient {
    
    protected static final memcached handler;
    static {
        handler = LibMemcached.memcached;
    }
    
    protected final memcached_st memcached_st;
    
    protected final MemcachedServerList serverList = new MemcachedServerList(this);
    
    protected final MemcachedStorage storage = new MemcachedStorage(this);
    
    public MemcachedClient(){
        this(handler.memcached_create(null));
    }
    
    public MemcachedClient(MemcachedClient parent){
        this(parent.memcached_st);
    }
    
    protected MemcachedClient(memcached_st parent){
        this.memcached_st = parent;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        handler.memcached_free(memcached_st);
    }

    @Override
    protected MemcachedClient clone() throws CloneNotSupportedException {
        memcached_st source = this.memcached_st;
        memcached_st dest = handler.memcached_create(null);
        if(null == handler.memcached_clone(dest, source)){
            throw new LibMemcachedRuntimeException("memcached_clone() failed.");
        }
        return new MemcachedClient(dest);
    }
    
    public MemcachedServerList getServerList(){
        return serverList;
    }
    
    public MemcachedStorage getStorage(){
        return storage;
    }
    
    public String libraryVersion(){
        return handler.memcached_lib_version();
    }
    
    public int version(){
        return handler.memcached_version(memcached_st);
    }
    
    public String error(int rc){
        return handler.memcached_strerror(memcached_st, rc);
    }
    
    public void addServer(String hostname, int port) throws LibMemcachedException {
        int rc = handler.memcached_server_add(memcached_st, hostname, port);
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(error(rc));
        }
    }
    
    public void addUdpServer(String hostname, int port) throws LibMemcachedException {
        int rc = handler.memcached_server_add_udp(memcached_st, hostname, port);
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(error(rc));
        }
    }
    
    public void addUnixSocketServer(String filename) throws LibMemcachedException {
        int rc = handler.memcached_server_add_unix_socket(memcached_st, filename);
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(error(rc));
        }
    }
    
    public MemcachedResult createResult(){
        memcached_result_st result_st = handler.memcached_result_create(memcached_st, null);
        return new MemcachedResult(this, result_st);
    }
    
    protected MemcachedResult createResult(memcached_result_st result_st){
        return new MemcachedResult(this, result_st);
    }
    
    public MemcachedServer getServerByKey(String key) throws LibMemcachedException {
        NativeLong keyLength = new NativeLong(key.length());
        IntByReference error = new IntByReference();
        memcached_server_st server_st = handler.memcached_server_by_key(memcached_st, key, keyLength, error);
        
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(error(rc));
        }
        return new MemcachedServer(this, server_st);
    }

}
