package equation.logarithm;

import logarithm.Ln;
import logarithm.Log3;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Log3Test {
    private static Log3 log3;

    @BeforeAll
    public static void init() {
        log3 = new Log3(new Ln());
    }

    @Test
    void checkValidationX_ThrowIllegalArgumentException() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> log3.compute(-1, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> log3.compute(0, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> log3.compute(Double.NaN, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> log3.compute(Double.NEGATIVE_INFINITY, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> log3.compute(Double.POSITIVE_INFINITY, 0.001))
        );
    }

    @Test
    void checkLn_OK() {
        assertAll(
                () -> assertEquals(0, log3.compute(1, 0.001)),
                () -> assertEquals(2.0959, log3.compute(10, 0.001), 0.01),
                () -> assertEquals(2.4649, log3.compute(15, 0.001), 0.01)
        );
    }
}