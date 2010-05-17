package libmemcached;

import libmemcached.constants.memcached_return;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface types {

    public static interface memcached_clone_func extends Callback {
        // C type: memcached_return_t
        memcached_return callback();
    }
    
    public static interface memcached_cleanup_func extends Callback {
        // C type: memcached_return_t
        memcached_return callback();
    }
    
    public static interface memcached_free_function extends Callback {
        // C type: memcached_return_t
        memcached_return callback();
    }
    
    public static interface memcached_server_function extends Callback {
        // C type: memcached_return_t
        memcached_return callback();
    }
    
    public static interface memcached_execute_function extends Callback {
        // C type: memcached_return_t
        memcached_return callback();
    }
    
    public static interface memcached_dump_function extends Callback {
        // C type: memcached_return_t
        memcached_return callback();
    }
    
    public static interface memcached_malloc_function extends Callback {
        // C type: void*
        Pointer callback();
    }
    
    public static interface memcached_realloc_function extends Callback {
        // C type: void*
        Pointer callback();
    }
    
    public static interface memcached_calloc_function extends Callback {
        // C type: void*
        Pointer callback();
    }
    
    public static interface memcached_trigger_key extends Callback {
        // C type: memcached_return_t
        memcached_return callback();
    }
    
    public static interface memcached_trigger_delete_key extends Callback {
        // C type: memcached_return_t
        memcached_return callback();
    }
}
