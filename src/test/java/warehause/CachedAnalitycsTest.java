package warehause;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import warehause.exceptions.ItemDoubleException;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CachedAnalitycsTest {

    private StorageWH storage;
    private CachedAnalitycs analitics;
    Wheel wheel_1 = new Wheel("1", ListWVendor.Bridgestone, ListWType.summer, "A", 10);
    Wheel wheel_2 = new Wheel("2", ListWVendor.Continental, ListWType.winterS, "A", 10);
    Wheel wheel_3 = new Wheel("3", ListWVendor.Yokohama, ListWType.allSeason, "C", 10);
    Wheel wheel_4 = new Wheel("4", ListWVendor.Cooper, ListWType.winterV, "A", 10);
    Wheel wheel_5 = new Wheel("5", ListWVendor.Cooper, ListWType.offroad, "A", 11);
    Wheel wheel_6 = new Wheel("6", ListWVendor.Goodyear, ListWType.summer, "D", 10);
    Wheel wheel_100 = new Wheel("100", ListWVendor.KAMA, ListWType.offroad, "D", 10);
    Wheel wheel_101 = new Wheel("101", ListWVendor.Goodyear, ListWType.summer, "D", 10);

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws ItemDoubleException {
        storage = new StorageWH();
        storage.putItem(wheel_1);
        storage.putItem(wheel_2);
        storage.putItem(wheel_3);
        storage.putAllItems(List.of(wheel_4, wheel_5, wheel_6));
        analitics = new CachedAnalitycs(new BasicAnalitycs(storage));
    }

    @Test
    void getVendor() {
        Set<ListWVendor> listOfVendorsInWH = analitics.getVendor();
        Assertions.assertEquals(5, listOfVendorsInWH.size());
        System.out.println(analitics.lruCache.stats().toString());
    }

    @Test
    void getItemOnPlace() {
    }

    @Test
    void getAllCnt() {
    }
}