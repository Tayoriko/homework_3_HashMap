package practive_v2.state;


public class StateContext {
    private static StateAbstract state;

    public StateContext(StateAbstract state){
        contructor(state);
    }

    public static void contructor(StateAbstract nextState){
        if (state == null || nextState == null){
            state = new StateMain();
        } else {
            state = nextState;
        }
    }

    public void updateState(){
        state.init();
        state.waitAction();
        state.exeAction();
    }

    public StateAbstract getState() {
        return state;
    }
}
