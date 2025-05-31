package exercise;

import java.util.ArrayList;

class SafetyList {

    private ArrayList<Integer> list;

    public SafetyList() {
        list = new ArrayList<>();
    }

    // Метод add может выполняться только одним потоком одновременно
    public synchronized void add(int value) {
        list.add(value);
    }

    public int get(int index) {
        return list.get(index);
    }

    public int getSize() {
        return list.size();
    }

}
