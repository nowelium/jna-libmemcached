package libmemcached;

import libmemcached.compat.size_t;
import libmemcached.memcached.memcached_st;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public interface string {
    
    public static class memcached_string_st extends Structure {
        // C type: char*
        public Pointer end;
        // C type: char*
        public Pointer string;
        // C type: size_t
        public size_t current_size;
        // C type: memcached_st*
        public memcached_st.ByReference root;
        // C type: options
        public options_struct options;
        
        public memcached_string_st() {
            super();
        }
        public memcached_string_st(Pointer end, Pointer string, size_t current_size, memcached_st.ByReference root, options_struct options) {
            super();
            this.end = end;
            this.string = string;
            this.current_size = current_size;
            this.root = root;
            this.options = options;
        }
        public static class ByReference extends memcached_string_st implements Structure.ByReference {
            
        }
        public static class ByValue extends memcached_string_st implements Structure.ByValue {
            
        }
        
        public static class options_struct extends Structure {
            // C type: bool
            public boolean is_allocated;
            // C type: bool
            public boolean is_initialized;
            
            public options_struct() {
                super();
            }
            public options_struct(boolean is_allocated, boolean is_initialized) {
                super();
                this.is_allocated = is_allocated;
                this.is_initialized = is_initialized;
            }
            
            public static class ByReference extends options_struct implements Structure.ByReference {
                
            }
            public static class ByValue extends options_struct implements Structure.ByValue {
                
            }
        }
    }

}
