package cache;
import java.util.HashMap;
import java.util.logging.Logger;

/*
* memory cache class container for Obkects
* key - generic type key for hashmap operations
*/
public class MemoryCache<Key,Value> implements MemoryCacheInterface<Key,Value> {
    private static Logger LOGGER = Logger.getLogger(MemoryCache.class.getName());
    @Override
    public Value get(Key key) {
        return this.memoryMap.get(key);
    }


    @Override
    public void put(Key key, Value value) {
        this.memoryMap.put(key,value);
    }

    @Override
    public Value remove(Key key) {
        return  memoryMap.remove(key);
    }

    @Override
    public void clear() {
        this.memoryMap.clear();
    }

    @Override
    public Integer getSize() {
       return this.memoryMap.size();
    }

    @Override
    public Boolean containsKey(Key key) {
        return this.memoryMap.containsKey(key);
    }


    private final HashMap<Key,Value> memoryMap;
    private Integer initialCapacity;

    public MemoryCache(Integer initialCapacity) {
        if (initialCapacity<=0){
            //default size
            initialCapacity=16;
        }
        this.initialCapacity=initialCapacity;
        this.memoryMap=new HashMap<Key, Value>(initialCapacity);
    }
    public MemoryCache() {
        this.initialCapacity=16;
        this.memoryMap=new HashMap<Key, Value>();
    }

    public Boolean isNotFull(){
        return  initialCapacity>memoryMap.size();
    }




}
