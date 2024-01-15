package practice.state;

import practice.Main;
import practice.PracticeFileDB;

import java.util.ArrayList;
import java.util.List;

public class StateMenuMain extends AbstractState{

    public StateMenuMain() {
        super();
    }

    @Override
    protected void printMenu(){
        //create and print to console text menu for navigation
        String menu = "======================================" + LN;
        menu += "-> Main menu. Select action №:" + LN;;
        menu += "0. EXIT" + LN;
        menu += "1. Students" + LN;
        menu += "2. Statisctics" + LN;
        menu += "3. Search" + LN;
        menu += "4. Read DB from file" + LN;
        menu += "5. Save DB to file" + LN;
        menu += "Input selected action №: ";
        System.out.print(menu);
    }

    @Override
    protected void genActions() {
        actions.add(ActionType.NAV);
        actions.add(ActionType.NAV);
        actions.add(ActionType.NAV);
        actions.add(ActionType.LOAD);
        actions.add(ActionType.SAVE);
        while (actions.size() <= 9) actions.add(ActionType.VOID);
    }

    @Override
    protected void selectAction(int action) {
        ActionType type = getAction(action);
        if (type == ActionType.EXIT){
            System.exit(-1);
        } else if (type == ActionType.NAV) {
            switch (action){
                case 1: new Context(new StateMenuStudents()); break;
                case 2: new Context(new StateMenuStatistics()); break;
                case 3: new Context(new StateMenuSearch()); break;
                default:{
                    System.out.println("Unknown command: " + action + ", back to main menu");
                    new Context(new StateMenuMain());
                    break;
                }
            }
            new Context(new StateMenuStatistics());
        } else if(type == ActionType.LOAD) {
            new PracticeFileDB().loadFromFile(Main.filename);
            new Context(new StateMenuMain());
        } else if(type == ActionType.SAVE) {
            new PracticeFileDB().saveToFile(Main.filename);
            new Context(new StateMenuMain());
        } else {
            System.out.println("Inccorrect action: " + action + " - back to main menu.");
            new Context(new StateMenuMain());
        }
    }
}
