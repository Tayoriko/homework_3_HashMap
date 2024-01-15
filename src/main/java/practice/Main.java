package practice;

import practice.state.Context;
import practice.state.StateMenuMain;

public class Main {
    public static Context context;
    public static final String filename = "practiceDB";
    public static void main(String[] args) {
        PracticeFileDB fileDB = new PracticeFileDB();
        fileDB.loadFromFile(filename);
        context = new Context(new StateMenuMain());
        while (true) {
            context.updateState();
        }
    }
}
