package equation;

import logarithm.Ln;
import logarithm.Log3;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import utils.CsvOutput;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogarithmSolverTest {
    private static final CsvOutput csvOutput = new CsvOutput();
    private static Log3 log3 = new Log3(new Ln());
    private static Log3 log3Mock = Mockito.mock(Log3.class);

//    @SneakyThrows
//    private static void fillMock(LogarithmicFunction lf, String path) {
//        try (CSVReader csvReader = new CSVReader(new FileReader(path))) {
//            List<String[]> lines = csvReader.readAll();
//            for (String[] line : lines) {
//                final double x = Double.parseDouble(line[0]);
//                final double res = Double.parseDouble(line[1]);
//
//                Mockito.when(lf.compute(x, 0.001)).thenReturn(res);
//            }
//        } catch (IOException e) {
//            System.out.println("io");
//        } catch (CsvException e) {
//            System.out.println("csv");
//        }
//    }
//
//    private void runTest(LogarithmicExpression le, Double x, Double trueResult){
//        try {
//            double result = le.calculate(x, 0.001);
//            assertEquals(trueResult, result, 0.001);
//        } catch (ArithmeticException e) {
//            assertEquals("Wrong x", e.getMessage());
//        }
//    }
//
//    @ParameterizedTest
//    @CsvFileSource(resources = "/inputLog/solverData.csv")
//    void lnTest(Double x, Double trueResult) {
//        LogarithmSolver logarithmicExpression = new LogarithmSolver(new Ln(), log3, log5, log10);
//        runTest(logarithmicExpression, x, trueResult);
//    }

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
}
