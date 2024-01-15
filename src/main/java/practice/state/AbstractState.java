package practice.state;

import practice.OneStudent;
import practice.validator.ValidInteger;
import practice.validator.ValidStudent;
import practice.validator.ValidSurname;

import java.util.*;

public abstract class AbstractState {

    public AbstractState() {
        handle();
    }

    public void handle(){
        actions = new ArrayList<>();
        actions.add(ActionType.EXIT);
        genActions();
        printMenu();
        state = getNewState();
        selectAction(state);
    }

    public static final String LN = String.valueOf('\n');
    private int state;

    protected List<ActionType> actions;

    protected abstract void genActions();

    protected String readInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int getNewState(){
        String code = readInput();
        return new ValidInteger().readInteger(code);
    }

    public OneStudent getNewStudent(){
        String data = readInput();
        return new OneStudent(new ValidStudent().checkDataOfStudent(data));
    }

    public String getOneSurname(){
        String data = readInput();
        return new ValidSurname().checkOneSurname(data);
    }

    public String[] getTwoSurname(){
        String data = readInput();
        return new ValidSurname().checkTwoSurname(data);
    }

    public ActionType getAction(Integer id){
        if (id >= 0 && id <= 9) return actions.get(id);
        else return ActionType.VOID;
    }

    protected int reqID(){
        System.out.print(LN + "Please, input ID for delete or 0 for Cancel: ");
        Integer id = new ValidInteger().readInteger(readInput());
        return id;
    }

    protected OneStudent reqStudent(){
        System.out.println(LN + "Please, input: Name, Surname, City, Course, Years Old: ");
        return getNewStudent();
    }

    protected String reqOneSurname(){
        System.out.println(LN + "Please, input: Surname: ");
        return getOneSurname();
    }

    protected String[] reqTwoSurname(){
        System.out.println(LN + "Please, input: Surname to start, surname to finish: ");
        return getTwoSurname();
    }

    protected abstract void printMenu();

    protected abstract void selectAction(int action);


}
