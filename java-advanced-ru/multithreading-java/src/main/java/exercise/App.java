package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.stream.Collectors;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        List<Integer> num = Arrays.stream(numbers).boxed().collect(Collectors.toList());

        Thread maxThread = new MaxThread(num);
        maxThread.start();
        LOGGER.info("INFO: Thread MaxThread started");
        try {
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("INFO: Thread MaxThread finished");
        Thread minThread = new MinThread(num);
        minThread.start();
        LOGGER.info("INFO: Thread MinThread started");

        try {
            minThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("INFO: Thread MinThread finished");

        Map<String, Integer> map = new HashMap<>();
        map.put("min", ((MinThread) minThread).getMin());
        map.put("max", ((MaxThread) maxThread).getMaxInt());
        return map;
    }

    public static void main(String[] args)  {
        int[] numbers = {10, -4, 67, 100, -100, 8};

        System.out.println(App.getMinMax(numbers));
    }
    // END
}
