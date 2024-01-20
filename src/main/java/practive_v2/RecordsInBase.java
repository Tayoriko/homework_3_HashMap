package practive_v2;

import practive_v2.input.Check;

public class RecordsInBase {

    private String name;
    private String surname;
    private String city;
    private String course;
    private int old;
    private Integer id = 0;

    public RecordsInBase(Check<String>[] data) {
        this.name = data[0].getValue();
        this.surname = data[1].getValue();
        this.city = data[2].getValue();
        this.course = data[3].getValue();
        this.old = Integer.parseInt(data[4].getValue());
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public String getCourse() {
        return course;
    }

    public int getOld() {
        return old;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id + ", " +
                this.name + ", " +
                this.surname + ", " +
                this.city + ", " +
                this.course + ", " +
                this.old;
    }
}
