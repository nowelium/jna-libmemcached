package libmemcached.exception;

public class LibMemcachedRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public LibMemcachedRuntimeException(String message){
        super(message);
    }

}