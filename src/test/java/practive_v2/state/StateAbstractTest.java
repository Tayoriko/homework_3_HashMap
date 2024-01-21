package practive_v2.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import practive_v2.GlobalElements;
import practive_v2.LocalDB;
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
        LocalDB db = LocalDB.getInstance();
        Assertions.assertEquals(1, db.getSize());


    }
}