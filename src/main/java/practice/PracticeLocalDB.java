package practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PracticeLocalDB {
    private static PracticeLocalDB instance;
    private Map<Integer, OneStudent> db;
    private static int counter = 0;

    private PracticeLocalDB(){
        db = new HashMap<>();
    }

    public static PracticeLocalDB getInstance(){
        if (instance == null) instance = new PracticeLocalDB();
        return instance;
    }


    public void setCounter(int counter) {
        PracticeLocalDB.counter = counter;
    }

    public int getSize(){
        return db.size();
    }

    public int getCounter(){
        counter++;
        return counter;
    }

    public void addStudent(OneStudent student) {
        if (!(student == null)) {
            int id = getCounter();
            student.setId(id);
            PracticeSearchCash.getInstance().addStudentToTree(id, student.getSurname());
            db.put(id, student);
            System.out.println("Success add new student! New ID = " + counter);
        }
        else {
            System.out.println("PracticeLocalDB.addStudent(): No data about student");
        }
    }

    public void deleteStudent(Integer id){
        if (db.containsKey(id)){
            PracticeSearchCash.getInstance().deleteStudentFromTree(id, db.get(id).getSurname());
            db.remove(id);
            System.out.println("Success remove student! Deleted ID = " + id);
        } else System.out.println("ID not found: " + id);
    }

    public void updateStudent(Integer id, OneStudent student){
        if (id > 0) {
            if (!(student == null)) {
                PracticeSearchCash.getInstance().updateStudentInTree(id, db.get(id).getSurname(), student.getSurname());
                deleteStudent(id);
                db.put(id, student);
                System.out.println("Success update student! ID = " + counter);
            } else {
                System.out.println("PracticeLocalDB.updateStudent(): No data about student");
            }
        } else System.out.println("Incorrect ID for update: " + id);
    }

    public OneStudent getStudentByID(Integer id){
        if (db.containsKey(id)){
            return db.get(id);
        } else System.out.println("Not found ID = " + id);
        return null;
    }

    public Map<String, Integer> getStatByCourse(){
        Map<String, Integer> stat = new HashMap<>();
        for (OneStudent student : db.values()){
            String key = student.getCourse();
            Integer count = stat.getOrDefault(key, 0);
            count++;
            stat.put(key, count);
        }
        return stat;
    }

    public Map<String, Integer> getStatByCities(){
        Map<String, Integer> stat = new HashMap<>();
        for (OneStudent student : db.values()){
            String key = student.getCity();
            Integer count = stat.getOrDefault(key, 0);
            count++;
            stat.put(key, count);
        }
        return stat;
    }

    public void printByIDs(Set<Integer> set){
        for (Integer id : set){
            System.out.println(db.get(id).toString());
        }
    }

    public Map<Integer, OneStudent> getAll(){
        return db;
    }
}
