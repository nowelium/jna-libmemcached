package libmemcached.wrapper;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import libmemcached.constants;
import libmemcached.memcached;
import libmemcached.exception.LibMemcachedException;
import libmemcached.result.memcached_result_st;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;

public class MemcachedStorage {
    
    public static class Result {
        protected String key;
        protected String value;
        protected long length;
        protected int flags;
        protected Result(String key, String value, long length, int flags){
            this.key = key;
            this.value = value;
            this.length = length;
            this.flags = flags;
        }
        public String getKey(){
            return key;
        }
        public String getValue(){
            return value;
        }
        public long getLength(){
            return length;
        }
        public int getFlags(){
            return flags;
        }
    }
    
    public static interface Fetcher {
        public void fetch(Result result);
    }
    
    protected final memcached handler;
    
    protected final MemcachedClient memcached;
    
    protected final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    protected final Lock writeLock = lock.writeLock();
    
    protected final Lock readLock = lock.readLock();
    
    protected MemcachedStorage(MemcachedClient memcached){
        this.handler = MemcachedClient.handler;
        this.memcached = memcached;
    }
    
    public Result getResult(String key) throws LibMemcachedException {
        NativeLong keyLength = new NativeLong(key.length());
        NativeLongByReference valueLength = new NativeLongByReference();
        IntByReference flags = new IntByReference();
        IntByReference error = new IntByReference();
        
        String result = handler.memcached_get(
            memcached.memcached_st,
            key, keyLength,
            valueLength,
            flags,
            error
        );
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached.error(rc));
        }
        return new Result(key, result, valueLength.getValue().longValue(), flags.getValue());
    }
    
    public Result getResultByKey(String masterKey, String key) throws LibMemcachedException {
        NativeLong masterKeyLength = new NativeLong(masterKey.length());
        NativeLong keyLength = new NativeLong(key.length());
        NativeLongByReference valueLength = new NativeLongByReference();
        IntByReference flags = new IntByReference();
        IntByReference error = new IntByReference();
        
        String result = handler.memcached_get_by_key(
            memcached.memcached_st,
            masterKey, masterKeyLength,
            key, keyLength,
            valueLength,
            flags,
            error
        );
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached.error(rc));
        }
        return new Result(key, result, valueLength.getValue().longValue(), flags.getValue());
    }
    
    public String get(String key) throws LibMemcachedException {
        Result result = getResult(key);
        return result.getValue();
    }
    
    public String getByKey(String masterKey, String key) throws LibMemcachedException {
        Result result = getResultByKey(masterKey, key);
        return result.getValue();
    }
    
    public ReturnType getMulti(String...keys) throws LibMemcachedException {
        NativeLong length = new NativeLong(keys.length);
        NativeLongByReference keyLength = new NativeLongByReference(length);
        int rc = handler.memcached_mget(memcached.memcached_st, keys, keyLength, keys.length);
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached.error(rc));
        }
        return ReturnType.get(rc);
    }
    
    public ReturnType getMultiByKey(String masterKey, String...keys) throws LibMemcachedException {
        NativeLong masterKeyLength = new NativeLong(masterKey.length());
        NativeLongByReference keyLength = new NativeLongByReference(new NativeLong(keys.length));
        int rc = handler.memcached_mget_by_key(memcached.memcached_st, masterKey, masterKeyLength, keys, keyLength, keys.length);
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached.error(rc));
        }
        return ReturnType.get(rc);
    }
    
    public void getMulti(Fetcher fetcher, String...keys) throws LibMemcachedException {
        readLock.lock();
        try {
            NativeLong length = new NativeLong(keys.length);
            NativeLongByReference keyLength = new NativeLongByReference(length);
            int rc = handler.memcached_mget(memcached.memcached_st, keys, keyLength, keys.length);
            if(!ReturnType.SUCCESS.equalValue(rc)){
                throw new LibMemcachedException(memcached.error(rc));
            }
            
            fetch(keys.length);
        } finally {
            readLock.unlock();
        }
    }
    
    public void getMultiByKey(Fetcher fetcher, String masterKey, String...keys) throws LibMemcachedException {
        readLock.lock();
        try {
            NativeLong masterKeyLength = new NativeLong(masterKey.length());
            NativeLong length = new NativeLong(keys.length);
            NativeLongByReference keyLength = new NativeLongByReference(length);
            int rc = handler.memcached_mget_by_key(memcached.memcached_st, masterKey, masterKeyLength, keys, keyLength, keys.length);
            if(!ReturnType.SUCCESS.equalValue(rc)){
                throw new LibMemcachedException(memcached.error(rc));
            }
            
            fetch(keys.length);
        } finally {
            readLock.unlock();
        }
    }
    
    public Result fetchResult(int length) throws LibMemcachedException {
        NativeLongByReference keyLength = new NativeLongByReference(new NativeLong(length));
        byte[] returnKey = new byte[constants.MEMCACHED_MAX_KEY];
        NativeLongByReference valueLength = new NativeLongByReference();
        IntByReference flags = new IntByReference();
        IntByReference error = new IntByReference();
        String result = handler.memcached_fetch(
            memcached.memcached_st,
            returnKey, keyLength, valueLength, flags, error
        );

        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached.error(rc));
        }
        return new Result(new String(returnKey), result, result.length(), flags.getValue());
    }
    
    public MemcachedResult fetchResult() throws LibMemcachedException {
        IntByReference error = new IntByReference();
        memcached_result_st result_st = handler.memcached_fetch_result(memcached.memcached_st, null, error);
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached.error(rc));
        }
        return memcached.createResult(result_st);
    }
    
    public MemcachedResult fetchResult(MemcachedResult result) throws LibMemcachedException {
        IntByReference error = new IntByReference();
        memcached_result_st result_st = handler.memcached_fetch_result(memcached.memcached_st, result.result_st, error);
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached.error(rc));
        }
        return memcached.createResult(result_st);
    }
    
    public String fetch(int length) throws LibMemcachedException {
        Result result = fetchResult(length);
        return result.getValue();
    }
    
    public void fetch(int length, Fetcher fetcher) {
        byte[] returnKey = new byte[constants.MEMCACHED_MAX_KEY];
        NativeLongByReference keyLength = new NativeLongByReference(new NativeLong(length));
        NativeLongByReference valueLength = new NativeLongByReference();
        IntByReference flags = new IntByReference();
        IntByReference error = new IntByReference();
        
        String result = null;
        do {
            result = handler.memcached_fetch(
                memcached.memcached_st,
                returnKey, keyLength, valueLength, flags, error
            );
            int err = error.getValue();
            if(!ReturnType.SUCCESS.equalValue(err)){
                continue;
            }
            
            fetcher.fetch(new Result(new String(returnKey), result, result.length(), flags.getValue()));
        } while(result != null);
    }
    
    public ReturnType delete(String key, int expiration){
        NativeLong keyLength = new NativeLong(key.length());
        int rc = handler.memcached_delete(
            memcached.memcached_st,
            key, keyLength,
            expiration
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType deleteByKey(String masterKey, String key, int expiration){
        NativeLong masterKeyLength = new NativeLong(masterKey.length());
        NativeLong keyLength = new NativeLong(key.length());
        int rc = handler.memcached_delete_by_key(
            memcached.memcached_st,
            masterKey, masterKeyLength,
            key, keyLength,
            expiration
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType increment(String key, int offset, long value){
        NativeLong keyLength = new NativeLong(key.length());
        int rc = handler.memcached_increment(
            memcached.memcached_st,
            key, keyLength,
            offset,
            value
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType incrementWithInitial(String key, int offset, long initial, int expiration, long value){
        NativeLong keyLength = new NativeLong(key.length());
        int rc = handler.memcached_increment_with_initial(
            memcached.memcached_st,
            key, keyLength,
            offset,
            initial,
            expiration,
            value
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType decrement(String key, int offset, long value){
        NativeLong keyLength = new NativeLong(key.length());
        int rc = handler.memcached_decrement(
            memcached.memcached_st,
            key, keyLength,
            offset,
            value
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType decrementWithInitial(String key, int offset, long initial, int expiration, long value){
        NativeLong keyLength = new NativeLong(key.length());
        int rc = handler.memcached_decrement_with_initial(
            memcached.memcached_st,
            key, keyLength,
            offset,
            initial,
            expiration,
            value
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType set(String key, String value, int expiration, int flags){
        NativeLong keyLength = new NativeLong(key.length());
        NativeLong valueLength = new NativeLong(value.length());
        int rc = handler.memcached_set(
            memcached.memcached_st,
            key, keyLength,
            value, valueLength,
            expiration, flags
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType setByKey(String masterKey, String key, String value, int expiration, int flags){
        NativeLong masterKeyLength = new NativeLong(masterKey.length());
        NativeLong keyLength = new NativeLong(key.length());
        NativeLong valueLength = new NativeLong(value.length());
        int rc = handler.memcached_set_by_key(
            memcached.memcached_st,
            masterKey, masterKeyLength,
            key, keyLength,
            value, valueLength,
            expiration, flags
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType add(String key, String value, int expiration, int flags){
        NativeLong keyLength = new NativeLong(key.length());
        NativeLong valueLength = new NativeLong(value.length());
        int rc = handler.memcached_add(
            memcached.memcached_st,
            key, keyLength,
            value, valueLength,
            expiration, flags
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType addByKey(String masterKey, String key, String value, int expiration, int flags){
        NativeLong masterKeyLength = new NativeLong(masterKey.length());
        NativeLong keyLength = new NativeLong(key.length());
        NativeLong valueLength = new NativeLong(value.length());
        int rc = handler.memcached_add_by_key(
            memcached.memcached_st,
            masterKey, masterKeyLength,
            key, keyLength,
            value, valueLength,
            expiration, flags
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType replace(String key, String value, int expiration, int flags){
        NativeLong keyLength = new NativeLong(key.length());
        NativeLong valueLength = new NativeLong(value.length());
        int rc = handler.memcached_replace(
            memcached.memcached_st,
            key, keyLength,
            value, valueLength,
            expiration, flags
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType replaceByKey(String masterKey, String key, String value, int expiration, int flags){
        NativeLong masterKeyLength = new NativeLong(masterKey.length());
        NativeLong keyLength = new NativeLong(key.length());
        NativeLong valueLength = new NativeLong(value.length());
        int rc = handler.memcached_replace_by_key(
            memcached.memcached_st,
            masterKey, masterKeyLength,
            key, keyLength,
            value, valueLength,
            expiration, flags
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType append(String key, String value, int expiration, int flags){
        NativeLong keyLength = new NativeLong(key.length());
        NativeLong valueLength = new NativeLong(value.length());
        int rc = handler.memcached_append(
            memcached.memcached_st,
            key, keyLength,
            value, valueLength,
            expiration, flags
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType appendByKey(String masterKey, String key, String value, int expiration, int flags){
        NativeLong masterKeyLength = new NativeLong(masterKey.length());
        NativeLong keyLength = new NativeLong(key.length());
        NativeLong valueLength = new NativeLong(value.length());
        int rc = handler.memcached_append_by_key(
            memcached.memcached_st,
            masterKey, masterKeyLength,
            key, keyLength,
            value, valueLength,
            expiration, flags
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType prepend(String key, String value, int expiration, int flags){
        NativeLong keyLength = new NativeLong(key.length());
        NativeLong valueLength = new NativeLong(value.length());
        int rc = handler.memcached_prepend(
            memcached.memcached_st,
            key, keyLength,
            value, valueLength,
            expiration, flags
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType prependByKey(String masterKey, String key, String value, int expiration, int flags){
        NativeLong masterKeyLength = new NativeLong(masterKey.length());
        NativeLong keyLength = new NativeLong(key.length());
        NativeLong valueLength = new NativeLong(value.length());
        int rc = handler.memcached_prepend_by_key(
            memcached.memcached_st,
            masterKey, masterKeyLength,
            key, keyLength,
            value, valueLength,
            expiration, flags
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType cas(String key, String value, int expiration, int flags, long cas){
        NativeLong keyLength = new NativeLong(key.length());
        NativeLong valueLength = new NativeLong(value.length());
        int rc = handler.memcached_cas(
            memcached.memcached_st,
            key, keyLength,
            value, valueLength,
            expiration, flags, cas
        );
        return ReturnType.get(rc);
    }
    
    public ReturnType casByKey(String masterKey, String key, String value, int expiration, int flags, long cas){
        NativeLong masterKeyLength = new NativeLong(masterKey.length());
        NativeLong keyLength = new NativeLong(key.length());
        NativeLong valueLength = new NativeLong(value.length());
        int rc = handler.memcached_cas_by_key(
            memcached.memcached_st,
            masterKey,masterKeyLength,
            key, keyLength,
            value, valueLength,
            expiration, flags, cas
        );
        return ReturnType.get(rc);
    }

}