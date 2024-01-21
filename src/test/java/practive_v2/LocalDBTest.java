package practive_v2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import practive_v2.input.Check;
import practive_v2.input.InputRecord;

import static org.junit.jupiter.api.Assertions.*;

class LocalDBTest {
    @DisplayName("Test add record")
    @Test
    void addRecordTest() {
        LocalDB db = LocalDB.getInstance();
        String data = "Николай, Басков, Москва, DevOps, 64";
        Check<RecordsInBase> check = new InputRecord().checkRecordInput(data);
        db.addRecord(check.getValue());
        check = new InputRecord().checkRecordInput(data);
        db.addRecord(check.getValue());
        check = new InputRecord().checkRecordInput(data);
        if (!check.isError()) db.addRecord(check.getValue());
        data = "Николай-1, Басков, Москва, DevOps, 64";
        check = new InputRecord().checkRecordInput(data);
        if (!check.isError()) db.addRecord(check.getValue());
        Assertions.assertEquals(3, db.getSize());
    }

    @DisplayName("Test delete record")
    @Test
    void delRecordTest() {
        LocalDB db = LocalDB.getInstance();
        String data = "Николай, Басков, Москва, DevOps, 64";
        Check<RecordsInBase> check = new InputRecord().checkRecordInput(data);
        db.addRecord(check.getValue());
        db.addRecord(check.getValue());
        Assertions.assertEquals(2, db.getSize());
        db.delRecord(1);
        Assertions.assertEquals(1, db.getSize());
    }

    @DisplayName("Test get sorted Records as String")
    @Test
    void getAllRecordTest(){
        LocalDB db = LocalDB.getInstance();
        String data = "Николай, Басков, Москва, DevOps, 64";
        for (int i = 0; i < 5; i++) {
            Check<RecordsInBase> check = new InputRecord().checkRecordInput(data);
            db.addRecord(check.getValue());
        }
        Assertions.assertEquals(5, db.getSize());
        String result = db.getAllRecord();
        System.out.println(result);
    }
}