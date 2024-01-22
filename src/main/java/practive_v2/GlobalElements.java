package practive_v2;

import practive_v2.state.StateContext;
import practive_v2.state.StateMain;

import java.util.Scanner;

public class GlobalElements {

    public static final String regexName = "^[A-ZА-ЯЁ][a-zа-яё]*(-[A-ZА-ЯЁ][a-zа-яё]*)?$";
    public static final String regexCity = "^[A-Za-zА-Яа-я]+(?:[ -][A-Za-zА-Яа-я]+)*$";
    public static final String regexCourse = "^[a-zA-ZА-Яа-я#0-9+-]{1,10}$";
    public static final String regexOld = "^(?:[1-9]|[1-9][0-9])$";
    public static final String LN = String.valueOf('\n');
    public static final String filename = "pratice_v2";

    public static String getInputString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void exit(){
        System.exit(-1);
    }
}
