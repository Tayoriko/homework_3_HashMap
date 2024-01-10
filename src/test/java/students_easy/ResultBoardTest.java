package students_easy;

import org.junit.jupiter.api.*;

import java.util.List;

class ResultBoardTest {

    ResultBoard board = new ResultBoard();

    @BeforeEach
    void beforeAll(){
        board.addStudent("Oleg", 3.13F);
        board.addStudent("Olga", 1.03F);
    }

    @DisplayName("Add 5 score to board")
    @Test
    void addStudent() {
        board.addStudent("Onni", 2.1F);
        board.addStudent("Oppa", 4.7F);
        board.addStudent("Oppay", 4.0F);
        //board.addStudent(null, 4.0F);
        Assertions.assertEquals(5, board.getBoard().size());
    }

    @DisplayName("Get 3 highest score from board")
    @Test
    void top3() {
        List<String> top3Scores = board.top3();
        Assertions.assertEquals("Olga", top3Scores.get(1));
    }
}