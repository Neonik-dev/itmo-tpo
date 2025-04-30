package logarithm;

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
                () -> assertThrows(IllegalArgumentException.class, () -> ln.compute(-2, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> ln.compute(0, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> ln.compute(Double.NaN, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> ln.compute(Double.NEGATIVE_INFINITY, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> ln.compute(Double.POSITIVE_INFINITY, 0.001))
        );
    }

    @Test
    void checkLn_OK() {
        double delta = 0.01;
        double eps = 0.001;
        assertAll(
                () -> assertEquals(0, ln.compute(1, eps)),
                () -> assertEquals(2.302585, ln.compute(10, eps), delta),
                () -> assertEquals(2.70805, ln.compute(15, eps), delta),
                () -> assertEquals(1, ln.compute(Math.E, eps), delta)
        );
    }

    @Test
    void checkLargeLn_OK() {
        double eps = 0.001;
        assertAll(
                () -> assertEquals(8.38213, ln.compute(12342, eps), eps),
                () -> assertEquals(8.87126, ln.compute(34234893284.23423, eps), eps),
                () -> assertEquals(7.86001, ln.compute(4343, eps), eps)
        );
    }
}