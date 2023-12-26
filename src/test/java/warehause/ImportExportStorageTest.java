package warehause;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import warehause.exceptions.ItemDoubleException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImportExportStorageTest {

    private StorageWH storage;
    private ImportExportStorage operationIO;
    Wheel wheel_1 = new Wheel("1", ListWVendor.Bridgestone, ListWType.summer, "A", 10);
    Wheel wheel_2 = new Wheel("2", ListWVendor.Continental, ListWType.winterS, "A", 10);
    Wheel wheel_3 = new Wheel("3", ListWVendor.Yokohama, ListWType.allSeason, "C", 10);
    Wheel wheel_4 = new Wheel("4", ListWVendor.Cooper, ListWType.winterV, "A", 10);
    Wheel wheel_5 = new Wheel("5", ListWVendor.KAMA, ListWType.offroad, "D", 10);
    Wheel wheel_6 = new Wheel("6", ListWVendor.Goodyear, ListWType.summer, "D", 10);
    Wheel wheel_100 = new Wheel("100", ListWVendor.KAMA, ListWType.offroad, "D", 10);
    Wheel wheel_101 = new Wheel("101", ListWVendor.Goodyear, ListWType.summer, "D", 10);

    @BeforeEach
    void setUp() throws ItemDoubleException {
        storage = new StorageWH();
        operationIO = new ImportExportStorage();
    }

    @Test
    void importCSV() {
        operationIO.importCSV(storage, "import");
        Assertions.assertEquals(14, storage.getAllItem().size());
    }

    @Test
    void exportCSV() throws IOException {
        operationIO.importCSV(storage, "import");
        int sizeImport = storage.getAllItem().size();
        operationIO.exportCSV(storage, "export");
        operationIO.importCSV(storage, "export");
        int sizeExport = storage.getAllItem().size();
        Assertions.assertEquals(sizeImport, sizeExport);
    }
}