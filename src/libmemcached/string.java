package libmemcached;

import com.sun.jna.PointerType;

public interface string {
    
    /*
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
        
        public memcached_string_st(){
            super();
            setAutoSynch(false);
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
            
            public options_struct(){
                super();
                setAutoSynch(false);
            }
            
            public static class ByReference extends options_struct implements Structure.ByReference {
                
            }
            public static class ByValue extends options_struct implements Structure.ByValue {
                
            }
        }
    }
    */
    
    public static class memcached_string_st extends PointerType {
        // ignore
    }

}
