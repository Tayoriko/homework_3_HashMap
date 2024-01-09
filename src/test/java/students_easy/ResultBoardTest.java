package students_easy;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

class ResultBoardTest {

    ResultBoard board = new ResultBoard();

    @BeforeEach
    void beforeAll(){
        board.addStudent("Oleg", 3.13F);
        board.addStudent("Olga", 1.03F);
        board.addStudent("Onni", 2.1F);
        board.addStudent("Oppa", 4.7F);
        board.addStudent("Oppay", 4.0F);
    }

    @DisplayName("Add 5 score to board")
    @Test
    void addStudent() {
        Assertions.assertEquals(5, board.getBoard().size());
    }

    @DisplayName("Get 3 highest score from board")
    @Test
    void top3() {
        List<String> top3Scores = new ArrayList<>();
        top3Scores = board.top3();
        Assertions.assertEquals("Oppay", top3Scores.get(1));
    }
}