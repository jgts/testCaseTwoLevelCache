package manager;

import cache.FileCache;

import java.util.logging.Logger;

/*
* Simpe cache manager Factory
*/
public class CacheManagerFactory {
    private static Logger LOGGER = Logger.getLogger(CacheManagerFactory.class.getName());
    public  static CacheManagerInterface createCacheManager(CacheType cacheType){
        if (cacheType==null){
            //set default if null
            cacheType=CacheType.MostRecentlyUsed;
            LOGGER.warning("Using default strategy cache");
        }
        CacheManagerInterface cacheManager=null;
        switch (cacheType){
           case LeastRecentlyUsed: cacheManager= new LRUCacheManager(); break;
           case MostRecentlyUsed: cacheManager= new MRUCacheManager(); break;
           case LeastFrequentlyUsed: cacheManager=new LFUCacheManager(); break;
           default:
               cacheManager= new MRUCacheManager();
               LOGGER.warning("Using default strategy cache");
               break;
        }
        return cacheManager;
    }

}
