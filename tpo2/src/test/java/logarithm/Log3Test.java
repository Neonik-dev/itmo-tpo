package logarithm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import utils.CsvOutput;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Log3Test extends LnTest {
    private static final CsvOutput csvOutput = new CsvOutput();
    private static final Log3 log3 = new Log3(new Ln());
    private static final Ln lnMock = Mockito.mock(Ln.class);
    private static final Log3 log3Mock = new Log3(lnMock);


    @Test
    void checkLn_OK() {
        assertAll(
                () -> assertEquals(0, log3.compute(1, 0.001)),
                () -> assertEquals(2.0959, log3.compute(10, 0.001), 0.01),
                () -> assertEquals(2.4649, log3.compute(15, 0.001), 0.01)
        );
    }

    @Test
    void checkLargeLn_OK() {
        double eps = 0.001;
        assertAll(
                () -> assertEquals(7.62974, log3.compute(12342, eps), eps),
                () -> assertEquals(8.07497, log3.compute(34234893284.23423, eps), eps),
                () -> assertEquals(7.15449, log3.compute(4343, eps), eps)
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logarithm/log3Data.csv")
    void sequenceFromFile_OK(Double x, Double trueResult) {
        double eps = 0.001;
        csvOutput.setFilePath("src/test/resources/result/logarithm/log3Answers.csv");

        double result = log3.compute(x, eps);
        csvOutput.logging(x, result);

        assertEquals(trueResult, result, eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logarithm/lnMockData.csv")
    void setLnMock_OK(Double x, Double lnResult, Double log3Result) {
        double eps = 0.001;
        csvOutput.setFilePath("src/test/resources/result/logarithm/log3MockAnswers.csv");

        Mockito.when(lnMock.compute(x, eps)).thenReturn(lnResult);
        double result = log3Mock.compute(x, eps);
        csvOutput.logging(x, result);

        assertEquals(log3Result, result, eps);
    }
}