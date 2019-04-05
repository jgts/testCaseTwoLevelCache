package manager;
import java.util.HashMap;
import java.util.Map;

/*
 * LRU cache mamaner
 */
public class LRUCacheManager<Key> implements CacheManagerInterface<Key>{
    private final Map<Key,Long> cacheIndex;


    public LRUCacheManager(){
        cacheIndex=new HashMap<>();
    }


    @Override
    public Key getAndRemove() {
        Map.Entry<Key, Long> minEntry = cacheIndex.entrySet().stream()
                .min(Map.Entry.comparingByValue()).get();
        Key key = minEntry.getKey();
        cacheIndex.remove(key);
        return key;
    }

    @Override
    public Long get(Key key) {
        return cacheIndex.get(key);
    }

    @Override
    public Boolean containsKey(Key key){
        return cacheIndex.containsKey(key);
    }

    @Override
    public void clear() {
        cacheIndex.clear();
    }


    @Override
    public void put(Key key) {
        cacheIndex.put(key,System.nanoTime());
    }
}
