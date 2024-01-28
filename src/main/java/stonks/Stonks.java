package stonks;

import java.util.Date;
import java.util.Objects;

public class Stonks {
    Date timeStamp;
    Double open;
    Double high;
    Double low;
    Double close;
    Long volume;

    public Stonks() {
    }

    public Stonks(Date timeStamp, Double open, Double high, Double low, Double close, Long volume) {
        this.timeStamp = timeStamp;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stonks stonks = (Stonks) o;
        return Objects.equals(close, stonks.close);
    }

    @Override
    public int hashCode() {
        return Objects.hash(close);
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public Double getOpen() {
        return open;
    }

    public Double getHigh() {
        return high;
    }

    public Double getLow() {
        return low;
    }

    public Double getClose() {
        return close;
    }

    public Long getVolume() {
        return volume;
    }
}
