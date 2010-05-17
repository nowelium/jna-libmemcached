package libmemcached.exception;

public class LibMemcachedException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public LibMemcachedException(String message){
        super(message);
    }

}