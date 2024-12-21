package jbar.music;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScaleRepository {

    // Todas las escalas organizadas por tipo y tono
    public static final Map<String, Map<String, List<String>>> SCALES = new HashMap<>();

    static {
        // Escalas Mayores
        SCALES.put("Mayor", new HashMap<>());
        SCALES.get("Mayor").put("C", Arrays.asList("C", "D", "E", "F", "G", "A", "B"));
        SCALES.get("Mayor").put("C#", Arrays.asList("C#", "D#", "F", "F#", "G#", "A#", "C"));
        SCALES.get("Mayor").put("D", Arrays.asList("D", "E", "F#", "G", "A", "B", "C#"));
        SCALES.get("Mayor").put("D#", Arrays.asList("D#", "F", "G", "G#", "A#", "C", "D"));
        SCALES.get("Mayor").put("E", Arrays.asList("E", "F#", "G#", "A", "B", "C#", "D#"));
        SCALES.get("Mayor").put("F", Arrays.asList("F", "G", "A", "A#", "C", "D", "E"));
        SCALES.get("Mayor").put("F#", Arrays.asList("F#", "G#", "A#", "B", "C#", "D#", "F"));
        SCALES.get("Mayor").put("G", Arrays.asList("G", "A", "B", "C", "D", "E", "F#"));
        SCALES.get("Mayor").put("G#", Arrays.asList("G#", "A#", "C", "C#", "D#", "F", "G"));
        SCALES.get("Mayor").put("A", Arrays.asList("A", "B", "C#", "D", "E", "F#", "G#"));
        SCALES.get("Mayor").put("A#", Arrays.asList("A#", "C", "D", "D#", "F", "G", "A"));
        SCALES.get("Mayor").put("B", Arrays.asList("B", "C#", "D#", "E", "F#", "G#", "A#"));

        // Escalas Menores Naturales
        SCALES.put("Menor Natural", new HashMap<>());
        SCALES.get("Menor Natural").put("C", Arrays.asList("C", "D", "D#", "F", "G", "G#", "A#"));
        SCALES.get("Menor Natural").put("C#", Arrays.asList("C#", "D#", "E", "F#", "G#", "A", "B"));
        SCALES.get("Menor Natural").put("D", Arrays.asList("D", "E", "F", "G", "A", "A#", "C"));
        SCALES.get("Menor Natural").put("D#", Arrays.asList("D#", "F", "F#", "G#", "A#", "B", "C#"));
        SCALES.get("Menor Natural").put("E", Arrays.asList("E", "F#", "G", "A", "B", "C", "D"));
        SCALES.get("Menor Natural").put("F", Arrays.asList("F", "G", "G#", "A#", "C", "C#", "D#"));
        SCALES.get("Menor Natural").put("F#", Arrays.asList("F#", "G#", "A", "B", "C#", "D", "E"));
        SCALES.get("Menor Natural").put("G", Arrays.asList("G", "A", "A#", "C", "D", "D#", "F"));
        SCALES.get("Menor Natural").put("G#", Arrays.asList("G#", "A#", "B", "C#", "D#", "E", "F#"));
        SCALES.get("Menor Natural").put("A", Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
        SCALES.get("Menor Natural").put("A#", Arrays.asList("A#", "C", "C#", "D#", "F", "F#", "G#"));
        SCALES.get("Menor Natural").put("B", Arrays.asList("B", "C#", "D", "E", "F#", "G", "A"));

        // Escalas Pentatónicas Mayores
        SCALES.put("Pentatónica Mayor", new HashMap<>());
        SCALES.get("Pentatónica Mayor").put("C", Arrays.asList("C", "D", "E", "G", "A"));
        SCALES.get("Pentatónica Mayor").put("C#", Arrays.asList("C#", "D#", "F", "G#", "A#"));
        SCALES.get("Pentatónica Mayor").put("D", Arrays.asList("D", "E", "F#", "A", "B"));
        SCALES.get("Pentatónica Mayor").put("D#", Arrays.asList("D#", "F", "G", "A#", "C"));
        SCALES.get("Pentatónica Mayor").put("E", Arrays.asList("E", "F#", "G#", "B", "C#"));
        SCALES.get("Pentatónica Mayor").put("F", Arrays.asList("F", "G", "A", "C", "D"));
        SCALES.get("Pentatónica Mayor").put("F#", Arrays.asList("F#", "G#", "A#", "D", "E"));
        SCALES.get("Pentatónica Mayor").put("G", Arrays.asList("G", "A", "B", "D", "E"));
        SCALES.get("Pentatónica Mayor").put("G#", Arrays.asList("G#", "A#", "C", "D#", "F"));
        SCALES.get("Pentatónica Mayor").put("A", Arrays.asList("A", "B", "C#", "E", "F#"));
        SCALES.get("Pentatónica Mayor").put("A#", Arrays.asList("A#", "C", "D", "F", "G"));
        SCALES.get("Pentatónica Mayor").put("B", Arrays.asList("B", "C#", "D#", "F#", "G#"));

        // Escalas Pentatónicas Menores
        SCALES.put("Pentatónica Menor", new HashMap<>());
        SCALES.get("Pentatónica Menor").put("C", Arrays.asList("C", "D#", "F", "G", "A#"));
        SCALES.get("Pentatónica Menor").put("C#", Arrays.asList("C#", "E", "F#", "G#", "B"));
        SCALES.get("Pentatónica Menor").put("D", Arrays.asList("D", "F", "G", "A", "C"));
        SCALES.get("Pentatónica Menor").put("D#", Arrays.asList("D#", "F#", "G#", "A#", "C#"));
        SCALES.get("Pentatónica Menor").put("E", Arrays.asList("E", "G", "A", "B", "D"));
        SCALES.get("Pentatónica Menor").put("F", Arrays.asList("F", "G#", "A#", "C", "D#"));
        SCALES.get("Pentatónica Menor").put("F#", Arrays.asList("F#", "A", "B", "C#", "E"));
        SCALES.get("Pentatónica Menor").put("G", Arrays.asList("G", "A#", "C", "D", "F"));
        SCALES.get("Pentatónica Menor").put("G#", Arrays.asList("G#", "B", "C#", "D#", "F#"));
        SCALES.get("Pentatónica Menor").put("A", Arrays.asList("A", "C", "D", "E", "G"));
        SCALES.get("Pentatónica Menor").put("A#", Arrays.asList("A#", "C#", "D#", "F", "G#"));
        SCALES.get("Pentatónica Menor").put("B", Arrays.asList("B", "D", "E", "F#", "A"));
    }
}
