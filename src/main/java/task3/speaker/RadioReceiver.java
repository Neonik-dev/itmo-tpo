package task3.speaker;

import java.util.List;
import java.util.Random;
import task3.RadioWaves;

public class RadioReceiver {
    private float frequency;

    public RadioReceiver() {
        frequency = 0.0f;
    }

    protected void configureFrequency(float frequency) {
        if (frequency < 0) {
            throw new IllegalArgumentException("Frequency can't be lower 0");
        }
        this.frequency = frequency;
    }

    public List<String> receiveSignal() {
        if (frequency == 0) {
            throw new IllegalStateException("Frequency can't be 0");
        }

        List<String> messages = RadioWaves.getInstance().getRadioWave(frequency);
        if (messages.isEmpty()) return List.of(generateString(new Random(), new Random().nextInt(1, 100)));

        return messages;
    }

    private String generateString(Random rng, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            String characters = "qwertyuiopasdfghjklzxcvbnm1234567890";
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
