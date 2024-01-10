package vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorCheckTest {

    @Test
    void readFile() {
        VectorCheck check = new VectorCheck();
        check.getMaxVectorFromFile("vector");
    }
}