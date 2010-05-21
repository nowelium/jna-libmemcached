package libmemcached.exception;

public class LibMemcachedException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private int code = -1;
    
    public LibMemcachedException(String message){
        this(message, 0);
    }
    
    public LibMemcachedException(String message, int code){
        super(message);
        this.code = code;
    }
    
    public int getCode(){
        return code;
    }

}