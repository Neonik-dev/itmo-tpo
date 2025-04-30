package trigonometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SinTest {
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
    void checkZeroValues_OK() {
        double eps = 0.001;
        assertAll(
                () -> assertEquals(0, sin.compute(-Math.PI, eps)),
                () -> assertEquals(0, sin.compute(-2 * Math.PI, eps)),
                () -> assertEquals(0, sin.compute(-3 * Math.PI, eps)),
                () -> assertEquals(0, sin.compute(-4 * Math.PI, eps))
        );
    }

    @Test
    public void checkSin_OK() {
        double eps = 0.001;
        assertAll(
                () -> assertEquals(0.5, sin.compute(Math.PI / 6 - 2 * Math.PI, eps), eps),
                () -> assertEquals(-1, sin.compute(Math.PI / 2 - Math.PI, eps), eps),
                () -> assertEquals(1, sin.compute(Math.PI / 2 - 2 * Math.PI, eps), eps),
                () -> assertEquals(0.7071067811865475, sin.compute(Math.PI / 4 - 2 * Math.PI, eps), eps),
                () -> assertEquals(0.8660254037844386, sin.compute(Math.PI / 3 - 2 * Math.PI, eps), eps)
        );
    }
}
