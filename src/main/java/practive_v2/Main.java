package practive_v2;

import practive_v2.file.ImportExport;
import practive_v2.state.StateContext;
import practive_v2.state.StateMain;

public class Main {
    public static StateContext context = new StateContext(new StateMain());
    public static void main(String[] args) {
        ImportExport.importBase(GlobalElements.filename);
        while (true) {
            context.updateState();
        }
    }
}
