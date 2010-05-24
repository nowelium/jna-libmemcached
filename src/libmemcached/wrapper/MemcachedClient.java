package libmemcached.wrapper;

import static libmemcached.wrapper.function.Analyze.memcached_analyze;
import static libmemcached.wrapper.function.Memcached.memcached_clone;
import static libmemcached.wrapper.function.Memcached.memcached_create;
import static libmemcached.wrapper.function.Memcached.memcached_free;
import static libmemcached.wrapper.function.Quit.memcached_quit;
import static libmemcached.wrapper.function.Result.memcached_result_create;
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
import libmemcached.analyze.memcached_analysis_st;
import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.result.memcached_result_st;
import libmemcached.stats.memcached_stat_st;
import libmemcached.types.memcached_server_instance_st;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedClient {
    
    protected final memcached_st memcached_st;
    
    protected final MemcachedServerList serverList = new MemcachedServerList(this);
    
    protected final MemcachedStorage storage = new MemcachedStorage(this);
    
    protected final MemcachedBehavior behavior = new MemcachedBehavior(this);
    
    @SuppressWarnings("unused")
    private final Object finalizer = new Object(){
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            memcached_free(memcached_st);
        }
    };
    
    public MemcachedClient(){
        this(memcached_create());
    }
    
    public MemcachedClient(MemcachedClient memcached){
        this(memcached.memcached_st);
    }
    
    private MemcachedClient(memcached_st memcached_st){
        this.memcached_st = memcached_st;
    }

    @Override
    protected MemcachedClient clone() throws CloneNotSupportedException {
        memcached_st source = this.memcached_st;
        memcached_st dest = memcached_create();
        if(null == memcached_clone(dest, source)){
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
        memcached_server_instance_st server_st = memcached_server_by_key(memcached_st, key);
        return new MemcachedServer(this, server_st);
    }
    
    public MemcachedStats createStats(String args) throws LibMemcachedException {
        memcached_stat_st stat_st = memcached_stat(memcached_st, args);
        return new MemcachedStats(this, stat_st);
    }
    
    public MemcachedAnalyze createAnalyze(MemcachedStats stats) throws LibMemcachedException {
        memcached_analysis_st analysis_st = memcached_analyze(memcached_st, stats.stat_st);
        return new MemcachedAnalyze(analysis_st);
    }
    
    public MemcachedResult createResult(){
        memcached_result_st result_st = memcached_result_create(memcached_st);
        return new MemcachedResult(this, result_st);
    }
    
    protected MemcachedResult createResult(memcached_result_st result_st){
        return new MemcachedResult(this, result_st);
    }
    
    public void quit(){
        memcached_quit(memcached_st);
    }

}
