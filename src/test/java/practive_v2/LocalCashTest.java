package practive_v2;

import org.junit.jupiter.api.Test;
import practive_v2.file.ImportExport;
import practive_v2.input.Check;
import practive_v2.input.InputRecord;

import static org.junit.jupiter.api.Assertions.*;

class LocalCashTest {
    LocalDB db = LocalDB.getInstance();
    LocalCash cash = LocalCash.getInstance();

    @Test
    void addStudentToTree() {
        cash.addStudentToTree(1, "Макет");
        cash.addStudentToTree(2, "Колетт");
        String data = "Николай, Басков, Москва, DevOps, 64";
        Check<RecordsInBase> check = new InputRecord().checkRecordInput(data);
        LocalCash.getInstance().addStudentToTree(LocalDB.getInstance().getId(), check.getValue().getSurname());
        System.out.println(cash.getDbCash().size());
    }

    @Test
    void deleteStudentFromTree() {
    }

    @Test
    void updateStudentInTree() {
    }

    @Test
    void getStudentSurnameOrEqual() {
    }

    @Test
    void getStudentsRange() {
    }
}