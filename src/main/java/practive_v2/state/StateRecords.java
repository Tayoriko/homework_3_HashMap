package practive_v2.state;

import practive_v2.GlobalElements;
import practive_v2.LocalDB;
import practive_v2.RecordsInBase;
import practive_v2.input.Check;
import practive_v2.input.InputRecord;

public class StateRecords extends StateAbstract {
    public StateRecords(){
        super();
    }

    @Override
    protected String menuGenerate() {
        menuName = ListState.RECORDS;
        menuList.add(new State(0, "EXIT"));
        menuList.add(new State(1, "Add new record"));
        menuList.add(new State(2, "Update by ID"));
        menuList.add(new State(3, "Read by ID"));
        menuList.add(new State(4, "Delete by ID"));
        menuList.add(new State(5, "Show all records"));
        menuList.add(new State(9, "BACK"));
        menuString += menuName.getName() + GlobalElements.LN;
        for (State line : menuList){
            menuString += menuGetLine(line);
        }
        return this.menuString;
    }

    @Override
    protected void exeAction() {
        switch (action){
            case 0: GlobalElements.exit(); break;
            case 1: {   //add new record to database
                    reqInformation();
                    getInformation();
                    break;
                }
            case 2: break;
            case 3: break;
            case 4: break;
            case 5: break;
            case 9: StateContext.contructor(new StateMain()); break;
            default:
                System.out.println("Invalid action: " + action);
                new StateContext(new StateMain());
                break;
        }
    }

    public void reqInformation(){
        System.out.println(GlobalElements.LN + "Input new record (Name, Surname, City, Course, Old): ");
        additionalInformation = GlobalElements.getInputString();
    }

    public void getInformation(){
        Check<RecordsInBase> check = new InputRecord().checkRecordInput(additionalInformation);
        if (!check.isError()) {
            LocalDB db = LocalDB.getInstance();
            db.addRecord(check.getValue());
        }
    }
}
