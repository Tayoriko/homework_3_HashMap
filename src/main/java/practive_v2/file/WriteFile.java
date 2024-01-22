package practive_v2.file;

import practice.PracticeLocalDB;
import practive_v2.LocalDB;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    public void writeToFile(String fileName) {
        try (FileWriter writer = new FileWriter("./src/main/resources/" + fileName + ".csv")) {
            writer.write("");
            String exportData = "";
            String record;
            LocalDB db = LocalDB.getInstance();
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
