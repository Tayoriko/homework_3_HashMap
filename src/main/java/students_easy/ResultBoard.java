package students_easy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ResultBoard {
    public TreeMap<Float, String> getBoard() {
        return board;
    }

    TreeMap<Float, String> board = new TreeMap<>();

    public void ResultsBoard() {

    }

    public void addStudent(String name, Float score) {
        if (name != null && score != null) {
            this.board.put(score, name);
        } else throw new RuntimeException("Incorrect input data");
    }

    public List<String> top3() {
        List<String> result = new ArrayList<>();
        Float key = 0.0F;
        if (board.lastKey() != null) {
            key = this.board.lastKey();
        }
        for (int i = 0; i < 2; i++) {
            result.add(this.board.get(key));
            if (board.lowerKey(key) != null) {
                key = this.board.lowerKey(key);
            } else break;
        }
        return result;
    }
}

