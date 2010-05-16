package libmemcached.exception;

import com.sun.jna.ptr.IntByReference;

import libmemcached.LibMemcached;
import libmemcached.memcached.memcached_st;

public class LibMemcachedRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public LibMemcachedRuntimeException(memcached_st mmc, IntByReference error){
        this(mmc, error.getValue());
    }
    
    public LibMemcachedRuntimeException(memcached_st mmc, int error){
        this(LibMemcached.memcached.memcached_strerror(mmc, error)); 
    }
    
    public LibMemcachedRuntimeException(String message){
        super(message);
    }

}
