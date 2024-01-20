package practive_v2.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateAbstractTest {

    @Test
    void printMainMenuTest() {
        StateAbstract state = new StateMain();
        String menu = ""
                + "0. EXIT"
                + "1. Students"
                + "2. Statistics"
                + "9. SAVE";
        Assertions.assertEquals(menu, state.genMenu());
    }

}