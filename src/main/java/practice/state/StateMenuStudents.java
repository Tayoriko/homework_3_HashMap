package practice.state;

import practice.PracticeLocalDB;
import practice.OneStudent;

public class StateMenuStudents extends AbstractState {

    public StateMenuStudents() {
        super();
    }

    @Override
    protected void printMenu(){
        //create and print to console text menu for navigation
        String menu = "======================================" + LN;
        menu += "-> Student menu. Select action №:" + LN;;
        menu += "0. EXIT" + LN;
        menu += "1. Add new student" + LN;
        menu += "2. Delete by ID" + LN;
        menu += "3. Update by ID" + LN;
        menu += "4. Read by ID" + LN;
        menu += "5. Show all records" + LN;
        menu += "..." + LN;
        menu += "9. Back to main menu" + LN;
        menu += "Input selected action №: ";
        System.out.print(menu);
    }

    @Override
    protected void genActions() {
        actions.add(ActionType.WRITE);
        actions.add(ActionType.WRITE);
        actions.add(ActionType.WRITE);
        actions.add(ActionType.READ);
        actions.add(ActionType.READ_ALL);
        while (actions.size() < 9) actions.add(ActionType.VOID);
        actions.add(ActionType.BACK);
    }

    @Override
    protected void selectAction(int action) {
        ActionType type = getAction(action);
        OneStudent student;
        PracticeLocalDB db = PracticeLocalDB.getInstance();
        if (type == ActionType.EXIT) {
            System.exit(-1);
        } else if (type == ActionType.BACK) {
            new Context(new StateMenuMain());
        } else if (type == ActionType.NAV) {
            switch (action){
                case 9: new Context(new StateMenuMain()); break;
                default:{
                    System.out.println("Unknown command: " + action + ", back to main menu");
                    new Context(new StateMenuMain());
                    break;
                }
            }
        } else if (type == ActionType.WRITE) {
            switch (action){
                case 1: {
                    student = reqStudent();
                    if (!student.isFault()) db.addStudent(student);
                    break;
                }
                case 2: {
                    int id = reqID();
                    if (id != 0) db.deleteStudent(id);
                    else System.out.println("Operation was cancelled");
                    break;
                }
                case 3: {
                    int id = reqID();
                    student = reqStudent();
                    if (id != 0)
                        if (!student.isFault())db.updateStudent(id, student);
                    else System.out.println("Operation was cancelled");
                    break;
                }
                default:{
                    System.out.println("Unknown command: " + action + ", back to main menu");
                    new Context(new StateMenuMain());
                    break;
                }
            }
            new Context(new StateMenuStudents());
        } else if (type == ActionType.READ){
            int id = reqID();
            OneStudent studentByID = db.getStudentByID(id);
            if (studentByID != null) System.out.println(studentByID.toString());
            new Context(new StateMenuStudents());
        } else if (type == ActionType.READ_ALL) {
            for (Integer record : PracticeLocalDB.getInstance().getAll().keySet()){
                System.out.println(PracticeLocalDB.getInstance().getAll().get(record).toString());
            }
            new Context(new StateMenuStudents());
        } else {
            System.out.println("Inccorrect action: " + action + " - back to main menu.");
            new Context(new StateMenuMain());
        }
    }
}
