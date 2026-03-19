import java.util.*;
public class MapFactory {
    public static Map<String, String> getMap(int opcion) {
        switch (opcion) {
            case 1:
                return new HashMap<>();
            case 2:
                return new TreeMap<>();
            case 3:
                return new LinkedHashMap<>();
            default:
                return new HashMap<>();
        }
    }
}