package students_easy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ResultBoard {
    public TreeMap<Float, String> getBoard() {
        return board;
    }

    TreeMap<Float, String> board = new TreeMap<>();

    public void ResultsBoard(){

    }

    void addStudent(String name, Float score) {
        this.board.put(score, name);
    }

    public List<String> top3 () {
        List<String> result = new ArrayList<>();
        Float key = this.board.lastKey();
        result.add(this.board.get(key));
        key = this.board.lowerKey(key);
        result.add(this.board.get(key));
        key = this.board.lowerKey(key);
        result.add(this.board.get(key));
        return result;
    }
}
