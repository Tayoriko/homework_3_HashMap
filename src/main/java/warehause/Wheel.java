package warehause;

import java.util.Objects;

public final class Wheel {
    private final String id;

    public ListWVendor getVendor() {
        return vendor;
    }

    private final ListWVendor vendor;

    public ListWType getType() {
        return type;
    }

    private final ListWType type;

    public String getPlace() {
        return place;
    }

    private final String place;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;

    public Wheel(String id, ListWVendor vendor, ListWType type, String place, int quantity) {
        this.id = id;
        this.vendor = vendor;
        this.type = type;
        this.place = place;
        this.quantity = quantity;
    }

    public String id() {
        return id;
    }

    public ListWVendor vendor() {
        return vendor;
    }

    public ListWType type() {
        return type;
    }

    public String place() {
        return place;
    }

    public int quantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Wheel) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.vendor, that.vendor) &&
                Objects.equals(this.type, that.type) &&
                Objects.equals(this.place, that.place) &&
                this.quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vendor, type, place, quantity);
    }

    @Override
    public String toString() {
        return "Wheel[" +
                "id=" + id + ", " +
                "vendor=" + vendor + ", " +
                "type=" + type + ", " +
                "place=" + place + ", " +
                "quantity=" + quantity + ']';
    }
}

