package warehause;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BasicAnalitycs implements Analitics {
    private final Storage storage;
    public BasicAnalitycs(Storage storage){
        this.storage = storage;
    }

    @Override
    public Set<ListWVendor> getVendor() {
        //я не понимаю как решить эту задачу в одну проклятую строку
        Set<ListWVendor> collect = new HashSet<>();
        for (String key : this.storage.getAllItem().keySet()) {
            if (this.storage.getAllItem().get(key).getQuantity() > 0) {
                collect.add(this.storage.getAllItem().get(key).getVendor());
            }
        }
        return collect;
    }

    @Override
    public Map<TypeAndPlace, Integer> getItemOnPlace(ListWType type, String place) {
        TypeAndPlace typeAndPlace;
        Map<TypeAndPlace, Integer> aggregation = new HashMap<>();
        Map<String, Wheel> itemInWH = storage.getAllItem();
        for (Wheel wheel : itemInWH.values()){
            if (wheel.getType().equals(type) && wheel.getPlace().equals(place) && wheel.getQuantity() > 0){
                typeAndPlace = new TypeAndPlace(type, place);
                Integer cnt = aggregation.getOrDefault(typeAndPlace, 0);
                cnt += wheel.getQuantity();
                aggregation.put(typeAndPlace, cnt);
            }
        }
        return aggregation;
    }

    @Override
    public Integer getAllCnt() {
        return storage.getAllItem().values().stream().map(wheel -> wheel.getQuantity()).mapToInt(Integer::intValue).sum();
    }
}
