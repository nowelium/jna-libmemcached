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
    
    protected static final Charset charset = Charset.forName("UTF-8");
    
    public static SimpleResult memcached_get(memcached_st ptr, String key) throws LibMemcachedException {
        final size_t key_length = new size_t(key.getBytes().length);
        final LongByReference value_length = new LongByReference();
        final IntByReference flags = new IntByReference();
        final IntByReference error = new IntByReference();
        
        final String value = getMemcached().memcached_get(ptr, key, key_length, value_length, flags, error);
        
        final int rc = error.getValue();
        if(ReturnType.NOTFOUND.equalValue(rc)){
            return null;
        }
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc), ReturnType.get(rc));
        }
        return new SimpleResult(key, value, value_length.getValue(), flags.getValue());
    }
    
    public static SimpleResult memcached_get_by_key(memcached_st ptr, String master_key, String key) throws LibMemcachedException {
        final size_t master_key_length = new size_t(master_key.getBytes().length);
        final size_t key_length = new size_t(key.getBytes().length);
        final IntByReference value_length = new IntByReference();
        final IntByReference flags = new IntByReference();
        final IntByReference error = new IntByReference();
        
        final String result = getMemcached().memcached_get_by_key(
            ptr,
            master_key, master_key_length,
            key, key_length,
            value_length,
            flags,
            error
        );
        final int rc = error.getValue();
        if(ReturnType.NOTFOUND.equalValue(rc)){
            return null;
        }
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        final int valuelen = value_length.getValue();
        final String value = result.substring(0, valuelen);
        return new SimpleResult(key, value, valuelen, flags.getValue());
    }
    
    public static ReturnType memcached_mget(memcached_st ptr, String...keys){
        final int length = keys.length;
        if(length < 1){
            return ReturnType.NOTFOUND;
        }
        
        final size_t[] size = new size_t[length];
        for(int i = 0; i < length; ++i){
            size[i] = new size_t(keys[i].getBytes().length);
        }
        
        final size_t keys_length = new size_t(length);
        final int rc = getMemcached().memcached_mget(ptr, keys, size, keys_length);
        return ReturnType.get(rc);
    }
    
    public static ReturnType memcached_mget_by_key(memcached_st ptr, String master_key, String...keys){
        final int length = keys.length;
        if(length < 1){
            return ReturnType.NOTFOUND;
        }
        
        final size_t[] size = new size_t[length];
        for(int i = 0; i < length; ++i){
            size[i] = new size_t(keys[i].getBytes().length);
        }
        
        final size_t master_key_kength = new size_t(master_key.getBytes().length);
        final size_t keys_length = new size_t(length);
        final int rc = getMemcached().memcached_mget_by_key(ptr, master_key, master_key_kength, keys, size, keys_length);
        return ReturnType.get(rc);
    }
    
    public static memcached_result_st memcached_fetch_result(memcached_st ptr) throws LibMemcachedException {
        return memcached_fetch_result(ptr, null);
    }
    
    public static memcached_result_st memcached_fetch_result(memcached_st ptr, memcached_result_st result_st) throws LibMemcachedException {
        final IntByReference error = new IntByReference();
        
        final memcached_result_st result = getMemcached().memcached_fetch_result(ptr, result_st, error);
        final int rc = error.getValue();
        if(ReturnType.END.equalValue(rc)){
            return null;
        }
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc), ReturnType.get(rc));
        }
        return result;
    }
    
    public static SimpleResult memcached_fetch(memcached_st ptr) throws LibMemcachedException {
        final IntByReference key_length = new IntByReference();
        final byte[] returnKey = new byte[constants.MEMCACHED_MAX_KEY];
        final IntByReference value_length = new IntByReference();
        final IntByReference flags = new IntByReference();
        final IntByReference error = new IntByReference();
        
        final String result = getMemcached().memcached_fetch(
            ptr,
            returnKey, key_length, value_length,
            flags, error
        );

        final int rc = error.getValue();
        if(ReturnType.END.equalValue(rc)){
            return null;
        }
        if(!ReturnType.SUCCESS.equalValue(rc)){
            throw new LibMemcachedException(memcached_strerror(ptr, rc));
        }
        
        final int keylen = key_length.getValue();
        final int valuelen = value_length.getValue();
        
        final String key = byteToString(returnKey, 0, keylen);
        final String value = byteToString(result.getBytes(), 0, valuelen);
        
        /*
        final byte[] keyBuf = new byte[keylen];
        System.arraycopy(returnKey, 0, keyBuf, 0, keylen);
        final byte[] valueBuf = new byte[valuelen];
        System.arraycopy(result.getBytes(), 0, valueBuf, 0, valuelen);
        
        final String key = new String(keyBuf, charset);
        final String value = new String(valueBuf, charset);
        */
        return new SimpleResult(key, value, valuelen, flags.getValue());
    }
    
    protected static String byteToString(byte[] bytes, int position, int limit){
        // TODO: allocate or allocateDirect
        //final ByteBuffer buffer = ByteBuffer.allocate(1024);
        final ByteBuffer buffer = ByteBuffer.allocateDirect(bytes.length);
        buffer.put(bytes);
        buffer.position(position);
        buffer.limit(limit);
        
        return charset.decode(buffer).toString();
    }

}
