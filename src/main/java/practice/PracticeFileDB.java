package practice;

import practice.validator.ValidStudent;
import warehause.Storagable;
import warehause.Wheel;
import warehause.exceptions.ItemNotFoundException;

import java.io.*;
import java.util.*;

public class PracticeFileDB {

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("./src/main/resources/" + filename + ".csv"))) {
            String header = reader.readLine();
            String[] split = header.split(",");
            String line;
            while ((line = reader.readLine()) != null)
            {
                String record = "";
                split = line.split(",");
                for (int i = 1; i < split.length - 1; i++) {
                    record += split[i] + ",";
                }
                record += split[5];
                OneStudent student = new OneStudent(new ValidStudent().checkDataOfStudent(record));
                PracticeLocalDB.getInstance().addStudent(student);
            }
            System.out.println("DB load from: " + filename);
        } catch (IOException e) {
            System.out.println("DB not found, start with empty base.");
        }
    }

    public void saveToFile(String fileName) {
        try (FileWriter writer = new FileWriter("./src/main/resources/" + fileName + ".csv")) {
            writer.write("");
            String exportData;
            exportData = "id,name,surname,course,city,old" + '\n';
            List<String> records = new ArrayList<>();
            String record;
            PracticeLocalDB db = PracticeLocalDB.getInstance();
            for (Integer key : db.getAll().keySet()){
                record = db.getAll().get(key).toString();
                exportData += record + '\n';
            }
            writer.write(exportData);
            writer.flush();
            System.out.println("DB save to: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
