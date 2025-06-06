package exercise;

import java.util.Arrays;
import java.util.List;

// BEGIN
public class MinThread extends Thread {
    private int minimum;
    private int[] numbers;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        int min = numbers[0];

        for (int currentNumber : numbers) {
            if (currentNumber < min) {
                min = currentNumber;
            }
        }

        minimum = min;
    }


    public int getMin() {
        return minimum;
    }
}
// END
