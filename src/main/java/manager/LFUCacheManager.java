package manager;
import java.util.HashMap;
import java.util.Map;

/*
* LFU cache mamaner
*/
public class LFUCacheManager<Key> implements CacheManagerInterface<Key> {
    private final Map<Key,Long> cacheIndex;

    public LFUCacheManager() {
        this.cacheIndex=new HashMap<>();
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
        return this.cacheIndex.get(key);
    }

    @Override
    public void put(Key key) {
        Long freq = get(key);
        if (freq==null){
            this.cacheIndex.put(key,0L);
        }else{
            freq++;
            this.cacheIndex.put(key,freq);
        }
    }

    @Override
    public Boolean containsKey(Key key) {
        return this.cacheIndex.containsKey(key);
    }

    @Override
    public void clear() {
        this.cacheIndex.clear();
    }
}
