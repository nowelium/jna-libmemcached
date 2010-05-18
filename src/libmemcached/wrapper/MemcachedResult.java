package libmemcached.wrapper;

import libmemcached.memcached;
import libmemcached.compat.size_t;
import libmemcached.compat.time_t;
import libmemcached.result.memcached_result_st;
import libmemcached.wrapper.type.ReturnType;

public class MemcachedResult {
    
    protected final memcached handler;
    
    protected final MemcachedClient memcached;
    
    protected final memcached_result_st result_st;
    
    protected MemcachedResult(MemcachedClient memcached, memcached_result_st result_st){
        this.handler = MemcachedClient.handler;
        this.memcached = memcached;
        this.result_st = result_st;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        handler.memcached_result_free(result_st);
    }
    
    public String getKey(){
        return handler.memcached_result_key_value(result_st);
    }
    
    public long getKeyLength(){
        return handler.memcached_result_key_length(result_st).longValue();
    }
    
    public String getValue(){
        return handler.memcached_result_value(result_st);
    }
    
    public long getLength(){
        return handler.memcached_result_length(result_st).longValue();
    }
    
    public long getCAS(){
        return handler.memcached_result_cas(result_st);
    }
    
    public int getFlags(){
        return handler.memcached_result_flags(result_st);
    }
    
    public ReturnType setValue(String value){
        size_t length = new size_t(value.length());
        int rc = handler.memcached_result_set_value(result_st, value, length);
        return ReturnType.get(rc);
    }
    
    public void setFlags(int flags){
        handler.memcached_result_set_flags(result_st, flags);
    }
    
    public void setExpiration(int expiration){
        time_t expire = new time_t(expiration);
        handler.memcached_result_set_expiration(result_st, expire);
    }
    
    public void reset(){
        handler.memcached_result_reset(result_st);
    }

}
