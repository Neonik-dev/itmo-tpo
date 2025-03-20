package task3;

public class RadioTransmitter {
    private float frequency;
    private boolean isOn;

    public RadioTransmitter() {
        frequency = 0.0f;
        isOn = false;
    }

    public void configureFrequency(float frequency) {
        if (frequency < 0) {
            throw new IllegalArgumentException("Frequency can't be lower 0");
        }
        this.frequency = frequency;
    }

    public void transmitSignal(String text) {
        if (!isOn) {
            throw new IllegalStateException("RadioTransmitter is not on");
        }

        if (frequency == 0) {
            throw new IllegalStateException("Can't transmit when signal is 0");
        }

        RadioWaves.getInstance().getRadioWave(frequency).add(text);
    }

    public float getFrequency() {
        return frequency;
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
}
