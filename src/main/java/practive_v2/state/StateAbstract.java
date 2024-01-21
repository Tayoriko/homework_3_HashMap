package practive_v2.state;

import practive_v2.GlobalElements;
import practive_v2.input.Check;
import practive_v2.input.InputInteger;

import java.util.LinkedList;
import java.util.List;

public abstract class StateAbstract {
    List<State> menuList = new LinkedList<>();
    protected String menuString = "";
    protected ListState menuName;
    protected int action;
    protected String additionalInformation;

    public StateAbstract(){

    }

    public void init(){
        menuGenerate();
        menuPrint();
    }

    public void waitAction(){
        action = checkAction(getAction());
    }

    protected abstract String menuGenerate();
    protected abstract void exeAction();
    protected abstract void reqInformation();
    protected abstract void getInformation();
    protected Integer checkAction(Check action) {
        if (!action.isError()) {
            if (menuList.stream().filter(line -> line.getId() == (int) action.getValue()).findFirst().isPresent())
            {
                return (int) action.getValue();
            }
        }
        return -1;
    }

    protected Check getAction(){
        String data = GlobalElements.getInputString();
        return new InputInteger().readInteger(data);
    }

    protected void menuPrint(){
        System.out.println(menuString);
        System.out.print("Input selected action: ");
    }

    protected String menuGetLine(State line){
        return line.getId() + ". " + line.getText() + GlobalElements.LN;
    }

    public String getMenuString() {
        return menuString;
    }

}
