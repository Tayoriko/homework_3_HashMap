package students;

public enum ListSubject {
    Alg("Алгебра"), Geom("Геометрия"), Phis("Физика"), Chem("Химия");

    private String name;

    ListSubject(String name) {
        this.name = name;
    }

    public String getFullName() {
        return this.name;
    }
}
