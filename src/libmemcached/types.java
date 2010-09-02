package libmemcached;

import libmemcached.compat.size_t;
import libmemcached.memcached.memcached_st;
import libmemcached.result.memcached_result_st;
import libmemcached.server.memcached_server_st;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface types {
    
    public static class memcached_server_instance_st extends memcached_server_st {
        // alias
    }
    
    public static class memcached_server_list_st extends memcached_server_st {
        // alias
    }
    
    public static interface memcached_free_function extends Callback {
        // C type: void
        public void callback(memcached_st ptr, Pointer mem, Pointer context);
    }
    
    public static interface memcached_malloc_function extends Callback {
        // C type: void*
        public Pointer callback(memcached_st ptr, size_t size, Pointer context);
    }
    
    public static interface memcached_realloc_function extends Callback {
        // C type: void*
        public Pointer callback(memcached_st ptr, Pointer mem, size_t size, Pointer context);
    }
    
    public static interface memcached_calloc_function extends Callback {
        // C type: void*
        public Pointer callback(memcached_st ptr, size_t nelem, size_t elsize, Pointer context);
    }
    
    public static interface memcached_clone_function extends Callback {
        // C type: memcached_return_t
        public int callback(memcached_st destination, memcached_st source);
    }
    
    public static interface memcached_cleanup_function extends Callback {
        // C type: memcached_return_t
        public int callback(memcached_st ptr);
    }
    
    public static interface memcached_execute_function extends Callback {
        // C type: memcached_return_t
        public int callback(memcached_st ptr, memcached_result_st result, Pointer context);
    }
    
    public static interface memcached_server_function extends Callback {
        // C type: memcached_return_t
        public int callback(memcached_st ptr, memcached_server_st server, Pointer context);
    }
    
    public static interface memcached_trigger_key_function extends Callback {
        // C type: memcached_return_t
        public int callback(memcached_st ptr, Pointer key, size_t key_length, memcached_result_st result);
    }
    
    public static interface memcached_trigger_delete_key_function extends Callback {
        // C type: memcached_return_t
        public int callback(memcached_st ptr, Pointer key, size_t key_length);
    }
    
    public static interface memcached_dump_function extends Callback {
        // C type: memcached_return_t
        public int callback(memcached_st ptr, Pointer key, size_t key_length, Pointer context);
    }
}
