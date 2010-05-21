package libmemcached.wrapper;

import static libmemcached.wrapper.function.StrError.memcached_strerror;
import static libmemcached.wrapper.function.Auto.memcached_decrement;
import static libmemcached.wrapper.function.Auto.memcached_decrement_with_initial;
import static libmemcached.wrapper.function.Auto.memcached_increment;
import static libmemcached.wrapper.function.Auto.memcached_increment_with_initial;
import static libmemcached.wrapper.function.Delete.memcached_delete;
import static libmemcached.wrapper.function.Delete.memcached_delete_by_key;
import static libmemcached.wrapper.function.Flush.memcached_flush;
import static libmemcached.wrapper.function.Get.memcached_fetch;
import static libmemcached.wrapper.function.Get.memcached_fetch_result;
import static libmemcached.wrapper.function.Get.memcached_get;
import static libmemcached.wrapper.function.Get.memcached_get_by_key;
import static libmemcached.wrapper.function.Get.memcached_mget;
import static libmemcached.wrapper.function.Get.memcached_mget_by_key;
import static libmemcached.wrapper.function.Storage.memcached_add;
import static libmemcached.wrapper.function.Storage.memcached_add_by_key;
import static libmemcached.wrapper.function.Storage.memcached_append;
import static libmemcached.wrapper.function.Storage.memcached_append_by_key;
import static libmemcached.wrapper.function.Storage.memcached_cas;
import static libmemcached.wrapper.function.Storage.memcached_cas_by_key;
import static libmemcached.wrapper.function.Storage.memcached_prepend;
import static libmemcached.wrapper.function.Storage.memcached_prepend_by_key;
import static libmemcached.wrapper.function.Storage.memcached_replace;
import static libmemcached.wrapper.function.Storage.memcached_replace_by_key;
import static libmemcached.wrapper.function.Storage.memcached_set;
import static libmemcached.wrapper.function.Storage.memcached_set_by_key;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import libmemcached.exception.LibMemcachedException;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedStorage {
    
    public static interface Fetcher {
        public void fetch(SimpleResult result);
    }
    
    protected final MemcachedClient memcached;
    
    protected final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    protected final Lock writeLock = lock.writeLock();
    
    protected final Lock readLock = lock.readLock();
    
    protected MemcachedStorage(MemcachedClient memcached){
        this.memcached = memcached;
    }
    
    public SimpleResult getResult(String key) throws LibMemcachedException {
        return memcached_get(memcached.memcached_st, key);
    }
    
    public SimpleResult getResultByKey(String masterKey, String key) throws LibMemcachedException {
        return memcached_get_by_key(memcached.memcached_st, masterKey, key);
    }
    
    public String get(String key) throws LibMemcachedException {
        SimpleResult result = getResult(key);
        return result.getValue();
    }
    
    public String getByKey(String masterKey, String key) throws LibMemcachedException {
        SimpleResult result = getResultByKey(masterKey, key);
        return result.getValue();
    }
    
    public ReturnType getMulti(String...keys) {
        return memcached_mget(memcached.memcached_st, keys);
    }
    
    public ReturnType getMultiByKey(String masterKey, String...keys) {
        return memcached_mget_by_key(memcached.memcached_st, masterKey, keys);
    }
    
    public void getMulti(Fetcher fetcher, String...keys) throws LibMemcachedException {
        readLock.lock();
        try {
            ReturnType rt = memcached_mget(memcached.memcached_st, keys);
            if(!ReturnType.SUCCESS.equals(rt)){
                throw new LibMemcachedException(memcached_strerror(memcached.memcached_st, rt.getValue()));
            }
            
            fetch(fetcher);
        } finally {
            readLock.unlock();
        }
    }
    
    public void getMultiByKey(Fetcher fetcher, String masterKey, String...keys) throws LibMemcachedException {
        readLock.lock();
        try {
            ReturnType rt = memcached_mget_by_key(memcached.memcached_st, masterKey, keys);
            if(!ReturnType.SUCCESS.equals(rt)){
                throw new LibMemcachedException(memcached_strerror(memcached.memcached_st, rt.getValue()));
            }
            
            fetch(fetcher);
        } finally {
            readLock.unlock();
        }
    }
    
    public SimpleResult fetch() throws LibMemcachedException {
        return memcached_fetch(memcached.memcached_st);
    }
    
    public MemcachedResult fetchResult() throws LibMemcachedException {
        return memcached.createResult(memcached_fetch_result(memcached.memcached_st));
    }
    
    public MemcachedResult fetchResult(MemcachedResult result) throws LibMemcachedException {
        return memcached.createResult(memcached_fetch_result(memcached.memcached_st, result.result_st));
    }
    
    public String fetchString() throws LibMemcachedException {
        SimpleResult result = fetch();
        return result.getValue();
    }
    
    public void fetch(Fetcher fetcher) throws LibMemcachedException {
        SimpleResult result = null;
        do {
            result = memcached_fetch(memcached.memcached_st);
            if(result == null){
                break;
            }
            
            fetcher.fetch(result);
        } while(result != null);
    }
    
    public ReturnType delete(String key, int expiration){
        return memcached_delete(memcached.memcached_st, key, expiration);
    }
    
    public ReturnType deleteByKey(String masterKey, String key, int expiration){
        return memcached_delete_by_key(memcached.memcached_st, masterKey, key, expiration);
    }
    
    public ReturnType increment(String key, int offset, long value){
        return memcached_increment(memcached.memcached_st, key, offset, value);
    }
    
    public ReturnType incrementWithInitial(String key, int offset, long initial, int expiration, long value){
        return memcached_increment_with_initial(memcached.memcached_st, key, offset, initial, expiration, value);
    }
    
    public ReturnType decrement(String key, int offset, long value){
        return memcached_decrement(memcached.memcached_st, key, offset, value);
    }
    
    public ReturnType decrementWithInitial(String key, int offset, long initial, int expiration, long value){
        return memcached_decrement_with_initial(memcached.memcached_st, key, offset, initial, expiration, value);
    }
    
    public ReturnType set(String key, String value, int expiration, int flags){
        return memcached_set(memcached.memcached_st, key, value, expiration, flags);
    }
    
    public ReturnType setByKey(String masterKey, String key, String value, int expiration, int flags){
        return memcached_set_by_key(memcached.memcached_st, masterKey, key, value, expiration, flags);
    }
    
    public ReturnType add(String key, String value, int expiration, int flags){
        return memcached_add(memcached.memcached_st, key, value, expiration, flags);
    }
    
    public ReturnType addByKey(String masterKey, String key, String value, int expiration, int flags){
        return memcached_add_by_key(memcached.memcached_st, masterKey, key, value, expiration, flags);
    }
    
    public ReturnType replace(String key, String value, int expiration, int flags){
        return memcached_replace(memcached.memcached_st, key, value, expiration, flags);
    }
    
    public ReturnType replaceByKey(String masterKey, String key, String value, int expiration, int flags){
        return memcached_replace_by_key(memcached.memcached_st, masterKey, key, value, expiration, flags);
    }
    
    public ReturnType append(String key, String value, int expiration, int flags){
        return memcached_append(memcached.memcached_st, key, value, expiration, flags);
    }
    
    public ReturnType appendByKey(String masterKey, String key, String value, int expiration, int flags){
        return memcached_append_by_key(memcached.memcached_st, masterKey, key, value, expiration, flags);
    }
    
    public ReturnType prepend(String key, String value, int expiration, int flags){
        return memcached_prepend(memcached.memcached_st, key, value, expiration, flags);
    }
    
    public ReturnType prependByKey(String masterKey, String key, String value, int expiration, int flags){
        return memcached_prepend_by_key(memcached.memcached_st, masterKey, key, value, expiration, flags);
    }
    
    public ReturnType cas(String key, String value, int expiration, int flags, long cas){
        return memcached_cas(memcached.memcached_st, key, value, expiration, flags, cas);
    }
    
    public ReturnType casByKey(String masterKey, String key, String value, int expiration, int flags, long cas){
        return memcached_cas_by_key(memcached.memcached_st, masterKey, key, value, expiration, flags, cas);
    }
    
    public ReturnType flush(int expiration){
        return memcached_flush(memcached.memcached_st, expiration);
    }

}