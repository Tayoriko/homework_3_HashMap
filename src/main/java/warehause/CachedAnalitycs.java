package warehause;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CachedAnalitycs implements Analitics {
    // Создание LRU Cache с максимальной емкостью 100 и временем жизни элементов 10 минут
    Cache<String, Wheel> lruCache = CacheBuilder.newBuilder()
            .maximumSize(5)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    private final BasicAnalitycs basicAnalitycs;
    public CachedAnalitycs(BasicAnalitycs basicAnalitycs){
        this.basicAnalitycs = basicAnalitycs;
    }


    @Override
    public Set<ListWVendor> getVendor() {
        return basicAnalitycs.getVendor();
    }

    @Override
    public Map<TypeAndPlace, Integer> getItemOnPlace(ListWType type, String place) {
        return basicAnalitycs.getItemOnPlace(type, place);
    }

    @Override
    public Integer getAllCnt() {
        return basicAnalitycs.getAllCnt();
    }
}
