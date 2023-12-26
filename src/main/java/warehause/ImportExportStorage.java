package warehause;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvWriter;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvWriterSettings;
import warehause.exceptions.ItemNotFoundException;


public class ImportExportStorage {
    public Storage importCSV(Storage storage, String fileName){
        storage.clean();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        this.getClass().getResourceAsStream("/" + fileName + ".csv")))){
                String header = reader.readLine();
                String[] split = header.split(",");
                Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < split.length; i++) {
                headerMap.put(split[i].trim(), i);
            }
            String line;
            int id = 0;
            while ((line = reader.readLine()) != null)
            {
                split = line.split(",");
                int count = Integer.parseInt(split[headerMap.get("quantity")].trim());
                Wheel wheel = new Wheel(
                        UUID.randomUUID().toString(),//Integer.toString(id),//
                        caseVendor(split[headerMap.get("vendor")]),
                        caseType(split[headerMap.get("type")]),
                        split[headerMap.get("place")].trim(),
                        count
                );
                storage.putItem(wheel);
                id++;
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

        return storage;
    }

    public void exportCSV(Storage storage, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter("./src/test/resources/" + fileName + ".csv")) {
            writer.write("");
            String exportData;
            exportData = "id,vendor,type,place,quantity" + '\n';
            List<String> records = new ArrayList<>();
            String record;
            for (String key : storage.getAllItem().keySet()){
                record = storage.getItem(key).toString();
                exportData += record + '\n';
            }
            writer.write(exportData);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ItemNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private ListWVendor caseVendor(String vendor){
        vendor.trim();
        switch (vendor){
            case "Bridgestone":
                return ListWVendor.Bridgestone;
            case "Continental":
                return ListWVendor.Continental;
            case "Cooper":
                return ListWVendor.Cooper;
            case "Goodyear":
                return ListWVendor.Goodyear;
            case "KAMA":
                return ListWVendor.KAMA;
            case "Nokian":
                return ListWVendor.Nokian;
            case "Yokohama":
                return ListWVendor.Yokohama;
            default:
                return ListWVendor.unknown;
        }
    }

    private ListWType caseType (String type){
        type.trim();
        switch (type){
            case "allSeason":
                return ListWType.allSeason;
            case "drift":
                return ListWType.drift;
            case "offroad":
                return ListWType.offroad;
            case "summer":
                return ListWType.summer;
            case "winterS":
                return ListWType.winterS;
            case "winterV":
                return ListWType.winterV;
            default:
                return ListWType.unknown;
        }
    }
}
