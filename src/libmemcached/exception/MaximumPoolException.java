package libmemcached.exception;

public class MaximumPoolException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public MaximumPoolException(String message){
        super(message);
    }

}
