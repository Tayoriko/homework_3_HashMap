package students;

import java.util.Date;
import java.util.Objects;

public final class ScoreDate {
    private final Date date = new Date();


    public ScoreDate(int year, int month, int date, int hour, int minutes) {
        this.date.setYear(year);
        this.date.setMonth(month);
        this.date.setDate(date);
        this.date.setHours(hour);
        this.date.setMinutes(minutes);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreDate scoreDate = (ScoreDate) o;
        return Objects.equals(date, scoreDate.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return "ScoreDate{" +
                "date=" + date +
                '}';
    }

    public Date getDate() {
        return date;
    }
}
