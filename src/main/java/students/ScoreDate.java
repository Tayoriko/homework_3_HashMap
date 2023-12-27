package students;

import java.util.Objects;

public final class ScoreDate {
    private final int year;
    private final int month;
    private final int date;
    private final int hour;
    private final int minutes;
    private final int abs;



    private final int day;

    public ScoreDate(int year, int month, int date, int hour, int minutes) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minutes = minutes;
        this.day = this.year + this.month + this.date;
        this.abs = this.year + this.month + this.date + this.hour + this.minutes;
    }

    public int year() {
        return year;
    }

    public int month() {
        return month;
    }

    public int date() {
        return date;
    }

    public int hour() {
        return hour;
    }

    public int minutes() {
        return minutes;
    }

    public int abs() {
        return abs;
    }

    public int day() {
        return day;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ScoreDate) obj;
        return this.year == that.year &&
                this.month == that.month &&
                this.date == that.date &&
                this.hour == that.hour &&
                this.minutes == that.minutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, date, hour, minutes);
    }

    @Override
    public String toString() {
        return hour + ":" + minutes + " :: " + date + "/" + month + "/" + year;
    }

}
