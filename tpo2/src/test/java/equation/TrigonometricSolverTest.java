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
    private static void fillMockSin(Sin ts) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/trigonometryTestData/test-sin-data.csv"))) {
            List<String[]> lines = csvReader.readAll();
            for (String[] line : lines) {
                double x = Double.parseDouble(line[0]);
                double y = Double.parseDouble(line[1]);
                double res = Double.parseDouble(line[2]);

                Mockito.when(ts.compute(x * Math.PI / y, 0.001)).thenReturn(res);
            }
        }
    }

    @SneakyThrows
    private static void fillMockCos(Cos tf) {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/trigonometryTestData/test-cos-data.csv"))) {
            List<String[]> lines = csvReader.readAll();
            for (String[] line : lines) {
                double n = Double.parseDouble(line[0]);
                double k = Double.parseDouble(line[1]);
                double res = Double.parseDouble(line[2]);

                Mockito.when(tf.compute(n * Math.PI / k, 0.001)).thenReturn(res);
            }
        }
    }

    private void execute(TrigonometricSolver ts, Double n, Double k, Double trueResult) {
        double arg = calcArg(n, k);
        double result;
        try {
            result = ts.compute(arg, 0.001);
            csvOutput.logging(arg, result);
            assertEquals(trueResult, result, 0.001);
        } catch (ArithmeticException e) {
            assertEquals("Неверный аргумент x", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometryTestData/test-trig-func-data.csv")
    void isolatedFunctionTest(Double n, Double k, Double expected) {
        csvOutput.setFilePath("src/test/resources/result/equation/trigonometrySolverAnswerIsolated.csv");
        TrigonometricSolver trigonometricExpression = new TrigonometricSolver(cos);
        execute(trigonometricExpression, n, k, expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometryTestData/test-trig-func-data.csv")
    void cosTest(Double n, Double k, Double expected) {
        csvOutput.setFilePath("src/test/resources/result/equation/trigonometrySolverAnswerCos.csv");
        TrigonometricSolver trigonometricExpression = new TrigonometricSolver(new Cos(sin));
        execute(trigonometricExpression, n, k, expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometryTestData/test-trig-func-data.csv")
    void fullTest(Double n, Double k, Double expected) {
        csvOutput.setFilePath("src/test/resources/result/equation/trigonometrySolverAnswerFull.csv");
        TrigonometricSolver trigonometricExpression = new TrigonometricSolver(new Cos(new Sin()));
        execute(trigonometricExpression, n, k, expected);
    }

    private static double calcArg(Double n, Double k) {
        return n * Math.PI / k;
    }
}
