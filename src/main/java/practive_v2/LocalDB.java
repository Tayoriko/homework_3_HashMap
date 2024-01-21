package practive_v2;

import practice.PracticeLocalDB;

import java.util.*;
import java.util.stream.Collectors;

public class LocalDB {
    private static LocalDB instance;
    Map<Integer, RecordsInBase> db;
    private static Integer id = 0;
    private LocalDB(){
        db = new HashMap<>();
    }
    public static LocalDB getInstance(){
        if (instance == null) instance = new LocalDB();
        return instance;
    }

    public int getSize(){
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

    public void addRecord(RecordsInBase record){
        if (record.getId() == 0) {
            db.put(useId(), record);
            db.get(getId()).setId(getId());
        }
        else db.put(record.getId(), record);
    }

    public void delRecord(int id){
        if (isPresentID(id)) db.remove(id);
    }

    public void updateRecord(int id, RecordsInBase record){
        delRecord(id);
        db.put(id, record);
        db.get(id).setId(id);
    }

    public String getRecordAsString(int id){
        if (isPresentID(id)) return db.get(id).toString();
        return "";
    }

    public boolean isPresentID(int id){
        return db.containsKey(id);
    }

    public String getAllRecord(){
        List<RecordsInBase> list = db.values().stream().sorted(Comparator.comparing(recordsInBase -> {
            boolean b = recordsInBase.getId() > recordsInBase.getId();
            return b;
        })).collect(Collectors.toList());
        String data = "";
        for (RecordsInBase recordsInBase : list){
            data += GlobalElements.LN + recordsInBase.toString();
        }
        return data;
    }
}
