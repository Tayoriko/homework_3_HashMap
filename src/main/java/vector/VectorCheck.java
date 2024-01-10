package vector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;

public class VectorCheck {

    double length = 0.0;

    public static double calcLength(String line){
        double[] pos = Pattern.compile("\\d+")
                .matcher(line)
                .results()
                .mapToDouble(matchResult -> Double.parseDouble(matchResult.group()))
                .toArray();
        if (pos.length == 4) {
            return Math.sqrt(((pos[2] - pos[0]) * (pos[2] - pos[0])) + ((pos[3] - pos[1]) * (pos[3] - pos[1])));
        }
        else return 0.0;
    }

    public void readFile() {
        try {
            double v = new BufferedReader(new FileReader("./src/test/resources/vector.txt")).lines()
                    .mapToDouble(VectorCheck::calcLength)
                    .max()
                    .orElse(0.0);
            System.out.println(v);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



}
