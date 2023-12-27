package students;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ScoreDBTest {

    private ScoreDB scoreDB;
    private ScoreDate date_1 = new ScoreDate(2023, 11, 1, 10, 10);
    private ScoreDate date_2 = new ScoreDate(2023, 11, 1, 10, 11);
    private ScoreDate date_3 = new ScoreDate(2023, 11, 1, 10, 12);
    private ScoreDate date_4 = new ScoreDate(2023, 11, 1, 10, 13);
    private ScoreDate date_5 = new ScoreDate(2023, 11, 1, 10, 14);
    private ScoreDate date_6 = new ScoreDate(2023, 11, 1, 10, 15);
    private ScoreDate date_7 = new ScoreDate(2023, 11, 1, 10, 16);
    private ScoreDate date_8 = new ScoreDate(2023, 11, 1, 10, 17);
    private Student student_1 = new Student("1_Anna", "S");
    private Student student_2 = new Student("2_Sergey", "D");
    private Student student_3 = new Student("3_Dmitry", "K");
    private Student student_4 = new Student("4_Oleg", "R");
    private Student student_5 = new Student("5_Mia", "L");
    private Student student_6 = new Student("6_Sveta", "S");
    private Score score_1 = new Score(student_1, ListSubject.Alg, 4, date_1);
    private Score score_2 = new Score(student_1, ListSubject.Alg, 5, date_2);
    private Score score_3 = new Score(student_2, ListSubject.Alg, 5, date_3);
    private Score score_4 = new Score(student_3, ListSubject.Phis, 5, date_4);
    private Score score_5 = new Score(student_4, ListSubject.Chem, 3, date_5);
    private Score score_6 = new Score(student_5, ListSubject.Alg, 2, date_6);
    private Score score_7 = new Score(student_6, ListSubject.Geom, 1, date_7);

    @BeforeEach
    void setUp() {
        scoreDB = new ScoreDB();
        scoreDB.addScore(score_1);
        scoreDB.addScore(score_2);
        scoreDB.addScore(score_3);
        scoreDB.addScore(score_4);
        scoreDB.addScore(score_5);
        scoreDB.addScore(score_6);
        scoreDB.addScore(score_7);

    }

    @Test
    void addScore() {
        score_1.setScore(5);
        scoreDB.addScore(score_1);
        scoreDB.addScore(score_1);
        Score test = scoreDB.getScore(student_1, ListSubject.Alg);
        Assertions.assertEquals(5, test.getScore());
        Assertions.assertEquals(4, test.getCounter());
    }

    @Test
    void getScore() {
        Score test = scoreDB.getScore(student_1, ListSubject.Alg);
        Assertions.assertEquals(student_1, test.getStudent());
        Assertions.assertEquals(ListSubject.Alg, test.getSubject());
    }


    @Test
    void getAverageScoreForSubject() {
        scoreDB.addScore(score_1);
        scoreDB.addScore(score_3);
        Double avgScore = scoreDB.getAverageScoreForSubject(ListSubject.Alg);
        Assertions.assertEquals(5.0, avgScore);

    }

    @Test
    void lastFiveStudentsWithExcellentMarkOnAnySubject() {
        Set<String> lastFive;
        int amount = 5;
        lastFive = scoreDB.lastFiveStudentsWithExcellentMarkOnAnySubject(amount);
        Assertions.assertEquals(3, lastFive.size());
    }

    @Test
    void multipleSubmissionsStudentNames() {
        scoreDB.addScore(score_6);
        Set<String> submissionsStudentNames = scoreDB.multipleSubmissionsStudentNames();
        Assertions.assertEquals(2, submissionsStudentNames.size());
    }
}