package practice.state;

import practice.Main;
import practice.PracticeLocalDB;

import java.util.Map;

public class StateMenuStatistics extends AbstractState{
    public StateMenuStatistics() {
        super();
    }

    @Override
    protected void printMenu(){
        //create and print to console text menu for navigation
        String menu = "======================================" + LN;
        menu += "-> Statistics menu. Select action №:" + LN;;
        menu += "0. EXIT" + LN;
        menu += "1. Statistics by courses" + LN;
        menu += "2. Statistics by cities" + LN;
        menu += "..." + LN;
        menu += "9. Back to main menu" + LN;
        menu += "Input selected action №: ";
        System.out.print(menu);
    }

    @Override
    protected void genActions() {
        actions.add(ActionType.STAT);
        actions.add(ActionType.STAT);
        while (actions.size() < 9) actions.add(ActionType.VOID);
        actions.add(ActionType.BACK);
    }

    @Override
    protected void selectAction(int action) {
        ActionType type = getAction(action);
        PracticeLocalDB db = PracticeLocalDB.getInstance();
        if (type == ActionType.EXIT) {
            System.exit(-1);
        } else if (type == ActionType.BACK) {
            new Context(new StateMenuMain());
        } else if(type == ActionType.STAT) {
            switch (action) {
                case 1:{
                    Map<String, Integer> stat = db.getStatByCourse();
                    System.out.println("Statistics by Courses:");
                    System.out.println(stat.toString());
                    new Context(new StateMenuStatistics());
                }
                case 2:{
                    Map<String, Integer> stat = db.getStatByCities();
                    System.out.println("Statistics by Cities:");
                    System.out.println(stat.toString());
                    new Context(new StateMenuStatistics());
                }
                default: {
                    System.out.println("Unknown command: " + action + ", back to main menu");
                    new Context(new StateMenuMain());
                    break;
                }
            }
            new Context(new StateMenuStatistics());
        } else {
            System.out.println("Inccorrect action: " + action + " - back to main menu.");
            new Context(new StateMenuMain());
        }
    }
}
