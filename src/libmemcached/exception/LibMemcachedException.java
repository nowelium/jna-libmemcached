package libmemcached.exception;

import libmemcached.wrapper.type.ReturnType;

public class LibMemcachedException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private final ReturnType rt;
    
    public LibMemcachedException(String message){
        this(message, null);
    }
    
    public LibMemcachedException(String message, ReturnType rt){
        super(message);
        this.rt = rt;
    }
    
    public ReturnType getReturnType(){
        return rt;
    }

}