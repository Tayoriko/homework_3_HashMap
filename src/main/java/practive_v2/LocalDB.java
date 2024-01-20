package practive_v2;

import practice.PracticeLocalDB;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
        id++;
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addRecord(RecordsInBase record){
        if (record.getId() == 0) db.put(getId(), record);
        else db.put(record.getId(), record);
    }
}
