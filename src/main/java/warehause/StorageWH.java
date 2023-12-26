package warehause;

import warehause.exceptions.ItemDoubleException;
import warehause.exceptions.ItemNotFoundException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StorageWH implements Storage {
    public static final int INITIAL_CAPACITY = 256;
    private final Map<String, Wheel> items = new HashMap<>(INITIAL_CAPACITY);

    @Override
    public void putItem(Wheel wheel) {
        items.put(wheel.id(), wheel);
    }

    @Override
    public Wheel getItem(String id) throws ItemNotFoundException {
        Wheel wheel = items.get(id);
        if (wheel == null){
            throw new ItemNotFoundException(id);
        }
        return items.get(id);
    }

    @Override
    public Map<String, Wheel> getAllItem() {
        return new HashMap<>(items);
    }

    @Override
    public List<Wheel> getAllItemSortByVendor(Predicate<Wheel> predicate) {
        return items.values().stream().filter(predicate)
                .sorted(Comparator.comparing(Wheel::vendor)
                    .thenComparing(Wheel::type)
                    .thenComparing(Wheel::place)
                    .thenComparing(Wheel::id))
                .collect(Collectors.toList());

    }


    @Override
    public boolean containsItem(String id) {
        return items.containsKey(id);
    }

    @Override
    public void updateItem(String id, int quantity) {
        Wheel wheel = items.get(id);
        wheel.setQuantity(quantity);
    }

    @Override
    public void removeItem(String id) throws ItemNotFoundException {
        Wheel wheel = items.get(id);
        if (wheel == null){
            throw new ItemNotFoundException(id);
        }
        else {
            items.remove(id);
        }
    }

    @Override
    public boolean checkVendor(ListWVendor vendor) {
        int allQuantity = 0;
        for (Wheel item : items.values()) {
            if (item.getVendor() == vendor) {
                allQuantity += item.getQuantity();
            }
        }
        return allQuantity > 0;
    }

    @Override
    public boolean checkType(ListWType type) {
        int allQuantity = 0;
        for (Wheel item : items.values()) {
            if (item.getType() == type) {
                allQuantity += item.getQuantity();
            }
        }
        return allQuantity > 0;
    }

    @Override
    public boolean checkPlace(String place) {
        int allQuantity = 0;
        for (Wheel item : items.values()) {
            if (item.getPlace() == place) {
                allQuantity += item.getQuantity();
            }
        }
        return allQuantity > 0;
    }

    @Override
    public void putAllItems(List<Wheel> wheels) throws ItemDoubleException {
        for (Wheel item : wheels){
            if (items.containsKey(item.id()))
            {
                throw new ItemDoubleException("Items already added");
            }
            items.put(item.id(), item);
        }
    }

}
