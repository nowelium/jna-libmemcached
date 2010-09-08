package libmemcached.wrapper;

import static libmemcached.wrapper.function.Result.memcached_result_cas;
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

import java.util.concurrent.atomic.AtomicBoolean;

import libmemcached.result.memcached_result_st;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedResult {
    
    protected final AtomicBoolean free = new AtomicBoolean(false);
    
    protected final memcached_result_st result_st;
    
    protected MemcachedResult(memcached_result_st result_st){
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
    
    public void free(){
        if(free.getAndSet(true)){
            return ;
        }
        
        memcached_result_free(result_st);
    }

}
