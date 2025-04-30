package equation;

import logarithm.Ln;
import logarithm.Log3;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import utils.CsvOutput;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogarithmSolverTest {
    private static final CsvOutput csvOutput = new CsvOutput();
    private static final Log3 log3 = new Log3(new Ln());
    private static final Log3 log3Mock = Mockito.mock(Log3.class);

    @ParameterizedTest
    @CsvFileSource(resources = "/equation/logSolverData.csv")
    void fullFromFile_OK(Double x, Double trueResult) {
        double eps = 0.001;
        LogarithmSolver solver = new LogarithmSolver(log3);
        csvOutput.setFilePath("src/test/resources/result/equation/logSolverAnswer.csv");

        double result = solver.compute(x, eps);
        csvOutput.logging(x, result);

        assertEquals(trueResult, result, eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/equation/log3MockSolverData.csv")
    void log3MockFromFile_OK(Double x, Double log3Result, Double trueResult) {
        double eps = 0.001;
        LogarithmSolver solver = new LogarithmSolver(log3Mock);
        csvOutput.setFilePath("src/test/resources/result/equation/log3MockSolverAnswer.csv");

        Mockito.when(log3Mock.compute(x, eps)).thenReturn(log3Result);
        double result = solver.compute(x, eps);
        csvOutput.logging(x, result);

        assertEquals(trueResult, result, eps);
    }
}
