package exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// BEGIN
public class App {

    public static void main(String[] args) {
        List<Home> apartments = new ArrayList<>(List.of(
                new Flat(41, 3, 10),
                new Cottage(125.5, 2),
                new Flat(80, 10, 2),
                new Cottage(150, 3)
        ));

        List<String> result = App.buildApartmentsList(apartments, 3);
        System.out.println(result);
    }

    public static List<String> buildApartmentsList(List<Home> homes, int count) {
        if (homes.isEmpty()) {
            return List.of();
        }
        Map<Double, String> map = new HashMap<>();
        TreeSet<Double> ts = new TreeSet<>();
        for (Home home : homes) {
            map.put(home.getArea(), home.toString());
            ts.add(home.getArea());
        }
        List<String> list = new ArrayList<>();
        for (var key : ts) {
            list.add(map.get(key));
        }
        return list.subList(0, count);
    }
}
// END
