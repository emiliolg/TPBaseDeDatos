package biblioteca.model;

import java.util.HashMap;
import java.util.Map;

//Cada enum tiene un hashmap q usa un enum como key y la distancia total de value
public enum Location {
//todo crear tabla de doble entrada, los hashmaps no se inicializan y devuelven null
    CABA(
            new HashMap<Location, Integer>() {{
                put(CABA, 0);
                put(CORDOBA, 646);
                put(CORRIENTES, 792);
                put(FORMOSA, 933);
                put(LAPLATA, 53);
                put(LARIOJA, 986);
                put(MENDOZA, 985);
                put(NEUQUEN, 989);
            }}
    ),
    CORDOBA(
            new HashMap<Location, Integer>() {{
                put(CABA, 646);
                put(CORDOBA, 0);
                put(CORRIENTES, 677);
                put(FORMOSA, 824);
                put(LAPLATA, 698);
                put(LARIOJA, 340);
                put(MENDOZA, 466);
                put(NEUQUEN, 907);
            }}
    ),
    CORRIENTES(
            new HashMap<Location, Integer>() {{
                put(CABA, 792);
                put(CORDOBA, 677);
                put(CORRIENTES, 0);
                put(FORMOSA, 157);
                put(LAPLATA, 830);
                put(LARIOJA, 814);
                put(MENDOZA, 1131);
                put(NEUQUEN, 1534);
            }}
    ),
    FORMOSA(
            new HashMap<Location, Integer>() {{
                put(CABA, 933);
                put(CORDOBA, 824);
                put(CORRIENTES, 157);
                put(FORMOSA, 0);
                put(LAPLATA, 968);
                put(LARIOJA, 927);
                put(MENDOZA, 1269);
                put(NEUQUEN, 1690);
            }}
    ),
    LAPLATA(
            new HashMap<Location, Integer>() {{
                put(CABA, 53);
                put(CORDOBA, 698);
                put(CORRIENTES, 830);
                put(FORMOSA, 968);
                put(LAPLATA, 0);
                put(LARIOJA, 1038);
                put(MENDOZA, 1029);
                put(NEUQUEN, 1005);
            }}
    ),
    LARIOJA(
            new HashMap<Location, Integer>() {{
                put(CABA, 986);
                put(CORDOBA, 340);
                put(CORRIENTES, 814);
                put(FORMOSA, 927);
                put(LAPLATA, 1038);
                put(LARIOJA, 0);
                put(MENDOZA, 427);
                put(NEUQUEN, 1063);
            }}
    ),
    MENDOZA(
            new HashMap<Location, Integer>() {{
                put(CABA, 985);
                put(CORDOBA, 466);
                put(CORRIENTES, 1131);
                put(FORMOSA, 1269);
                put(LAPLATA, 1029);
                put(LARIOJA, 427);
                put(MENDOZA, 0);
                put(NEUQUEN, 676);
            }}
    ),
    NEUQUEN(
            new HashMap<Location, Integer>() {{
                put(CABA, 989);
                put(CORDOBA, 907);
                put(CORRIENTES, 1534);
                put(FORMOSA, 1690);
                put(LAPLATA, 1005);
                put(LARIOJA, 1063);
                put(MENDOZA, 676);
                put(NEUQUEN, 0);
            }}
    );


    private final Map<Location, Integer> distances;

    Location(Map<Location, Integer> distances) {
        this.distances = distances;
    }

    int getDistanceTo(Location to) {
        return distances.get(to);
    }
}
