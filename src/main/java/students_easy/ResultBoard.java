package students_easy;

import students.Student;

import java.util.*;

public class ResultBoard {
    public TreeSet<StudentAvgScore> getBoard() {
        return board;
    }

    TreeSet<StudentAvgScore> board = new TreeSet<>();

    public void addStudent (String name, int id, float score){
        if (name != null){
            if (board.stream().noneMatch(studentAvgScore ->  id == studentAvgScore.id())) {
                StudentAvgScore student = new StudentAvgScore(name, id, score);
                board.add(student);
            } else {
                throw new RuntimeException("Incorrect ID");
            }
        }
    }

    public List<String> top3(){
        List<String> result = new ArrayList<>(3);
        StudentAvgScore person = board.last();
        for (int i = 0; i < 3; i++) {
            if (person != null) {
                result.add(person.name());
            }
            if (board.lower(person) != null) {
                person = board.lower(person);
            } else break;
        }
        return result;
    }

}

final class StudentAvgScore implements Comparable{
    private final String name;
    private final int id;
    private final float score;

    public StudentAvgScore(String name, int id, float score) {
        this.name = name;
        this.id = id;
        this.score = score;
    }

    public String name() {
        return name;
    }

    public int id() {
        return id;
    }

    public float score() {
        return score;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (StudentAvgScore) obj;
        return Objects.equals(this.name, that.name) &&
                this.id == that.id &&
                Float.floatToIntBits(this.score) == Float.floatToIntBits(that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, score);
    }

    @Override
    public String toString() {
        return "StudentAvgScore[" +
                "name=" + name + ", " +
                "id=" + id + ", " +
                "score=" + score + ']';
    }


    @Override
    public int compareTo(Object o) {
        StudentAvgScore student = (StudentAvgScore) o;
        if (this.score != student.score){
            return Float.compare(this.score, student.score);
        }
        else {
            return Integer.compare(this.id, student.id);
        }
    }
}



