package task3;

import java.util.*;

public class RadioWaves {
    private static RadioWaves instance;
    private final Map<Float, List<String>> radioWaves = new HashMap<>();

    private RadioWaves() {}

    public static RadioWaves getInstance() {
        if (instance == null) {
            instance = new RadioWaves();
        }
        return instance;
    }

    public List<String> getRadioWave(Float frequency) {
        if (!radioWaves.containsKey(frequency)) {
            radioWaves.put(frequency, new LinkedList<>());
        }
        return radioWaves.get(frequency);
    }
}
