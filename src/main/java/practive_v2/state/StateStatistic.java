package practive_v2.state;

import practive_v2.GlobalElements;
import practive_v2.LocalDB;

import java.util.Map;

public class StateStatistic extends StateAbstract{

    public StateStatistic(){
        super();
    }

    @Override
    protected String menuGenerate() {
        menuName = ListState.STATS;
        menuList.add(new State(0, "EXIT"));
        menuList.add(new State(1, "Statistic by Courses"));
        menuList.add(new State(2, "Statistic by Cities"));
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
            case 1: {   //get statistics by Courses
                actionStatByCourses();
                StateContext.contructor(new StateStatistic());
                break;
            }
            case 2: {   //get statistics by Cities
                actionStatByCities();
                StateContext.contructor(new StateStatistic());
                break;
            }
            case 9: {   //back to upper layer menu
                StateContext.contructor(new StateMain());
                break;
            }
            default:
                System.out.println("Invalid action: " + action);
                new StateContext(new StateStatistic());
                break;
        }
    }

    @Override
    protected void reqInformation() {

    }

    @Override
    protected void getInformation() {

    }

    public void actionStatByCourses(){
        Map<String, Integer> stat = LocalDB.getInstance().getStatByCourse();
        System.out.println("Statistics by Courses:");
        System.out.println(stat.toString());
    }

    public void actionStatByCities(){
        Map<String, Integer> stat = LocalDB.getInstance().getStatByCities();
        System.out.println("Statistics by Courses:");
        System.out.println(stat.toString());
    }
}
