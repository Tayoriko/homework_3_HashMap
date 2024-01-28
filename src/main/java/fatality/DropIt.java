package fatality;


import practive_v2.RecordsInBase;
import practive_v2.input.Check;
import practive_v2.input.InputRecord;

import java.util.ArrayList;
import java.util.List;

public class DropIt {

    public static List<Object> objectList = new ArrayList<>();

    public static void main(String[] args) {
        while(true){
            objectList.add(new Object());
        }
    }
}
