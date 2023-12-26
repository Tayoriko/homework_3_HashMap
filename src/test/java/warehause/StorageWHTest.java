package warehause;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import warehause.exceptions.ItemDoubleException;
import warehause.exceptions.ItemNotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StorageWHTest {
    private StorageWH storage;
    Wheel wheel_1 = new Wheel("1", ListWVendor.Bridgestone, ListWType.summer, "A", 10);
    Wheel wheel_2 = new Wheel("2", ListWVendor.Continental, ListWType.winterS, "A", 10);
    Wheel wheel_3 = new Wheel("3", ListWVendor.Yokohama, ListWType.allSeason, "C", 10);
    Wheel wheel_4 = new Wheel("4", ListWVendor.Cooper, ListWType.winterV, "A", 10);
    Wheel wheel_5 = new Wheel("5", ListWVendor.KAMA, ListWType.offroad, "D", 10);
    Wheel wheel_6 = new Wheel("6", ListWVendor.Goodyear, ListWType.summer, "D", 10);
    Wheel wheel_100 = new Wheel("100", ListWVendor.KAMA, ListWType.offroad, "D", 10);
    Wheel wheel_101 = new Wheel("101", ListWVendor.Goodyear, ListWType.summer, "D", 10);

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        storage = new StorageWH();
        storage.putItem(wheel_1);
        storage.putItem(wheel_2);
        storage.putItem(wheel_3);
    }

    @org.junit.jupiter.api.Test
    void putItemTest() throws ItemNotFoundException {
        Wheel actual = storage.getItem(wheel_1.id());
        Assertions.assertEquals(wheel_1, actual);
    }

    @org.junit.jupiter.api.Test
    void getItemTest() throws ItemNotFoundException {
        String checkID = storage.getItem("3").id();
        Assertions.assertEquals(checkID, "3");
        Assertions.assertThrows(ItemNotFoundException.class, () -> storage.getItem("51"));
    }


    @org.junit.jupiter.api.Test
    void removeItemTest() throws ItemNotFoundException {
        storage.removeItem("3");
        Assertions.assertThrows(ItemNotFoundException.class, () -> storage.getItem("3"));
    }

    @Test
    void containsItemTest() {
        Assertions.assertEquals(storage.containsItem("1"), true);
        Assertions.assertEquals(storage.containsItem("Oo"), false);
    }

    @Test
    void updateItemTest() throws ItemNotFoundException {
        int newQuantity = 7;
        storage.updateItem("1", newQuantity);
        Assertions.assertEquals(storage.getItem("1").getQuantity(), newQuantity);
    }

    @Test
    void putAllItemsTest() throws ItemDoubleException {
        storage.putAllItems(List.of(wheel_4, wheel_5, wheel_6));
        Assertions.assertTrue(storage.containsItem("4"));
        Assertions.assertTrue(storage.containsItem("5"));
        Assertions.assertFalse(storage.containsItem("94"));
    }

    @Test
    void checkVendorTest() {
        Assertions.assertTrue(storage.checkVendor(ListWVendor.Bridgestone));
        Assertions.assertFalse(storage.checkVendor(ListWVendor.Nokian));
    }

    @Test
    void checkTypeTest() {
        Assertions.assertTrue(storage.checkType(ListWType.allSeason));
    }

    @Test
    void checkPlace() {
        storage.putItem(wheel_5);
        Assertions.assertTrue(storage.checkPlace("D"));
    }

    @Test
    void getAllItem() throws ItemDoubleException {
        storage.putAllItems(List.of(wheel_6, wheel_4, wheel_5));
        Map<String, Wheel> allItems = storage.getAllItem();
        Assertions.assertEquals(6, allItems.size());
    }

    @Test
    void getAllItemSortByVendor() throws ItemDoubleException {
        storage.putAllItems(List.of(wheel_6, wheel_4, wheel_5));
        Set<ListWVendor> expectedVendors = new HashSet<>(List.of(ListWVendor.KAMA, ListWVendor.Bridgestone));
        List<Wheel> allItemSortByVendor = storage.getAllItemSortByVendor(wheel -> expectedVendors.contains(wheel.getVendor()));
        List<Wheel> sortedOrder = List.of(wheel_1, wheel_5);
        Assertions.assertEquals(2, allItemSortByVendor.size());
        Assertions.assertEquals(sortedOrder, allItemSortByVendor);



    }
}