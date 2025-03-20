package task3.speaker;

import java.util.ArrayList;
import java.util.List;

public class Speaker {
    private final RadioReceiver radioReceiver;
    private boolean isOn;
    private float volume;

    Speaker(RadioReceiver radioReceiver) {
        this.radioReceiver = radioReceiver;
        this.isOn = false;
        this.volume = 0;
    }

    public List<String> getSound() {
        if (volume <= 0 || !isOn) return new ArrayList<>();
        List<String> messages = radioReceiver.receiveSignal();
        if (volume >= 80) {
            return messages.stream().map(String::toUpperCase).toList();
        }
        return messages;
    }

    public boolean isOn() {
        return isOn;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public void configureRadioReceiverFrequency(float frequency) {
        radioReceiver.configureFrequency(frequency);
    }

    public void configureVolume(float volume) {
        this.volume = volume;
    }
}
