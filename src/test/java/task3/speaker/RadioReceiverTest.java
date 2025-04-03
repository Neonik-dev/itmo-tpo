package task3.speaker;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.RadioWaves;

import static org.junit.jupiter.api.Assertions.*;

public class RadioReceiverTest {
    private RadioReceiver receiver;

    @BeforeEach
    void setUp() {
        receiver = new RadioReceiver();
    }

    @Test
    void frequencyIsLowerThanZeroTest() {
        assertThrows(IllegalArgumentException.class, () -> receiver.configureFrequency(-2));
    }

    @Test
    void receiveSignalWhenFrequencyIsZeroTest() {
        receiver.configureFrequency(0);
        assertThrows(IllegalStateException.class, () -> receiver.receiveSignal());
    }

    @Test
    void receiveSignalWhenFrequencyIsNegativeTest() {
        int frequency = 123;
        List<String> signals = RadioWaves.getInstance().getRadioWave(123f);
        assertTrue(signals.isEmpty());
        receiver.configureFrequency(frequency);
        List<String> messages = receiver.receiveSignal();
        assertEquals(1, messages.size());
    }

    @Test
    void calculateSignalStrengthTest() {
        receiver.configureFrequency(123);
        assertEquals(20.93422d, receiver.calculateSignalStrength(), 0.1E-4);
    }

    @Test
    void calculateSignalStrengthWithZeroFrequencyTest() {
        receiver.configureFrequency(0);
        assertEquals(0.0, receiver.calculateSignalStrength());
    }

    @Test
    void calculateSignalStrengthWithZeroPowerTest() {
        receiver.configureFrequency(100);
        receiver.configurePower(0);
        assertEquals(0.0, receiver.calculateSignalStrength());
    }

    @Test
    void calculateSignalStrengthWithZeroAndFrequencyPowerTest() {
        receiver.configureFrequency(0);
        receiver.configurePower(0);
        assertEquals(0.0, receiver.calculateSignalStrength());
    }
}
