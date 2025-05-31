package exercise;

// BEGIN
public class ListThread implements Runnable {

    private SafetyList list;

    // Конструктор, принимающий SafetyList
    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1); // Задержка в 1 мс
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Восстанавливаем флаг прерывания
                break;
            }
            list.add(i); // Добавляем элемент в лист
        }
    }
}
// END
