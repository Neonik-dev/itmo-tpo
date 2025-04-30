package equation;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import trigonometry.Cos;
import trigonometry.Sin;

import static org.junit.Assert.assertEquals;

public class TrigonometrySolverTest {
    public static Sin sin = Mockito.mock(Sin.class);
    public static Cos cos = Mockito.mock(Cos.class);

    @BeforeAll
    public static void fillAll() {
        fillMockSin(sin);
        fillMockCos(cos);
    }

    private static void fillMockSin(Sin tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/trigonometryTestData/test-sin-data.csv"))) {
            List<String[]> lines = csvReader.readAll();
            for (String[] line : lines) {
                double x = Double.parseDouble(line[0]);
                double y = Double.parseDouble(line[1]);
                double res = Double.parseDouble(line[2]);

                Mockito.when(tf.compute(x * Math.PI / y, 0.001)).thenReturn(res);
            }
        } catch (IOException e) {
            System.out.println("io");
        } catch (CsvException e) {
            System.out.println("csv");
        }
    }

    private static void fillMockCos(Cos tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/trigonometryTestData/test-cos-data.csv"))) {
            List<String[]> lines = csvReader.readAll();
            for (String[] line : lines) {
                double x = Double.parseDouble(line[0]);
                double y = Double.parseDouble(line[1]);
                double res = Double.parseDouble(line[2]);

                Mockito.when(tf.compute(x * Math.PI / y, 0.001)).thenReturn(res);
            }
        } catch (IOException e) {
            System.out.println("io");
        } catch (CsvException e) {
            System.out.println("csv");
        }
    }

    private void runTest(TrigonometricSolver ts, Double divisible, Double divider, Double trueResult) {
        double x = divisible * Math.PI / divider, result;
        try {
            result = ts.compute(x, 0.001);
            assertEquals(trueResult, result, 0.001);
        } catch (ArithmeticException e) {
            assertEquals("Wrong x", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometryTestData/test-trig-func-data.csv")
    void isolatedFunctionTest(Double divisible, Double divider, Double trueResult) {
        TrigonometricSolver trigonometricExpression
                = new TrigonometricSolver(cos);
        runTest(trigonometricExpression, divisible, divider, trueResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometryTestData/test-trig-func-data.csv")
    void cosTest(Double divisible, Double divider, Double trueResult) {
        TrigonometricSolver trigonometricExpression
                = new TrigonometricSolver(new Cos(sin));
        runTest(trigonometricExpression, divisible, divider, trueResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometryTestData/test-trig-func-data.csv")
    void fullTest(Double divisible, Double divider, Double trueResult) {
        TrigonometricSolver trigonometricExpression
                = new TrigonometricSolver(new Cos(new Sin()));
        runTest(trigonometricExpression, divisible, divider, trueResult);
    }
}
