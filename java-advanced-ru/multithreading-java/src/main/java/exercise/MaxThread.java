package exercise;

import java.util.Arrays;
import java.util.List;

// BEGIN
public class MaxThread extends Thread{
    private int maxInt;
    private int[] numbers;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
       maxInt = Arrays.stream(numbers).max().getAsInt();
    }

    public int getMaxInt() {
        return maxInt;
    }
}
// END
