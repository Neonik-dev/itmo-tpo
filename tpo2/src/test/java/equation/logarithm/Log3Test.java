package equation.logarithm;

import logarithm.Ln;
import logarithm.Log3;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Log3Test extends LnTest {
    private static Log3 log3;

    @BeforeAll
    public static void init() {
        log3 = new Log3(new Ln());
    }

    @Test
    void checkLn_OK() {
        assertAll(
                () -> assertEquals(0, log3.compute(1, 0.001)),
                () -> assertEquals(2.0959, log3.compute(10, 0.001), 0.01),
                () -> assertEquals(2.4649, log3.compute(15, 0.001), 0.01)
        );
    }

    @Test
    void checkLargeLn_OK() {
        double eps = 0.001;
        assertAll(
                () -> assertEquals(7.62974, log3.compute(12342, eps), eps),
                () -> assertEquals(8.07497, log3.compute(34234893284.23423, eps), eps),
                () -> assertEquals(7.15449, log3.compute(4343, eps), eps)
        );
    }
}