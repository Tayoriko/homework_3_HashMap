package practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import practice.state.ActionType;
import practice.validator.ValidInteger;
import practice.validator.ValidStudent;

import java.util.ArrayList;
import java.util.List;

class MainTest {

    @DisplayName("Test get action")
    @Test
    public void readActionTest(){
        int value = new ValidInteger().readInteger(" 9 ");
        Assertions.assertEquals(9, value);
    }

    @DisplayName("Test get student")
    @Test
    public void readStudentTest(){
        String source = "Oleg, Maket, Lipetsk, DevOps, 19";
        String result = new ValidStudent().checkDataOfStudent(source);
        Assertions.assertEquals(source, result);
    }


    @DisplayName("Test add student to DB")
    @Test
    public void addOneStudentTest(){
        String source = "Oleg, Maket, Lipetsk, DevOps, 19";
        String result = new ValidStudent().checkDataOfStudent(source);
        OneStudent student = new OneStudent(result);
        PracticeLocalDB db = PracticeLocalDB.getInstance();
        db.addStudent(student);
        Assertions.assertEquals(1, db.getCounter());
    }

    @DisplayName("Test delete student from DB")
    @Test
    public void deleteOneStudentTest(){
        PracticeLocalDB db = PracticeLocalDB.getInstance();
        Assertions.assertEquals(0, db.getSize());
        String source = "Oleg, Maket, Lipetsk, DevOps, 19";
        String result = new ValidStudent().checkDataOfStudent(source);
        OneStudent student = new OneStudent(result);
        db.addStudent(student);
        source = "Sarah, Connor, New-York, HTML5, 25";
        result = new ValidStudent().checkDataOfStudent(source);
        student = new OneStudent(result);
        db.addStudent(student);
        Integer id = 1;
        db.deleteStudent(id);
        Assertions.assertEquals(1, db.getSize());
    }

    @DisplayName("WTF")
    @Test
    public void wtf(){
        List<ActionType> actions = new ArrayList<>();
        actions.add(ActionType.EXIT);
        System.out.println(actions.stream().toString());
    }

}