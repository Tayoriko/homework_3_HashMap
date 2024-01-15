package practice;

import students.Student;

import static practice.state.AbstractState.LN;

public class OneStudent {
    private String name;
    private String surname;
    private String city;
    private String course;
    private int old;
    private boolean fault = false;

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public OneStudent(){}

    public OneStudent(String s) {
        if (!s.isEmpty()) {
            fault = false;
            String[] newRecord = s.split(",");
            for (int i = 0; i < newRecord.length; i++) {
                newRecord[i] = newRecord[i].trim();
            }
            this.name = newRecord[0];
            this.surname = newRecord[1];
            this.city = newRecord[2];
            this.course = newRecord[3];
            this.old = Integer.valueOf(newRecord[4]);
        } else {
            fault = true;
            System.out.println("OneStudent(): No data about student.");
        }
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

    public boolean isFault() {
        return fault;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.id + ", " +
                this.name + ", " +
                this.surname + ", " +
                this.city + ", " +
                this.course + ", " +
                this. old;
    }
}
