package libmemcached.wrapper;

import libmemcached.LibMemcached;
import libmemcached.memcached;
import libmemcached.compat.size_t;
import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.result.memcached_result_st;
import libmemcached.stats.memcached_stat_st;
import libmemcached.types.memcached_server_instance_st;
import libmemcached.wrapper.type.ReturnType;

import com.sun.jna.ptr.IntByReference;

public class MemcachedClient {
    
    protected static final memcached handler;
    static {
        handler = LibMemcached.memcached;
    }
    
    protected final memcached_st memcached_st;
    
    protected final MemcachedServerList serverList = new MemcachedServerList(this);
    
    protected final MemcachedStorage storage = new MemcachedStorage(this);
    
    protected final MemcachedBehavior behavior = new MemcachedBehavior(this);
    
    public MemcachedClient(){
        this(handler.memcached_create(null));
    }
    
    public MemcachedClient(MemcachedClient memcached){
        this(memcached.memcached_st);
    }
    
    private MemcachedClient(memcached_st memcached_st){
        this.memcached_st = memcached_st;
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
            throw new CloneNotSupportedException("memcached_clone() failed.");
        }
        return new MemcachedClient(dest);
    }
    
    protected MemcachedClient newInstance(memcached_st memcached_st){
        return new MemcachedClient(memcached_st);
    }
    
    public MemcachedServerList getServerList(){
        return serverList;
    }
    
    public MemcachedStorage getStorage(){
        return storage;
    }
    
    public MemcachedBehavior getBehavior(){
        return behavior;
    }
    
    public String libraryVersion(){
        return handler.memcached_lib_version();
    }
    
    public int version(){
        return handler.memcached_version(memcached_st);
    }
    
    public ReturnType verbosity(int verbosity){
        int rc = handler.memcached_verbosity(memcached_st, verbosity);
        return ReturnType.get(rc);
    }
    
    public String error(int rc){
        return handler.memcached_strerror(memcached_st, rc);
    }
    
    public String error(ReturnType rt){
        return handler.memcached_strerror(memcached_st, rt.getValue());
    }
    
    public ReturnType addServer(String hostname, int port) {
        int rc = handler.memcached_server_add(memcached_st, hostname, port);
        return ReturnType.get(rc);
    }
    
    public ReturnType addUdpServer(String hostname, int port) {
        int rc = handler.memcached_server_add_udp(memcached_st, hostname, port);
        return ReturnType.get(rc);
    }
    
    public ReturnType addUnixSocketServer(String filename) {
        int rc = handler.memcached_server_add_unix_socket(memcached_st, filename);
        return ReturnType.get(rc);
    }
    
    public MemcachedServer createServer(String key) throws LibMemcachedException {
        size_t keyLength = new size_t(key.length());
        IntByReference error = new IntByReference();
        memcached_server_instance_st server_st = handler.memcached_server_by_key(memcached_st, key, keyLength, error);
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(error(rc));
        }
        return new MemcachedServer(this, server_st);
    }
    
    public MemcachedStats createStats(String args) throws LibMemcachedException {
        IntByReference error = new IntByReference();
        memcached_stat_st stat_st = handler.memcached_stat(memcached_st, args, error);
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(error(rc));
        }
        return new MemcachedStats(this, stat_st);
    }
    
    public MemcachedResult createResult(){
        memcached_result_st result_st = handler.memcached_result_create(memcached_st, null);
        return new MemcachedResult(this, result_st);
    }
    
    protected MemcachedResult createResult(memcached_result_st result_st){
        return new MemcachedResult(this, result_st);
    }
    
    public void quit(){
        handler.memcached_quit(memcached_st);
    }

}
