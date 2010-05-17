package libmemcached.wrapper;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import libmemcached.memcached;
import libmemcached.exception.LibMemcachedException;
import libmemcached.types.memcached_server_list_st;

import com.sun.jna.ptr.IntByReference;

public class MemcachedServerList {
    
    protected final memcached handler;
    
    protected final MemcachedClient memcached;
    
    protected final Lock lock = new ReentrantLock();
    
    protected memcached_server_list_st server_st = null;
    
    protected MemcachedServerList(MemcachedClient memcached){
        this.handler = MemcachedClient.handler;
        this.memcached = memcached;
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if(null != server_st){
            handler.memcached_server_list_free(server_st);
        }
    }

    public void append(String hostname, int port) throws LibMemcachedException {
        lock.lock();
        
        try {
            IntByReference error = new IntByReference();
            memcached_server_list_st newServerSt = handler.memcached_server_list_append(server_st, hostname, port, error);
            int rc = error.getValue();
            if(!ReturnType.SUCCESS.equalValue(rc)){
                throw new LibMemcachedException(memcached.error(rc));
            }
            server_st = newServerSt;
        } finally {
            lock.unlock();
        }
    }
    
    public void append(String hostname, int port, int weight) throws LibMemcachedException {
        lock.lock();
        
        try {
            IntByReference error = new IntByReference();
            memcached_server_list_st newServerSt = handler.memcached_server_list_append_with_weight(server_st, hostname, port, weight, error);
            int rc = error.getValue();
            if(!ReturnType.SUCCESS.equalValue(rc)){
                throw new LibMemcachedException(memcached.error(rc));
            }
            server_st = newServerSt;
        } finally {
            lock.unlock();
        }
    }
    
    public void parse(String server_strings) {
        lock.lock();
        
        try {
            server_st = handler.memcached_servers_parse(server_strings);
        } finally {
            lock.unlock();
        }
    }
    
    public int count(){
        lock.lock();
        
        try {
            return handler.memcached_server_list_count(server_st);
        } finally {
            lock.unlock();
        }
    }
    
    public void push() throws LibMemcachedException {
        lock.lock();
        
        try {
            int rc = handler.memcached_server_push(memcached.memcached_st, server_st);
            if(!ReturnType.SUCCESS.equalValue(rc)){
                throw new LibMemcachedException(memcached.error(rc));
            }
        } finally {
            lock.unlock();
        }
    }

}
