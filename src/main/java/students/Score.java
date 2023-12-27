package students;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public final class Score{

    private final String id;
    public final Student student;
    public final ListSubject subject;
    public int score;
    private int counter;

    public ScoreDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(ScoreDate lastDate) {
        this.lastDate = lastDate;
    }

    private ScoreDate lastDate;

    public Score(Student student, ListSubject subject, int score, ScoreDate date) {
        this.id = UUID.randomUUID().toString();
        this.student = student;
        this.subject = subject;
        this.score = score;
        this.counter = 1;
        this.lastDate = date;
    }

    public String getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public ListSubject getSubject(){
        return this.subject;
    }

    public String subject() {
        return subject.name();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCounter() {
        return counter;
    }

    public void addCounter() {
        this.counter++;
    }

    public void resetCounter() {
        this.counter = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Score) obj;
        return Objects.equals(this.student, that.student) &&
                Objects.equals(this.subject, that.subject) &&
                this.score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, subject, score);
    }

    @Override
    public String toString() {
        return "Score[" +
                "name=" + student.toString() + ", " +
                "subject=" + subject.getFullName() + ", " +
                "counter=" + counter + ", " +
                "score=" + score +
                "date=" + lastDate.toString() + ']';
    }

}
