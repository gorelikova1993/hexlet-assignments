package exercise;

import java.util.List;

// BEGIN
public class MinThread extends Thread {
    private int min;
    private List<Integer> numbers;

    public MinThread(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
         min = numbers.stream().mapToInt(Integer::intValue).min().getAsInt();
    }

    public int getMin() {
        return min;
    }
}
// END
