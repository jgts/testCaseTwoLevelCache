package manager;

/*
* simple cache strategy enum
*/
public enum CacheType {
    LeastRecentlyUsed (0),
    MostRecentlyUsed (1),
    LeastFrequentlyUsed(2);
    private final Integer value;

    public Integer getValue() {
        return value;
    }
    private static CacheType[] values = null;

    private  CacheType(int value) {
        this.value = value;
    }


    public static CacheType fromInt(Integer i) {
        if (CacheType.values == null) {
            CacheType.values = CacheType.values();
        }
        return CacheType.values[i];
    }
}
