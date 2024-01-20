package practive_v2.state;

import java.util.Map;
import java.util.Set;

public class StateMain extends StateAbstract {

    @Override
    protected void printMenu() {

    }

    @Override
    protected String genMenu() {
        String menu = "";
        menuSet.add(new State(0, "EXIT"));
        menuSet.add(new State(1, "Students"));
        menuSet.add(new State(2, "Statistics"));
        menuSet.add(new State(9, "SAVE"));
        for (State line : menuSet){
            menuString += getMenu(line);
        }
        return menu;
    }
}
