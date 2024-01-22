package practive_v2.file;

import practive_v2.LocalDB;
import practive_v2.RecordsInBase;
import practive_v2.input.Check;
import practive_v2.input.InputRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public void readFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("./src/main/resources/" + filename + ".csv"))) {
            String line;
            while ((line = reader.readLine()) != null)
            {
                Check<RecordsInBase> check = new InputRecord().checkRecordFile(line);
                if (!check.isError()) LocalDB.getInstance().addRecord(check.getValue());
            }
            System.out.println("DB load from: " + filename);
        } catch (IOException e) {
            System.out.println("DB not found, start with empty base.");
        }
    }
}
