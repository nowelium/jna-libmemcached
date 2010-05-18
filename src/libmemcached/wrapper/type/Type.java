package libmemcached.wrapper.type;

import java.util.HashMap;

interface Type<T extends Enum<T>> {
    
    public int getValue();
    
    class Map<T extends Type<?>> {
        private final HashMap<Integer, T> map = new HashMap<Integer, T>();
        public Map(Class<T> clazz){
            T[] values = clazz.getEnumConstants();
            for(T value: values){
                map.put(value.getValue(), value);
            }
        }
        public T get(int value){
            return map.get(value);
        }
    }
    
}
