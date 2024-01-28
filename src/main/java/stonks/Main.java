package stonks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        NetList netList = new NetList();
        List<Stonks> stonksList = netList.getStonks("https://raw.githubusercontent.com/productstar-team/javaTwo/master/resources/monthly_IBM.csv");
        stonksList.sort(new Comparator<Stonks>() {
            @Override
            public int compare(Stonks o1, Stonks o2) {
                return Double.compare(o1.getClose(), o2.getClose());
            }
        });

        System.out.println("Date for min price: " +
                stonksList.get(0).getTimeStamp().toString() +
                ", price: " +
                stonksList.get(0).getClose().toString());

        System.out.println("Date for max price: " +
                stonksList.get(stonksList.size()-1).getTimeStamp().toString() +
                ", price: " +
                stonksList.get(stonksList.size()-1).getClose().toString());

    }
}
