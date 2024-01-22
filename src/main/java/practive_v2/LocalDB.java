package practive_v2;

import java.util.*;
import java.util.stream.Collectors;

public class LocalDB {
    private static LocalDB instance;
    Map<Integer, RecordsInBase> db;
    private static Integer id = 0;

    private LocalDB() {
        db = new HashMap<>();
    }

    public static LocalDB getInstance() {
        if (instance == null) instance = new LocalDB();
        return instance;
    }

    public int getSize() {
        return db.size();
    }

    public Integer getId() {
        return id;
    }

    public Integer useId() {
        id++;
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addRecord(RecordsInBase record) {
        if (record.getId() == 0) {
            db.put(useId(), record);
            db.get(getId()).setId(getId());
            LocalCash.getInstance().addStudentToTree(record.getId(), record.getSurname());
        } else {
            if (db.containsKey(record.getId())) System.out.println("Error: ID " + record.getId() + " found in base!");
            db.put(record.getId(), record);
            LocalCash.getInstance().addStudentToTree(record.getId(), record.getSurname());
        }
    }

    public void delRecord(int id) {
        if (isPresentID(id)) {
            LocalCash.getInstance().deleteStudentFromTree(id, db.get(id).getSurname());
            db.remove(id);
        }
    }

    public void updateRecord(int id, RecordsInBase record) {
        if (isPresentID(id)) {
            LocalCash.getInstance().updateStudentInTree(id, db.get(id).getSurname(), record.getSurname());
            delRecord(id);
            db.put(id, record);
            db.get(id).setId(id);
        }
    }

    public String getRecordAsString(int id) {
        if (isPresentID(id)) return db.get(id).toString();
        return "";
    }

    public boolean isPresentID(int id) {
        return db.containsKey(id);
    }

    public String getAllRecord() {
        String data = "";
        if (!db.isEmpty()) {
            List<RecordsInBase> list = db.values().stream().sorted(Comparator.comparing(recordsInBase -> {
                boolean b = recordsInBase.getId() > recordsInBase.getId();
                return b;
            })).collect(Collectors.toList());
            for (RecordsInBase recordsInBase : list) {
                data += GlobalElements.LN + recordsInBase.toString();
            }
        } else data = "No records in base!";
        return data;
    }

    public Map<String, Integer> getStatByCourse(){
        Map<String, Integer> stat = new HashMap<>();
        for (RecordsInBase recordsInBase : db.values()){
            String key = recordsInBase.getCourse();
            Integer count = stat.getOrDefault(key, 0);
            count++;
            stat.put(key, count);
        }
        return stat;
    }

    public Map<String, Integer> getStatByCities(){
        Map<String, Integer> stat = new HashMap<>();
        for (RecordsInBase recordsInBase : db.values()){
            String key = recordsInBase.getCity();
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

    public Map<Integer, RecordsInBase> getAll(){
        return db;
    }

    public void clear(){
        db.clear();
    }
}
