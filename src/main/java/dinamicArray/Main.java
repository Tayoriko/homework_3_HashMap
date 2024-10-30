import java.lang.reflect.Array;
import java.util.Arrays;

private int maxSize = 2;
private int size = 0;
private int[] array = new int[maxSize];

public void main() {
    add(4);
    add(3);
    add(1);
    add(5);
    print(); // должно вывести 4,3,1,5
    Arrays.sort(array);
    print(); // должно вывести 1,3,4,5
}


private void add (int value) {
    if (checkEmptySpace()) {
        this.array = arrayExtend();
    }
    this.size++;
    this.array[size] = value;
}

private boolean checkEmptySpace() {
    if (this.array.length > this.size) {
        return true;
    } else {
        return false;
    }
}

private int[] arrayExtend () {
    maxSize *= 2;
    int[] array = new int[maxSize];
    if (this.array.length - 1 >= 0) System.arraycopy(this.array, 0, array, 0, this.array.length - 1);
    return array;
}

private void print () {
    System.out.println(Arrays.toString(this.array));
}
