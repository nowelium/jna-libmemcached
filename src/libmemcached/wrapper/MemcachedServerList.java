package libmemcached.wrapper;

import static libmemcached.wrapper.function.Parse.memcached_servers_parse;
import static libmemcached.wrapper.function.ServerList.memcached_server_list_append;
import static libmemcached.wrapper.function.ServerList.memcached_server_list_append_with_weight;
import static libmemcached.wrapper.function.ServerList.memcached_server_list_count;
import static libmemcached.wrapper.function.ServerList.memcached_server_list_free;
import static libmemcached.wrapper.function.ServerList.memcached_server_push;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import libmemcached.exception.LibMemcachedException;
import libmemcached.types.memcached_server_list_st;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedServerList {
    
    protected final MemcachedClient memcached;
    
    protected final Lock lock = new ReentrantLock();
    
    protected memcached_server_list_st server_st = null;

    @SuppressWarnings("unused")
    private final Object finalizer = new Object(){
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            if(null != server_st){
                memcached_server_list_free(server_st);
            }
        }
    };
    
    protected MemcachedServerList(MemcachedClient memcached){
        this.memcached = memcached;
    }
    
    public void append(String hostname, int port) throws LibMemcachedException {
        lock.lock();
        
        try {
            server_st = memcached_server_list_append(server_st, hostname, port);
        } finally {
            lock.unlock();
        }
    }
    
    public void append(String hostname, int port, int weight) throws LibMemcachedException {
        lock.lock();
        
        try {
            server_st = memcached_server_list_append_with_weight(server_st, hostname, port, weight);
        } finally {
            lock.unlock();
        }
    }
    
    public void parse(String server_strings) {
        lock.lock();
        
        try {
            server_st = memcached_servers_parse(server_strings);
        } finally {
            lock.unlock();
        }
    }
    
    public int count(){
        lock.lock();
        
        try {
            return memcached_server_list_count(server_st);
        } finally {
            lock.unlock();
        }
    }
    
    public ReturnType push() {
        lock.lock();
        
        try {
            return memcached_server_push(memcached.memcached_st, server_st);
        } finally {
            lock.unlock();
        }
    }

}
