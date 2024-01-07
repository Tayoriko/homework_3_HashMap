package students;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

class ScoreDBTest {

    private ScoreDB scoreDB;
    private ScoreDate date_1 = new ScoreDate(2023, 11, 1, 10, 10);
    private ScoreDate date_2 = new ScoreDate(2023, 11, 1, 10, 11);
    private ScoreDate date_3 = new ScoreDate(2023, 11, 2, 10, 12);
    private ScoreDate date_4 = new ScoreDate(2023, 11, 1, 10, 13);
    private ScoreDate date_5 = new ScoreDate(2023, 11, 2, 10, 14);
    private ScoreDate date_6 = new ScoreDate(2023, 11, 1, 10, 15);
    private ScoreDate date_7 = new ScoreDate(2023, 11, 1, 10, 16);
    private ScoreDate date_8 = new ScoreDate(2023, 11, 1, 10, 17);
    private Student student_1 = new Student("1_Anna", "S");
    private Student student_2 = new Student("2_Sergey", "D");
    private Student student_3 = new Student("3_Dmitry", "K");
    private Student student_4 = new Student("4_Oleg", "R");
    private Student student_5 = new Student("5_Mia", "L");
    private Student student_6 = new Student("6_Sveta", "S");
    private Score score_1 = new Score(student_1, ListSubject.Alg, 5, date_1);
    private Score score_2 = new Score(student_1, ListSubject.Alg, 5, date_2);
    private Score score_3 = new Score(student_2, ListSubject.Alg, 5, date_3);
    private Score score_4 = new Score(student_3, ListSubject.Phis, 5, date_4);
    private Score score_5 = new Score(student_4, ListSubject.Chem, 3, date_5);
    private Score score_6 = new Score(student_5, ListSubject.Alg, 5, date_6);
    private Score score_7 = new Score(student_6, ListSubject.Geom, 5, date_7);

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

    @DisplayName("Test for add score to in-memory database")
    @Test
    void addScore() {
        score_1.setScore(5);
        scoreDB.addScore(score_1);
        scoreDB.addScore(score_1);
        Score test = scoreDB.getScore(student_1, ListSubject.Alg);
        Assertions.assertEquals(5, test.getScore());
        Assertions.assertEquals(4, test.getCounter());
    }

    @DisplayName("Test for get score records from base by student and subject")
    @Test
    void getScore() {
        Score test = scoreDB.getScore(student_1, ListSubject.Alg);
        Assertions.assertEquals(student_1, test.getStudent());
        Assertions.assertEquals(ListSubject.Alg, test.getSubject());
    }

    @DisplayName("Test for getting average score by subject")
    @Test
    void getAverageScoreForSubject() {
        scoreDB.addScore(score_1);
        scoreDB.addScore(score_3);
        Double avgScore = scoreDB.getAverageScoreForSubject(ListSubject.Alg);
        Assertions.assertEquals(5.0, avgScore);

    }

    @DisplayName("Test for getting last 5 students with excellent marks")
    @Test
    void lastFiveStudentsWithExcellentMarkOnAnySubject() {
        Set<String> lastFive;
        int amount = 5;
        lastFive = scoreDB.lastFiveStudentsWithExcellentMarkOnAnySubject(amount);
        Assertions.assertEquals(5, lastFive.size());
    }

    @DisplayName("Test for getting students with more then 1 counters for any subjects")
    @Test
    void multipleSubmissionsStudentNames() {
        scoreDB.addScore(score_6);
        Set<String> submissionsStudentNames = scoreDB.multipleSubmissionsStudentNames();
        Assertions.assertEquals(2, submissionsStudentNames.size());
    }

    @DisplayName("Test for get all subjects with scores from un-memory database")
    @Test
    void getAllScores() {
        Collection<String> allScores;
        allScores = scoreDB.getScoreSubjects();
        Assertions.assertEquals(4, allScores.size());
    }

    @DisplayName("Test for getting students for the subject")
    @Test
    void getScoreStudents() {
        Collection<Student> allScores;
        allScores = scoreDB.getScoreStudents(ListSubject.Alg);
        Assertions.assertEquals(3, allScores.size());
    }

    @DisplayName("Test for getting all scores")
    @Test
    void testGetAllScores() {
        Map<String, Score> allScores;
        allScores = scoreDB.scoreDB;
        Assertions.assertEquals(6, allScores.size());
    }

    @DisplayName("Test for getting students, who get scores by the date")
    @Test
    void getStudentsScoreByDate() {
        Collection<Student> allScores;
        ScoreDate date = new ScoreDate(2023, 11, 1, 10, 11);
        allScores = scoreDB.getStudentsScoreByDate(date);
        Assertions.assertEquals(4, allScores.size());
    }

    @DisplayName("Test cash with average mark by the score")
    @Test
    void testCash()
    {
        //agl 5+5+5+5
        Score score_10 = new Score(student_1, ListSubject.Alg, 3, date_2);
        Score score_11 = new Score(student_2, ListSubject.Alg, 3, date_3);
        //phis 5
        Score score_12 = new Score(student_3, ListSubject.Phis, 3, date_4);
        Score score_13 = new Score(student_3, ListSubject.Phis, 1, date_4);
        scoreDB.addScore(score_10);
        scoreDB.addScore(score_11);
        scoreDB.addScore(score_12);
        scoreDB.addScore(score_13);
        double avgAlg = scoreDB.cashDB.getScore(ListSubject.Alg);
        double avgPhis = scoreDB.cashDB.getScore(ListSubject.Phis);
        Assertions.assertEquals(4.33, avgAlg);
        Assertions.assertEquals(3.0, avgPhis);
    }
}