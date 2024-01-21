package practive_v2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import practive_v2.input.*;

public class inputComplexTest {

    @DisplayName("Check input action code")
    @Test
    void readIntegerTest(){
        String data = "1";
        Check<String> check = new InputInteger().readInteger(data);
        Assertions.assertEquals(false, check.isError());
        Assertions.assertEquals(1, check.getValue());
        data = "o1";
        check = new InputInteger().readInteger(data);
        Assertions.assertEquals(true, check.isError());
        Assertions.assertEquals(-1, check.getValue());
    }

    @DisplayName("Check input Name/Surname")
    @Test
    void readOneSurnameTest() {
        String data = "Oleg";
        Check<String> check = new InputSurname().readOneSurname(data);
        Assertions.assertEquals(false, check.isError());
        data = "Николаев-Петренко";
        check = new InputSurname().readOneSurname(data);
        Assertions.assertEquals(false, check.isError());
        data = "Ошибка-2";
        check = new InputSurname().readOneSurname(data);
        Assertions.assertEquals(true, check.isError());
    }

    @DisplayName("Check input City")
    @Test
    void readCityTest() {
        String data = "Moscow";
        Check<String> check = new InputCity().readCity(data);
        Assertions.assertEquals(false, check.isError());
        data = "Москва-на-Х";
        check = new InputCity().readCity(data);
        Assertions.assertEquals(false, check.isError());
        data = "Ошибка-2";
        check = new InputCity().readCity(data);
        Assertions.assertEquals(true, check.isError());
    }

    @DisplayName("Check input Course")
    @Test
    void readCourseTest() {
        String data = "HTML5";
        Check<String> check = new InputCourse().readCourse(data);
        Assertions.assertEquals(false, check.isError());
        data = "DevOps";
        check = new InputCourse().readCourse(data);
        Assertions.assertEquals(false, check.isError());
        data = "Какой-то бред 6";
        check = new InputCourse().readCourse(data);
        Assertions.assertEquals(true, check.isError());
    }

    @DisplayName("Check input standart Record")
    @Test
    void readRecordTest(){
        String data = "Николай, Басков, Москва, DevOps, 64";
        Check<RecordsInBase> check = new InputRecord().checkRecordInput(data);
        Assertions.assertEquals(false, check.isError());
        Assertions.assertEquals(64, check.getValue().getOld());
    }

    @DisplayName("Check input from file Record")
    @Test
    void readFileRecordTest(){
        String data = "256, Николай, Басков, Москва, DevOps, 64";
        Check<RecordsInBase> check = new InputRecord().checkRecordFile(data);
        Assertions.assertEquals(false, check.isError());
        Assertions.assertEquals(256, check.getValue().getId());
    }
}
