package trigonometry;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CosTest {
    private static Cos cos;

    @BeforeAll
    static void init() {
        cos = new Cos(new Sin());
    }

    @Test
    public void checkValidationX_ThrowIllegalArgumentException() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> cos.compute(2, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> cos.compute(Double.NaN, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> cos.compute(Double.NEGATIVE_INFINITY, 0.001)),
                () -> assertThrows(IllegalArgumentException.class, () -> cos.compute(Double.POSITIVE_INFINITY, 0.001))
        );
    }

    @Test
    void checkZeroValues_OK() {
        double eps = 0.001;
        assertAll(
                () -> assertEquals(0, cos.compute(-Math.PI / 2, eps)),
                () -> assertEquals(0, cos.compute(-1.5 * Math.PI, eps)),
                () -> assertEquals(0, cos.compute(-2.5 * Math.PI, eps)),
                () -> assertEquals(0, cos.compute(-3.5 * Math.PI, eps))
        );
    }

    @Test
    public void checkSin_OK() {
        double eps = 0.001;
        assertAll(
                () -> assertEquals(0.8660254037844386, cos.compute(Math.PI / 6 - 2 * Math.PI, eps), eps),
                () -> assertEquals(-1, cos.compute(-Math.PI, eps), eps),
                () -> assertEquals(1, cos.compute(0, eps), eps),
                () -> assertEquals(0.7071067811865475, cos.compute(Math.PI / 4 - 2 * Math.PI, eps), eps),
                () -> assertEquals(0.5, cos.compute(Math.PI / 3 - 2 * Math.PI, eps), eps)
        );
    }
}
