package warehause;

import warehause.exceptions.ItemDoubleException;
import warehause.exceptions.ItemNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface Storage {
    //add 1 type wheel to Storage
    void putItem(Wheel wheel);
    //add X type wheels to storage
    void putAllItems(List<Wheel> wheels) throws ItemDoubleException;
    //get 1 type wheel from storage
    Wheel getItem(String id) throws ItemNotFoundException;
    //get all
    Map<String, Wheel> getAllItem();
    //get all sorted by vendor
    List<Wheel> getAllItemSortByVendor(Predicate<Wheel> predicate);
    //contains 1 item in storage
    boolean containsItem(String id);
    //update 1 item in storage
    void updateItem(String id, int quantity);
    //remove 1 item from wh
    void removeItem(String id) throws ItemNotFoundException;
    //check vendors in wh
    boolean checkVendor(ListWVendor vendor);
    //check type in wh
    boolean checkType(ListWType type);
    //check void section in wh
    boolean checkPlace(String place);
    //clean base
    void clean();
}
