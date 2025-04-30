package equation.trigonometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trigonometry.Sin;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SInTest {
    private static Sin sin;

    @BeforeEach
    public void init() {
        sin = new Sin();
    }

    @Test
    public void checkValidationX_ThrowIllegalArgumentException() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> sin.compute(2, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> sin.compute(Double.NaN, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> sin.compute(Double.NEGATIVE_INFINITY, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> sin.compute(Double.POSITIVE_INFINITY, 0.001))
        );
    }

    @Test
    void checkSin_OK() {
        double delta = 0.01;
        double eps = 0.001;
        assertAll(
                () -> assertEquals(0, sin.compute(1, eps)),
                () -> assertEquals(2.302585, sin.compute(10, eps), delta),
                () -> assertEquals(2.70805, sin.compute(15, eps), delta),
                () -> assertEquals(1, sin.compute(Math.E, eps), delta)
        );
    }

    @Test
    public void checkZeroValues_OK() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> sin.compute(2, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> sin.compute(Double.NaN, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> sin.compute(Double.NEGATIVE_INFINITY, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> sin.compute(Double.POSITIVE_INFINITY, 0.001))
        );
    }

}
