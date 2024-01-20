package practive_v2.state;

import java.util.HashSet;
import java.util.Set;

public abstract class StateAbstract {
    Set<State> menuSet = new HashSet<>();
    protected String menuString = "";

    protected abstract String genMenu();
    protected void printMenu(){
        System.out.println(menuString);
    }
    protected String getMenu(State line){
        return line.getId() + ". " + line.getText();
    }

}
