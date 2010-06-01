package libmemcached.wrapper;

import static libmemcached.wrapper.function.Result.memcached_result_cas;
import static libmemcached.wrapper.function.Result.memcached_result_create;
import static libmemcached.wrapper.function.Result.memcached_result_flags;
import static libmemcached.wrapper.function.Result.memcached_result_free;
import static libmemcached.wrapper.function.Result.memcached_result_key_length;
import static libmemcached.wrapper.function.Result.memcached_result_key_value;
import static libmemcached.wrapper.function.Result.memcached_result_length;
import static libmemcached.wrapper.function.Result.memcached_result_reset;
import static libmemcached.wrapper.function.Result.memcached_result_set_expiration;
import static libmemcached.wrapper.function.Result.memcached_result_set_flags;
import static libmemcached.wrapper.function.Result.memcached_result_set_value;
import static libmemcached.wrapper.function.Result.memcached_result_value;
import libmemcached.result.memcached_result_st;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedResult {
    
    protected final MemcachedClient memcached;
    
    protected final memcached_result_st result_st;
    
    @SuppressWarnings("unused")
    private final Object finalizer = new Object(){
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            memcached_result_free(result_st);
        }
    };
    
    protected MemcachedResult(MemcachedClient memcached){
        this(memcached, memcached_result_create(memcached.memcached_st));
    }
    
    protected MemcachedResult(MemcachedClient memcached, memcached_result_st result_st){
        this.memcached = memcached;
        this.result_st = result_st;
    }

    public String getKey(){
        return memcached_result_key_value(result_st);
    }
    
    public long getKeyLength(){
        return memcached_result_key_length(result_st);
    }
    
    public String getValue(){
        return memcached_result_value(result_st);
    }
    
    public long getLength(){
        return memcached_result_length(result_st);
    }
    
    public long getCAS(){
        return memcached_result_cas(result_st);
    }
    
    public int getFlags(){
        return memcached_result_flags(result_st);
    }
    
    public ReturnType setValue(String value){
        return memcached_result_set_value(result_st, value);
    }
    
    public void setFlags(int flags){
        memcached_result_set_flags(result_st, flags);
    }
    
    public void setExpiration(int expiration){
        memcached_result_set_expiration(result_st, expiration);
    }
    
    public void reset(){
        memcached_result_reset(result_st);
    }

}
