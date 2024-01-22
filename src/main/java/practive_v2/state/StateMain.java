package practive_v2.state;

import practive_v2.GlobalElements;
import practive_v2.file.ImportExport;

public class StateMain extends StateAbstract {

    public StateMain(){
        super();
    }

    @Override
    protected String menuGenerate() {
        menuName = ListState.MAIN;
        menuList.add(new State(0, "EXIT"));
        menuList.add(new State(1, "Students"));
        menuList.add(new State(2, "Statistics"));
        menuList.add(new State(3, "Search"));
        menuList.add(new State(8, "LOAD"));
        menuList.add(new State(9, "SAVE"));
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
            case 1: StateContext.contructor(new StateRecords()); break;
            case 2: StateContext.contructor(new StateStatistic()); break;
            case 3: StateContext.contructor(new StateSearch()); break;
            case 8: {
                ImportExport.importBase(GlobalElements.filename);
                StateContext.contructor(new StateMain());
                break;
            }
            case 9: {
                ImportExport.exportBase(GlobalElements.filename);
                StateContext.contructor(new StateMain());
                break;
            }
            default:
                System.out.println("Invalid action: " + action);
                new StateContext(new StateMain());
                break;
        }
    }

    @Override
    protected void reqInformation() {

    }

    @Override
    protected void getInformation() {

    }

}
