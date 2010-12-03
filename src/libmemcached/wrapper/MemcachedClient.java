package libmemcached.wrapper;

import static libmemcached.wrapper.function.Analyze.memcached_analyze;
import static libmemcached.wrapper.function.Memcached.memcached_clone;
import static libmemcached.wrapper.function.Memcached.memcached_create;
import static libmemcached.wrapper.function.Memcached.memcached_free;
import static libmemcached.wrapper.function.Pool.memcached_pool_create;
import static libmemcached.wrapper.function.Quit.memcached_quit;
import static libmemcached.wrapper.function.Server.memcached_server_add;
import static libmemcached.wrapper.function.Server.memcached_server_add_udp;
import static libmemcached.wrapper.function.Server.memcached_server_add_udp_with_weight;
import static libmemcached.wrapper.function.Server.memcached_server_add_unix_socket;
import static libmemcached.wrapper.function.Server.memcached_server_add_unix_socket_with_weight;
import static libmemcached.wrapper.function.Server.memcached_server_add_with_weight;
import static libmemcached.wrapper.function.Server.memcached_server_by_key;
import static libmemcached.wrapper.function.Stats.memcached_stat;
import static libmemcached.wrapper.function.StrError.memcached_strerror;
import static libmemcached.wrapper.function.Verbosity.memcached_verbosity;
import static libmemcached.wrapper.function.Version.memcached_lib_version;
import static libmemcached.wrapper.function.Version.memcached_version;
import static libmemcached.wrapper.function.Result.memcached_result_create;

import java.util.concurrent.atomic.AtomicBoolean;

import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedClient {
    
    protected final AtomicBoolean free = new AtomicBoolean(false);
    
    protected final memcached_st memcached_st;
    
    protected final MemcachedServerList serverList;
    
    protected final MemcachedStorage storage;
    
    protected final MemcachedBehavior behavior;
    
//    @SuppressWarnings("unused")
//    private final Object finalizer = new Object(){
//        @Override
//        protected void finalize() throws Throwable {
//            memcached_free(memcached_st);
//            super.finalize();
//        }
//    };
    
    public MemcachedClient(){
        this(memcached_create());
    }
    
    public MemcachedClient(MemcachedClient memcached) throws CloneNotSupportedException {
        this(memcached.clone().memcached_st);
    }
    
    private MemcachedClient(memcached_st memcached_st){
        this.memcached_st = memcached_st;
        this.serverList = new MemcachedServerList(memcached_st);
        this.storage = new MemcachedStorage(memcached_st);
        this.behavior = new MemcachedBehavior(memcached_st);
    }

    @Override
    public MemcachedClient clone() throws CloneNotSupportedException {
        memcached_st dest = memcached_clone(null, memcached_st);
        if(null == dest){
            throw new CloneNotSupportedException("memcached_clone() failed.");
        }
        return new MemcachedClient(dest);
    }
    
    protected static MemcachedClient newInstance(memcached_st memcached_st){
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
        return memcached_lib_version();
    }
    
    public ReturnType version(){
        return memcached_version(memcached_st);
    }
    
    public ReturnType verbosity(int verbosity){
        return memcached_verbosity(memcached_st, verbosity);
    }
    
    public String error(int rc){
        return memcached_strerror(memcached_st, rc);
    }
    
    public String error(ReturnType rt){
        return memcached_strerror(memcached_st, rt.getValue());
    }
    
    public ReturnType addServer(String hostname, int port) {
        return memcached_server_add(memcached_st, hostname, port);
    }
    
    public ReturnType addServer(String hostname, int port, int weight) {
        return memcached_server_add_with_weight(memcached_st, hostname, port, weight);
    }

    public ReturnType addUdpServer(String hostname, int port) {
        return memcached_server_add_udp(memcached_st, hostname, port);
    }

    public ReturnType addUdpServer(String hostname, int port, int weight) {
        return memcached_server_add_udp_with_weight(memcached_st, hostname, port, weight);
    }
    
    public ReturnType addUnixSocketServer(String filename) {
        return memcached_server_add_unix_socket(memcached_st, filename);
    }

    public ReturnType addUnixSocketServer(String filename, int weight) {
        return memcached_server_add_unix_socket_with_weight(memcached_st, filename, weight);
    }
    
    public MemcachedServer createServer(String key) throws LibMemcachedException {
        return new MemcachedServer(memcached_server_by_key(memcached_st, key));
    }
    
    public MemcachedStats createStats(String args, String hostname, int port) throws LibMemcachedException {
        return new MemcachedStats(memcached_st, memcached_stat(memcached_st, args, hostname, port));
    }
    
    public MemcachedStats createStats(String args) throws LibMemcachedException {
        return new MemcachedStats(memcached_st, memcached_stat(memcached_st, args));
    }
    
    public MemcachedStats createStats() throws LibMemcachedException {
        return new MemcachedStats(memcached_st, memcached_stat(memcached_st));
    }
    
    public MemcachedAnalyze createAnalyze(MemcachedStats stats) throws LibMemcachedException {
        return new MemcachedAnalyze(memcached_analyze(memcached_st, stats.stat_st));
    }
    
    public MemcachedResult createResult(){
        return new MemcachedResult(memcached_result_create(memcached_st));
    }
    
    public MemcachedPool createPool(int initial, int max){
        return new MemcachedPool(memcached_pool_create(memcached_st, initial, max));
    }
    
    public void quit(){
        memcached_quit(memcached_st);
    }
    
    public void free(){
        if(free.getAndSet(true)){
            return ;
        }
        
        memcached_free(memcached_st);
    }

}
