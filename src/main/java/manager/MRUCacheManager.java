package manager;
import java.util.HashMap;
import java.util.Map;
/*
 * MRU cache mamaner
 */
public class MRUCacheManager<Key> implements CacheManagerInterface<Key> {
    private final Map<Key,Long> cacheIndex;


    public MRUCacheManager(){
        cacheIndex=new HashMap<>();
    }


    @Override
    public Key getAndRemove() {

        Map.Entry<Key, Long> maxEntry = cacheIndex.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get();
        Key key=maxEntry.getKey();
        cacheIndex.remove(key);
        return key;
    }

    @Override
    public Long get(Key key) {
        return cacheIndex.get(key);
    }

    @Override
    public void put(Key key) {
        cacheIndex.put(key,System.nanoTime());

    }

    @Override
    public Boolean containsKey(Key key) {
        return  cacheIndex.containsKey(key);
    }

    @Override
    public void clear() {
        cacheIndex.clear();
    }


}
