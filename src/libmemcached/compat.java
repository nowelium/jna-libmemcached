package libmemcached;

import com.sun.jna.Callback;
import com.sun.jna.IntegerType;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;

public interface compat {
    
    // netdb.h
    public static final int NI_MAXHOST = 1025;
    
    // alias
    public static class size_t extends IntegerType {
        private static final long serialVersionUID = 1L;
        public size_t(){
            this(0);
        }
        public size_t(long value){
            super(Native.SIZE_T_SIZE, value);
        }
    }
    
    // alias
    public static class time_t extends IntegerType {
        private static final long serialVersionUID = 1L;
        public time_t(){
            this(0);
        }
        public time_t(int value) {
            super(Native.LONG_SIZE, value);
        }
    }
    
    // alias
    public static class addrinfo extends PointerType {
        public addrinfo(){
            super();
        }
        public addrinfo(Pointer pointer){
            super(pointer);
        }
    }
    
    // libhashkit/hashkit.h
    /*
    public static class hashkit_st extends Structure {
        public hashkit_function_st base_hash;
        public hashkit_function_st distribution_hash;
        public flags_struct flags;
        public options_struct options;
        
        public hashkit_st(){
            super();
            setAutoSynch(false);
        }
        
        public static class ByReference extends hashkit_st implements Structure.ByReference {
            
        }
        public static class ByValue extends hashkit_st implements Structure.ByValue {
            
        }
        
        public static interface hashkit_hash_function extends Callback {
            public int invoke(Pointer key, size_t key_length, Pointer context);
        }
        
        public static class hashkit_function_st extends Structure {
            public hashkit_hash_function function;
            public Pointer context;
            
            public hashkit_function_st(){
                super();
                setAutoSynch(false);
            }
            
            public static class ByReference extends hashkit_function_st implements Structure.ByReference {
                
            }
            public static class ByValue extends hashkit_function_st implements Structure.ByValue {
                
            }
        }
        public static class flags_struct extends Structure {
            public boolean is_base_same_distributed;
            
            public flags_struct(){
                super();
                setAutoSynch(false);
            }

            public static class ByReference extends flags_struct implements Structure.ByReference {
                
            }
            public static class ByValue extends flags_struct implements Structure.ByValue {
                
            }
        }
        public static class options_struct extends Structure {
            public boolean is_allocated;
            
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
    
    public static class hashkit_st extends PointerType {
        // ignore
    }
    
    public static interface sasl_callback_t extends Callback {
        // include sasl/sasl.h
    }
}
