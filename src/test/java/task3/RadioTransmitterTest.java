package task3;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RadioTransmitterTest {
    private RadioTransmitter transmitter;

    @BeforeEach
    void setUp() {
        transmitter = new RadioTransmitter();
    }

    @Test
    void frequencyIsLowerThanZeroTest() {
        assertThrows(IllegalArgumentException.class, () -> transmitter.configureFrequency(-1));
    }

    @Test
    void frequencySetCorrectTest() {
        float frequency = 100;
        transmitter.configureFrequency(frequency);
        assertEquals(frequency, transmitter.getFrequency());
    }

    @Test
    void transmitSignalWhenOffTest() {
        transmitter.configureFrequency(10);
        assertThrows(IllegalStateException.class, () -> transmitter.transmitSignal("123"));
    }

    @Test
    void transmitSignalWhenOnTest() {
        float frequency = 10;
        transmitter.turnOn();
        transmitter.configureFrequency(frequency);
        String message = "123";
        transmitter.transmitSignal(message);
        assertTrue(RadioWaves.getInstance().getRadioWave(frequency).contains(message));
    }

    @Test
    void transmitSignalWhenOnAndFrequencyIsZeroTest() {
        transmitter.configureFrequency(0);
        transmitter.turnOn();
        assertThrows(IllegalStateException.class, () -> transmitter.transmitSignal("123"));
    }

    @Test
    void turnOnTest() {
        transmitter.turnOn();
        assertTrue(transmitter.isOn());
    }

    @Test
    void turnOffTest() {
        transmitter.turnOff();
        assertFalse(transmitter.isOn());
    }
}
