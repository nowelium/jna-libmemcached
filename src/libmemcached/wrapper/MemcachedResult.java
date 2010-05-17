package libmemcached.wrapper;

import libmemcached.memcached;
import libmemcached.result.memcached_result_st;

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
    
    public int getFlags(){
        return result_st.item_flags;
    }
    
    public int getExpiration(){
        return result_st.item_expiration;
    }
    
    public String getKey(){
        return new String(result_st.item_key);
    }
    
    public long length(){
        return result_st.key_length.longValue();
    }
    
    public long getCAS(){
        return result_st.item_cas;
    }
    
    public void reset(){
        handler.memcached_result_reset(result_st);
    }

}
