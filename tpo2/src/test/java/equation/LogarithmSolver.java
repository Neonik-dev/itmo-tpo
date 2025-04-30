package equation;

import logarithm.Log3;

public class LogarithmSolver {

    private static void fillMock(LogarithmicFunction lf, String path) {
        try (CSVReader csvReader = new CSVReader(new FileReader(path))) {
            List<String[]> lines = csvReader.readAll();
            for (String[] line : lines) {
                final double x = Double.parseDouble(line[0]);
                final double res = Double.parseDouble(line[1]);

                Mockito.when(lf.compute(x, 0.001)).thenReturn(res);
            }
        } catch (IOException e) {
            System.out.println("io");
        } catch (CsvException e) {
            System.out.println("csv");
        }
    }

    private void runTest(LogarithmicExpression le, Double x, Double trueResult){
        try {
            double result = le.calculate(x, 0.001);
            assertEquals(trueResult, result, 0.001);
        } catch (ArithmeticException e) {
            assertEquals("Wrong x", e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputLog/solverData.csv")
    void lnTest(Double x, Double trueResult) {
        LogarithmicExpression logarithmicExpression
                = new LogarithmicExpression(new Ln(), log3, log5, log10);
        runTest(logarithmicExpression, x, trueResult);
    }
}
