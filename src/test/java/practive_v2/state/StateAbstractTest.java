package practive_v2.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import practive_v2.GlobalElements;
import practive_v2.LocalDB;
import practive_v2.RecordsInBase;
import practive_v2.input.Check;
import practive_v2.input.InputInteger;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

class StateAbstractTest {

    StateContext context = new StateContext(new StateMain());

    @DisplayName("Test print menu components")
    @Test
    void printMainMenuTest() {
        context.getState().init();
        String menu = context.getState().menuName.getName() + GlobalElements.LN
                + "0. EXIT" + GlobalElements.LN
                + "1. Students" + GlobalElements.LN
                + "2. Statistics" + GlobalElements.LN
                + "3. Search" + GlobalElements.LN
                + "8. LOAD" + GlobalElements.LN
                + "9. SAVE" + GlobalElements.LN;
        Assertions.assertEquals(menu, context.getState().getMenuString());
    }

    @DisplayName("Test check action")
    @Test
    void checkActionTest(){
        context.getState().init();
        Check<Integer> check = new InputInteger().readInteger("2");
        int value = context.getState().checkAction(check);
        Assertions.assertEquals(2, value);
        check = new InputInteger().readInteger("21");
        value = context.getState().checkAction(check);
        Assertions.assertEquals(-1, value);
    }

    @DisplayName("Test get action")
    @Test
    void getActionTest(){
        context.getState().init();
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        context.getState().waitAction();
        // Восстанавливаем System.in
        System.setIn(System.in);
        // Проверка
        Assertions.assertEquals(1, context.getState().action);
    }

    @DisplayName("Test switch Main->Record")
    @Test
    void switchTest() {
        LocalDB.getInstance().clear();
        context.getState().init();
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        context.getState().waitAction();
        System.setIn(System.in);
        context.getState().exeAction();
        context.getState().init();
        Assertions.assertEquals(ListState.RECORDS, context.getState().menuName);
    }

    @DisplayName("Test back switch Record->Main")
    @Test
    void switchBackTest(){
        switchTest();
        String input = "9";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        context.getState().waitAction();
        System.setIn(System.in);
        context.getState().exeAction();
        context.getState().init();
        Assertions.assertEquals(ListState.MAIN, context.getState().menuName);
    }

    @DisplayName("Test add new record from menu")
    @Test
    public void addNewRecordTest(){
        switchTest();
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        context.getState().waitAction();
        input = "Николай, Басков, Москва, DevOps, 64";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        context.getState().reqInformation();
        System.setIn(System.in);
        context.getState().getInformation();
        RecordsInBase record =  context.getState().getRecord();
        LocalDB.getInstance().addRecord(record);
        System.out.println("Succes! ID: " + LocalDB.getInstance().getId());
        LocalDB db = LocalDB.getInstance();
        Assertions.assertEquals(1, db.getSize());
    }

    @DisplayName("Test delete record from menu")
    @Test
    public void delRecordTest(){
        addNewRecordTest();
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        context.getState().waitAction();
        input = LocalDB.getInstance().getId().toString();
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        context.getState().reqID("delete");
        System.setIn(System.in);
        if (!context.getState().getID().isError()) LocalDB.getInstance().delRecord(context.getState().id);
        StateContext.contructor(new StateRecords());
        Assertions.assertEquals(0, LocalDB.getInstance().getSize());
    }

    @DisplayName("Teset for update record")
    @Test
    public void updateRecordTest(){
        addNewRecordTest();
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        context.getState().waitAction();
        input = LocalDB.getInstance().getId().toString();
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        context.getState().reqID("update");
        if (!context.getState().getID().isError()) {
            input = "Николай, Хуясков, Москва, DevOps, 64";
            in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            context.getState().reqInformation();
            System.setIn(System.in);
            context.getState().getInformation();
            LocalDB.getInstance().updateRecord(context.getState().id, context.getState().record);
            System.out.println("Success! New record for ID: " + LocalDB.getInstance().getRecordAsString(context.getState().id));
        } else System.out.println("error while update record");
        Assertions.assertEquals("11, "+ input, LocalDB.getInstance().getRecordAsString(11));
    }
}