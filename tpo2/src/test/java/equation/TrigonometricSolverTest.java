package equation;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.List;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import trigonometry.Cos;
import trigonometry.Sin;
import utils.CsvOutput;

import static org.junit.Assert.assertEquals;

public class TrigonometricSolverTest {
    public static Sin sin = Mockito.mock(Sin.class);
    public static Cos cos = Mockito.mock(Cos.class);
    private static final CsvOutput csvOutput = new CsvOutput();

    @BeforeAll
    public static void fillAll() {
        fillMockSin(sin);
        fillMockCos(cos);
    }

    @SneakyThrows
    private static void fillMockSin(Sin tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/trigonometryTestData/test-sin-data.csv"))) {
            List<String[]> lines = csvReader.readAll();
            for (String[] line : lines) {
                double x = Double.parseDouble(line[0]);
                double y = Double.parseDouble(line[1]);
                double res = Double.parseDouble(line[2]);

                Mockito.when(tf.compute(x * Math.PI / y, 0.001)).thenReturn(res);
            }
        }
    }

    @SneakyThrows
    private static void fillMockCos(Cos tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/trigonometryTestData/test-cos-data.csv"))) {
            List<String[]> lines = csvReader.readAll();
            for (String[] line : lines) {
                double x = Double.parseDouble(line[0]);
                double y = Double.parseDouble(line[1]);
                double res = Double.parseDouble(line[2]);

                Mockito.when(tf.compute(x * Math.PI / y, 0.001)).thenReturn(res);
            }
        }
    }

    private void runTest(TrigonometricSolver ts, Double divisible, Double divider, Double trueResult) {
        double x = divisible * Math.PI / divider;
        double result;
        try {
            result = ts.compute(x, 0.001);
            csvOutput.logging(x, result);
            assertEquals(trueResult, result, 0.001);
        } catch (ArithmeticException e) {
            assertEquals("Wrong x", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometryTestData/test-trig-func-data.csv")
    void isolatedFunctionTest(Double divisible, Double divider, Double trueResult) {
        csvOutput.setFilePath("src/test/resources/result/equation/trigonometrySolverAnswer.csv");
        TrigonometricSolver trigonometricExpression = new TrigonometricSolver(cos);
        runTest(trigonometricExpression, divisible, divider, trueResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometryTestData/test-trig-func-data.csv")
    void cosTest(Double divisible, Double divider, Double trueResult) {
        csvOutput.setFilePath("src/test/resources/result/equation/trigonometrySolverAnswer.csv");
        TrigonometricSolver trigonometricExpression = new TrigonometricSolver(new Cos(sin));
        runTest(trigonometricExpression, divisible, divider, trueResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometryTestData/test-trig-func-data.csv")
    void fullTest(Double divisible, Double divider, Double trueResult) {
        csvOutput.setFilePath("src/test/resources/result/equation/trigonometrySolverAnswer.csv");
        TrigonometricSolver trigonometricExpression = new TrigonometricSolver(new Cos(new Sin()));
        runTest(trigonometricExpression, divisible, divider, trueResult);
    }
}
