package libmemcached.wrapper.function;

import static libmemcached.wrapper.function.StrError.memcached_strerror;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import libmemcached.constants;
import libmemcached.compat.size_t;
import libmemcached.exception.LibMemcachedException;
import libmemcached.memcached.memcached_st;
import libmemcached.result.memcached_result_st;
import libmemcached.wrapper.SimpleResult;
import libmemcached.wrapper.type.ReturnType;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

public class Get extends Function {
    
    public static SimpleResult memcached_get(memcached_st ptr, String key) throws LibMemcachedException {
        size_t key_length = new size_t(key.length());
        LongByReference value_length = new LongByReference();
        IntByReference flags = new IntByReference();
        IntByReference error = new IntByReference();
        
        String value = getMemcached().memcached_get(ptr, key, key_length, value_length, flags, error);
        int rc = error.getValue();
        if(ReturnType.NOTFOUND.equalValue(rc)){
            return null;
        }
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc), ReturnType.get(rc));
        }
        return new SimpleResult(key, value, value_length.getValue(), flags.getValue());
    }
    
    public static SimpleResult memcached_get_by_key(memcached_st ptr, String master_key, String key) throws LibMemcachedException {
        size_t master_key_length = new size_t(master_key.length());
        size_t key_length = new size_t(key.length());
        IntByReference value_length = new IntByReference();
        IntByReference flags = new IntByReference();
        IntByReference error = new IntByReference();
        
        String result = getMemcached().memcached_get_by_key(
            ptr,
            master_key, master_key_length,
            key, key_length,
            value_length,
            flags,
            error
        );
        int rc = error.getValue();
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        String value = result.substring(0, value_length.getValue());
        return new SimpleResult(key, value, value_length.getValue(), flags.getValue());
    }
    
    public static ReturnType memcached_mget(memcached_st ptr, String...keys){
        size_t[] size = new size_t[keys.length];
        for(int i = 0; i < keys.length; ++i){
            size[i] = new size_t(keys[i].length());
        }
        
        size_t keys_length = new size_t(keys.length);
        int rc = getMemcached().memcached_mget(ptr, keys, size, keys_length);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_mget_by_key(memcached_st ptr, String master_key, String...keys){
        size_t[] size = new size_t[keys.length];
        for(int i = 0; i < keys.length; ++i){
            size[i] = new size_t(keys[i].length());
        }
        
        size_t master_key_kength = new size_t(master_key.length());
        size_t keys_length = new size_t(keys.length);
        int rc = getMemcached().memcached_mget_by_key(ptr, master_key, master_key_kength, keys, size, keys_length);
        return ReturnType.get(rc);
    }
    
    public static memcached_result_st memcached_fetch_result(memcached_st ptr) throws LibMemcachedException {
        return memcached_fetch_result(ptr, null);
    }
    
    public static memcached_result_st memcached_fetch_result(memcached_st ptr, memcached_result_st result_st) throws LibMemcachedException {
        IntByReference error = new IntByReference();
        memcached_result_st result = getMemcached().memcached_fetch_result(ptr, result_st, error);
        int rc = error.getValue();
        if(ReturnType.END.equalValue(rc)){
            return null;
        }
        
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc), ReturnType.get(rc));
        }
        return result;
    }
    
    public static SimpleResult memcached_fetch(memcached_st ptr) throws LibMemcachedException {
        IntByReference key_length = new IntByReference();
        byte[] returnKey = new byte[constants.MEMCACHED_MAX_KEY];
        IntByReference value_length = new IntByReference();
        IntByReference flags = new IntByReference();
        IntByReference error = new IntByReference();
        String result = getMemcached().memcached_fetch(
            ptr,
            returnKey, key_length, value_length,
            flags, error
        );

        int rc = error.getValue();
        if(ReturnType.END.equalValue(rc)){
            return null;
        }
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        
        String key = new String(returnKey, 0, key_length.getValue());
        //String value = result.substring(0, value_length.getValue());
        
        // TODO: this should not be hardcoded to 1024 bytes
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(result.getBytes());
        
        int length = value_length.getValue();
        buffer.position(0);
        buffer.limit(length);
        String value = Charset.forName("UTF-8").decode(buffer).toString();
        return new SimpleResult(key, value, value_length.getValue(), flags.getValue());
    }

}
