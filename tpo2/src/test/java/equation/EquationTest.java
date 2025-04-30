package equation;

import logarithm.Ln;
import logarithm.Log3;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import trigonometry.Cos;
import trigonometry.Sin;
import utils.CsvOutput;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquationTest {
    private static final CsvOutput csvOutput = new CsvOutput();
    private static final LogarithmSolver logarithmSolver = new LogarithmSolver(new Log3(new Ln()));
    private static final LogarithmSolver logarithmSolverMock = Mockito.mock(LogarithmSolver.class);
    private static final TrigonometricSolver trigonometricSolver = new TrigonometricSolver(new Cos(new Sin()));
    private static final TrigonometricSolver trigonometricSolverMock = Mockito.mock(TrigonometricSolver.class);


    @ParameterizedTest
    @CsvFileSource(resources = "/equation/equationData.csv")
    void fullFromFile_OK(Double x, Double y, Double trueResult) {
        double eps = 0.001;
        if ( y != 0 ) {
            x = x * Math.PI / y;
        }
        Equation equation = new Equation(logarithmSolver, trigonometricSolver);
        csvOutput.setFilePath("src/test/resources/result/equation/equationAnswer.csv");

        double result = equation.compute(x, eps);
        csvOutput.logging(x, result);

        assertEquals(trueResult, result, eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/equation/equationData.csv")
    void setTrigonometricSolverMockFromFile_OK(Double x, Double y, Double trueResult) {
        double eps = 0.001;
        if ( y != 0 ) {
            x = x * Math.PI / y;
        }
        Equation equation = new Equation(logarithmSolver, trigonometricSolverMock);
        csvOutput.setFilePath("src/test/resources/result/equation/equationTrigonometricSolverMockAnswer.csv");

        Mockito.when(trigonometricSolverMock.compute(x, eps)).thenReturn(trueResult);
        double result = equation.compute(x, eps);
        csvOutput.logging(x, result);

        assertEquals(trueResult, result, eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/equation/equationData.csv")
    void setLogarithmSolverMockFromFile_OK(Double x, Double y, Double trueResult) {
        double eps = 0.001;
        if ( y != 0 ) {
            x = x * Math.PI / y;
        }
        Equation equation = new Equation(logarithmSolverMock, trigonometricSolver);
        csvOutput.setFilePath("src/test/resources/result/equation/equationLogarithmSolverMockAnswer.csv");

        Mockito.when(logarithmSolverMock.compute(x, eps)).thenReturn(trueResult);
        double result = equation.compute(x, eps);
        csvOutput.logging(x, result);

        assertEquals(trueResult, result, eps);
    }

}
