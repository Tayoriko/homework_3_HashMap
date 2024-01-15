package practice.state;

import practice.Main;
import practice.PracticeLocalDB;
import practice.PracticeSearchCash;

import java.util.Set;

public class StateMenuSearch extends AbstractState{

    public StateMenuSearch() {
        super();
    }

    @Override
    protected void printMenu(){
        //create and print to console text menu for navigation
        String menu = "======================================" + LN;
        menu += "-> Search menu. Select action №:" + LN;;
        menu += "0. EXIT" + LN;
        menu += "1. Search from start to Surname" + LN;
        menu += "2. Search from Surname to end" + LN;
        menu += "3. Search from Surname to Surname" + LN;
        menu += "..." + LN;
        menu += "9. Back to main menu" + LN;
        menu += "Input selected action №: ";
        System.out.print(menu);
    }

    @Override
    protected void genActions() {
        actions.add(ActionType.SEARCH);
        actions.add(ActionType.SEARCH);
        actions.add(ActionType.SEARCH);
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
        } else if(type == ActionType.SEARCH) {
            switch (action) {
                case 1:{
                    String surname = reqOneSurname();
                    Set<Integer> studentsIDs = PracticeSearchCash.getInstance().getStudentSurnameOrEqual(surname, false);
                    PracticeLocalDB.getInstance().printByIDs(studentsIDs);
                    new Context(new StateMenuSearch());
                    break;
                }
                case 2:{
                    String surname = reqOneSurname();
                    Set<Integer> studentsIDs = PracticeSearchCash.getInstance().getStudentSurnameOrEqual(surname, true);
                    PracticeLocalDB.getInstance().printByIDs(studentsIDs);
                    new Context(new StateMenuSearch());
                    break;
                }
                case 3:{
                    String[] surname = reqTwoSurname();
                    Set<Integer> studentsIDs = PracticeSearchCash.getInstance().getStudentsRange(surname[0], surname[1]);
                    PracticeLocalDB.getInstance().printByIDs(studentsIDs);
                    new Context(new StateMenuSearch());
                    break;
                }
                default: {
                    System.out.println("Unknown command: " + action + ", back to main menu");
                    new Context(new StateMenuMain());
                    break;
                }
            }
            new Context(new StateMenuSearch());
        } else {
            System.out.println("Inccorrect action: " + action + " - back to main menu.");
            new Context(new StateMenuMain());
        }
    }
}
