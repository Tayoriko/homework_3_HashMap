package practive_v2;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LocalCash {

    private static LocalCash instance;
    private TreeMap<String, Set<Integer>> dbCash;

    private LocalCash(){
        dbCash = new TreeMap<>();
    }

    public static LocalCash getInstance(){
        if (instance == null) instance = new LocalCash();
        return instance;
    }

    public void addStudentToTree(Integer id, String surname){
        Set<Integer> existingIDs = dbCash.getOrDefault(surname, new HashSet<>());
        existingIDs.add(id);
        dbCash.put(surname, existingIDs);
    }

    public void deleteStudentFromTree(Integer id, String surname){
        dbCash.get(surname).remove(id);
        if (dbCash.get(surname).isEmpty()){
            dbCash.remove(surname);
        }
    }

    public void updateStudentInTree(Integer id, String surnameOld, String surnameNew){
        deleteStudentFromTree(id, surnameOld);
        addStudentToTree(id, surnameNew);
    }

    public Set<Integer> getStudentSurnameOrEqual(String surname, boolean vector){
        Set<Integer> res;
        if (!vector) {
            res = dbCash.headMap(surname, true)
                    .values()
                    .stream()
                    .flatMap(ints -> ints.stream())
                    .collect(Collectors.toSet());
        }
        else{
            res = dbCash.tailMap(surname, true)
                    .values()
                    .stream()
                    .flatMap(ints -> ints.stream())
                    .collect(Collectors.toSet());
        }
        return res;
    }

    public Set<Integer> getStudentsRange(String surnameStart, String surnameFinish){
        Set<Integer> res = dbCash.subMap(surnameStart, surnameFinish)
                .values()
                .stream()
                .flatMap(ints -> ints.stream())
                .collect(Collectors.toSet());
        return res;
    }

    public TreeMap<String, Set<Integer>> getDbCash(){
        return dbCash;
    }
}
