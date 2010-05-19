package libmemcached.wrapper.function;

import static libmemcached.wrapper.function.StrError.memcached_strerror;
import libmemcached.constants;
import libmemcached.compat.size_t;
import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.result.memcached_result_st;
import libmemcached.wrapper.SimpleResult;
import libmemcached.wrapper.type.ReturnType;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;

public class Get extends Function {
    
    public static SimpleResult memcached_get(memcached_st ptr, String key) throws LibMemcachedException {
        size_t key_length = new size_t(key.length());
        NativeLongByReference value_length = new NativeLongByReference();
        IntByReference flags = new IntByReference();
        IntByReference error = new IntByReference();
        
        String value = getMemcached().memcached_get(ptr, key, key_length, value_length, flags, error);
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        return new SimpleResult(key, value, value_length.getValue().longValue(), flags.getValue());
    }
    
    public static SimpleResult memcached_get_by_key(memcached_st ptr, String master_key, String key) throws LibMemcachedException {
        size_t master_key_length = new size_t(master_key.length());
        size_t key_length = new size_t(key.length());
        NativeLongByReference value_length = new NativeLongByReference();
        IntByReference flags = new IntByReference();
        IntByReference error = new IntByReference();
        
        String value = getMemcached().memcached_get_by_key(ptr, master_key, master_key_length, key, key_length, value_length, flags, error);
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        return new SimpleResult(key, value, value_length.getValue().longValue(), flags.getValue());
    }
    
    public static ReturnType memcached_mget(memcached_st ptr, String...keys){
        NativeLong length = new NativeLong(keys.length);
        NativeLongByReference key_length = new NativeLongByReference(length);
        int rc = getMemcached().memcached_mget(ptr, keys, key_length, keys.length);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_mget_by_key(memcached_st ptr, String master_key, String...keys){
        size_t master_key_kength = new size_t(master_key.length());
        NativeLongByReference keyLength = new NativeLongByReference(new NativeLong(keys.length));
        int rc = getMemcached().memcached_mget_by_key(ptr, master_key, master_key_kength, keys, keyLength, keys.length);
        return ReturnType.get(rc);
    }
    
    public static memcached_result_st memcached_fetch_result(memcached_st ptr) throws LibMemcachedException {
        return memcached_fetch_result(ptr, null);
        
    }
    public static memcached_result_st memcached_fetch_result(memcached_st ptr, memcached_result_st result_st) throws LibMemcachedException {
        IntByReference error = new IntByReference();
        memcached_result_st result = getMemcached().memcached_fetch_result(ptr, result_st, error);
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        return result;
    }
    
    public static SimpleResult memcached_fetch(memcached_st ptr, int length) throws LibMemcachedException {
        NativeLongByReference keyLength = new NativeLongByReference(new NativeLong(length));
        byte[] returnKey = new byte[constants.MEMCACHED_MAX_KEY];
        NativeLongByReference valueLength = new NativeLongByReference();
        IntByReference flags = new IntByReference();
        IntByReference error = new IntByReference();
        String result = getMemcached().memcached_fetch(
            ptr,
            returnKey, keyLength, valueLength, flags, error
        );

        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        return new SimpleResult(new String(returnKey), result, result.length(), flags.getValue());
    }

}
