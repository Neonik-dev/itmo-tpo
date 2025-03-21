package task3.speaker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task3.RadioWaves;

import static org.junit.jupiter.api.Assertions.*;

public class SpeakerTest {
    private Speaker speaker;
    private RadioWaves radioWaves;

    @BeforeEach
    void setUp() {
        speaker = new Speaker(new RadioReceiver());
        radioWaves = RadioWaves.getInstance();
    }

    @Test
    void noSoundWhenOffTest() {
        float frequency = 100.7f;
        radioWaves.getRadioWave(frequency).add("Test message");
        speaker.turnOff();
        assertTrue(speaker.getSound().isEmpty());
    }

    @Test
    void noSoundWhenOnAndVolumeIsZeroTest() {
        float frequency = 100.7f;
        float zeroVolume = 0.0f;
        radioWaves.getRadioWave(frequency).add("Test message");
        speaker.turnOn();
        speaker.configureVolume(zeroVolume);
        assertTrue(speaker.getSound().isEmpty());
    }

    @Test
    void getSoundWhenOnAndVolumeIsMoreThenZeroTest() {
        float frequency = 100.7f;
        float nonZeroVolume = 50.0f;
        String testMessage = "Test message";
        radioWaves.getRadioWave(frequency).add(testMessage);
        speaker.turnOn();
        speaker.configureVolume(nonZeroVolume);
        speaker.configureRadioReceiverFrequency(frequency);
        assertTrue(speaker.getSound().contains(testMessage));
    }

    @Test
    void getSoundWhenOffAndVolumeIsMoreThenEightyTest() {
        float frequency = 100.7f;
        float loudVolume = 90.0f;
        String testMessage = "Test message";
        radioWaves.getRadioWave(frequency).add(testMessage);
        speaker.turnOn();
        speaker.configureRadioReceiverFrequency(frequency);
        speaker.configureVolume(loudVolume);
        String message = speaker.getSound().get(0);
        assertEquals(message, message.toUpperCase());
    }

    @Test
    void turnOnTest() {
        speaker.turnOn();
        assertTrue(speaker.isOn());
    }

    @Test
    void turnOffTest() {
        speaker.turnOff();
        assertFalse(speaker.isOn());
    }
}
