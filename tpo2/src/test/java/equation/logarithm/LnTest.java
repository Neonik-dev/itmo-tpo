package equation.logarithm;

import logarithm.Ln;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LnTest {
    private static Ln ln;

    @BeforeAll
    public static void init() {
        ln = new Ln();
    }

    @Test
    void checkValidationX_ThrowIllegalArgumentException() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> ln.compute(-1, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> ln.compute(0, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> ln.compute(Double.NaN, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> ln.compute(Double.NEGATIVE_INFINITY, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> ln.compute(Double.POSITIVE_INFINITY, 0.001))
        );
    }

    @Test
    void checkLn_OK() {
        assertAll(
                () -> assertEquals(0, ln.compute(1, 0.001)),
                () -> assertEquals(2.302585, ln.compute(10, 0.001), 0.01),
                () -> assertEquals(2.70805, ln.compute(15, 0.001), 0.01)
        );
    }
}