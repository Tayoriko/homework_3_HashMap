package practice.state;

import practice.Main;

public class Context {
    private AbstractState state;

    public Context (AbstractState state){
        if (this.state == null || state == null){
            this.state = new StateMenuMain();
        } else {
            this.state = state;
        }
    }

    public void updateState(){
        state.handle();
    }

}
