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
        menuList.add(new State(2, "Delete by ID"));
        menuList.add(new State(3, "Update by ID"));
        menuList.add(new State(4, "Read by ID"));
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
                actionAddNewRecord();
                StateContext.contructor(new StateRecords());
                break;
            }
            case 2: {   //delete record by ID
                actionDeleteRecord();
                StateContext.contructor(new StateRecords());
                break;
            }
            case 3: {
                actionUpdateRecord();
                StateContext.contructor(new StateRecords());
                break;
            }
            case 4: {
                actionReadRecord();
                StateContext.contructor(new StateRecords());
                break;
            }
            case 5: {
                actionReadAll();
                StateContext.contructor(new StateRecords());
                break;
            }
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
            record = check.getValue();
        }
    }

    private void actionAddNewRecord(){
        reqInformation();
        getInformation();
        LocalDB.getInstance().addRecord(record);
        System.out.println("Success! ID: " + LocalDB.getInstance().getId());
    }

    private void actionDeleteRecord(){
        reqID("delete");
        if (!getID().isError()) {
            System.out.println("Record: " + LocalDB.getInstance().getRecordAsString(id));
            LocalDB.getInstance().delRecord(id);
            System.out.println("Deleted!");
        }
    }

    private void actionUpdateRecord(){
        reqID("update");
        if (!getID().isError()) {
            reqInformation();
            getInformation();
            LocalDB.getInstance().updateRecord(id, record);
            System.out.println("Success! New record for ID: " + LocalDB.getInstance().getRecordAsString(id));
        }
    }

    private void actionReadRecord(){
        reqID("read");
        if (!getID().isError()) {
            System.out.println("Success! Record found with ID: " + LocalDB.getInstance().getRecordAsString(id));
        }
    }

    private void actionReadAll(){
        LocalDB db = LocalDB.getInstance();
        System.out.println(db.getAllRecord());
    }
}
