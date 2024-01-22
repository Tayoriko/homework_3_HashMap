package practive_v2.state;

import practive_v2.GlobalElements;
import practive_v2.LocalCash;
import practive_v2.LocalDB;
import practive_v2.input.Check;
import practive_v2.input.InputSurname;

import java.util.Set;

public class StateSearch extends StateAbstract {

    private String surnameOne;
    private String surnameTwo;
    private String surname;

    public StateSearch() {
        super();
    }

    @Override
    protected String menuGenerate() {
        menuName = ListState.SEARCH;
        menuList.add(new State(0, "EXIT"));
        menuList.add(new State(1, "Search from A to Surname"));
        menuList.add(new State(2, "Search from Surname to Z"));
        menuList.add(new State(3, "Search from Surname to Surname"));
        menuList.add(new State(9, "BACK"));
        menuString += menuName.getName() + GlobalElements.LN;
        for (State line : menuList) {
            menuString += menuGetLine(line);
        }
        return this.menuString;
    }

    @Override
    protected void exeAction() {
        switch (action) {
            case 0:
                GlobalElements.exit();
                break;
            case 1: {   //search records from A to Surname
                actionSearchFromAToSurname();
                StateContext.contructor(new StateSearch());
                break;
            }
            case 2: {   //search records from Surname to Z
                actionSearchFromSurnameToZ();
                StateContext.contructor(new StateSearch());
                break;
            }
            case 3: {   //search records from Surname to Surname
                actionSearchFromSurnameToSurname();
                StateContext.contructor(new StateSearch());
                break;
            }
            case 9: {   //back to upper layer menu
                StateContext.contructor(new StateMain());
                break;
            }
            default:
                System.out.println("Invalid action: " + action);
                new StateContext(new StateSearch());
                break;
        }
    }

    public void reqInformation() {
        System.out.println(GlobalElements.LN + "Input Surname: ");
        additionalInformation = GlobalElements.getInputString();
    }

    public void getInformation() {
        Check<String> check = new InputSurname().readOneSurname(additionalInformation);
        if (!check.isError()) {
            surname = check.getValue();
        }
    }

    private void actionSearchFromAToSurname() {
        reqInformation();
        getInformation();
        Set<Integer> studentsIDs = LocalCash.getInstance().getStudentSurnameOrEqual(surname, false);
        LocalDB.getInstance().printByIDs(studentsIDs);
        System.out.println("Success!");
    }

    private void actionSearchFromSurnameToZ() {
        reqInformation();
        getInformation();
        Set<Integer> studentsIDs = LocalCash.getInstance().getStudentSurnameOrEqual(surname, true);
        LocalDB.getInstance().printByIDs(studentsIDs);
        System.out.println("Success!");
    }

    private void actionSearchFromSurnameToSurname() {
        reqInformation();
        getInformation();
        surnameOne = surname;
        reqInformation();
        getInformation();
        surnameTwo = surname;
        Set<Integer> studentsIDs = LocalCash.getInstance().getStudentsRange(surnameOne, surnameTwo);
        LocalDB.getInstance().printByIDs(studentsIDs);
        System.out.println("Success!");
    }
}
