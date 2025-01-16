package exercise;

import java.util.List;

// BEGIN
public class MaxThread extends Thread{
    private int maxInt;
    private List<Integer> numbers;

    public MaxThread(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
       maxInt = numbers.stream().mapToInt(Integer::intValue).max().getAsInt();
    }

    public int getMaxInt() {
        return maxInt;
    }
}
// END
