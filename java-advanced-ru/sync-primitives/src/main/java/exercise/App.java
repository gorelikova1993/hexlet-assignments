package exercise;

class App {

    public static void main(String[] args) throws InterruptedException {
        // BEGIN
        // Создаем потокобезопасный лист
        SafetyList list = new SafetyList();

        // Создаем два потока, каждый добавит по 1000 элементов
        Thread thread1 = new Thread(new ListThread(list));
        Thread thread2 = new Thread(new ListThread(list));

        // Запускаем потоки
        thread1.start();
        thread2.start();

        // Ждём завершения работы потоков
        thread1.join();
        thread2.join();

        // Выводим количество элементов в листе
        System.out.println("Количество элементов в листе: " + list.getSize());
        // END
    }
}

