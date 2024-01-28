package stonks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NetList {
    public List<Stonks> getStonks (String url) throws MalformedURLException {
        URL fileUrl = new URL(url);
        String filePaths = fileUrl.getFile();
        Path path = Paths.get(filePaths);
        String filename = path.getFileName().toString();
        String fileAddr = "./tmp/" + filename;
        try (BufferedInputStream is =
                new BufferedInputStream(new URL((url)).openStream())){
            Scanner scanner = new Scanner(is).useDelimiter("\\A");
            String result = scanner.hasNext() ? scanner.next() : "";
            return convertCsvToStonks(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Stonks> convertCsvToStonks(String inputData){
        List<Stonks> stonks = new ArrayList<>();
        String[] lines = inputData.split("\n");
        for (int i = 1; i < lines.length; i++) {
            stonks.add(convertLineToStonk(lines[i]));
        }
        return stonks;
    }

    private Stonks convertLineToStonk(String line){
        String[] data = line.split(",");
        for (String value : data){
            value = value.trim();
        }
        Stonks stonks = new Stonks(
                Date.valueOf(data[0]),
                Double.parseDouble(data[1]),
                Double.parseDouble(data[2]),
                Double.parseDouble(data[3]),
                Double.parseDouble(data[4]),
                Long.parseLong(data[5])
        );
        return stonks;
    }

}
