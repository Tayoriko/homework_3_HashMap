package practive_v2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import practive_v2.input.Check;
import practive_v2.input.InputRecord;

import static org.junit.jupiter.api.Assertions.*;

class LocalDBTest {
    @Test
    void addRecord() {
        LocalDB db = LocalDB.getInstance();
        String data = "Николай, Басков, Москва, DevOps, 64";
        Check<RecordsInBase> check = new InputRecord().checkRecordInput(data);
        db.addRecord(check.getValue());
        db.addRecord(check.getValue());
        if (!check.isError()) db.addRecord(check.getValue());
        data = "Николай-1, Басков, Москва, DevOps, 64";
        check = new InputRecord().checkRecordInput(data);
        if (!check.isError()) db.addRecord(check.getValue());
        Assertions.assertEquals(3, db.getSize());
    }
}