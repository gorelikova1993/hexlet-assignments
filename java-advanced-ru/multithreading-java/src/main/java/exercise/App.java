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
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);

        maxThread.start();
        LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " started");

        minThread.start();
        LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " started");
        try {
            minThread.join();
            LOGGER.log(Level.INFO, "Thread " + minThread.getName() + " finished");
            maxThread.join();
            LOGGER.log(Level.INFO, "Thread " + maxThread.getName() + " finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Map result = Map.of(
                "min",  minThread.getMin(),
                "max", maxThread.getMaxInt()
        );

        LOGGER.log(Level.INFO, "Result: " + result.toString());

        return result;
    }

    public static void main(String[] args)  {
        int[] numbers = {10, -4, 67, 100, -100, 8};

        System.out.println(App.getMinMax(numbers));
    }
    // END
}
