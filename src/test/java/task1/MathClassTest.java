package task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class MathClassTest {
    @ParameterizedTest
    @ValueSource(doubles = {-100, -1.1, -1.000001, 1.00001, 1.3, 100})
    void checkValidationX_ThrowIllegalArgumentException(double x) {
        MathClass mathClass = new MathClass(x, 100);

        assertThrows(
                IllegalArgumentException.class,
                mathClass::arccos
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.9, -0.3, 0, 0.345456, 0.9, 1})
    void checkValidationX_OK(double x) {
        MathClass mathClass = new MathClass(x, 100);

        assertDoesNotThrow(mathClass::arccos);
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 100000, 23423421})
    void checkValidationTerms_ThrowIllegalArgumentException(int terms) {
        MathClass mathClass = new MathClass(0.5, terms);

        assertThrows(
                IllegalArgumentException.class,
                mathClass::arccos
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 10001, 23423421})
    void checkValidationTerms_OK(int terms) {
        MathClass mathClass = new MathClass(0.5, terms);

        assertThrows(
                IllegalArgumentException.class,
                mathClass::arccos
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 234, 10000})
    void checkValidationX_OK(int terms) {
        MathClass mathClass = new MathClass(0.5, terms);

        assertDoesNotThrow(mathClass::arccos);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.5, 0, 0.23432323, 0.5})
    void checkAcosForSmallX_OK(double x) {
        MathClass mathClass = new MathClass(x, 20);

        double result = mathClass.arccos();

        double expected = Math.acos(x);
        assertEquals(expected, result, 1e-8, "Неверное приближение для x = {" + x + "}");

    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.78544, 0.9998})
    void checkAcos_OK(double x) {
        MathClass mathClass = new MathClass(x, 50);

        double result = mathClass.arccos();

        double expected = Math.acos(x);
        assertEquals(expected, result, 1e-1, "Неверное приближение для x = {" + x + "}");

    }
}
