package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

class App {
    // BEGIN
    // Пул потоков для асинхронного выполнения задач
    private static final ExecutorService executor = Executors.newFixedThreadPool(4);
    // END
    /**
     * Асинхронно читает содержимое двух файлов и записывает их объединённый текст в третий файл.
     *
     * @param path1 путь до первого файла
     * @param path2 путь до второго файла
     * @param destinationPath путь до итогового файла
     * @return CompletableFuture со строкой — объединённым содержимым двух файлов
     */
    public static CompletableFuture<String> unionFiles(String path1, String path2, String destinationPath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Читаем оба файла как строки
                String content1 = Files.readString(Paths.get(path1));
                String content2 = Files.readString(Paths.get(path2));
                String combinedContent = content1 + content2;
                Path destPath = Paths.get(destinationPath);
                // Создаём файл, если он не существует
                if (Files.notExists(destPath)) {
                    Files.createFile(destPath);
                }
                // Записываем объединённое содержимое
                Files.writeString(destPath, combinedContent);
                return combinedContent;
            } catch (IOException e) {
                // В случае ошибки выводим сообщение и пробрасываем исключение дальше
                System.out.println("Ошибка при чтении или записи файла: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }, executor);
    }
    /**
     * Асинхронно вычисляет размер всех файлов в указанной директории (без учёта поддиректорий).
     *
     * @param dirPath путь к директории
     * @return CompletableFuture с общим размером файлов в байтах
     */
    public static CompletableFuture<Long> getDirectorySize(String dirPath) {
        return CompletableFuture.supplyAsync(() -> {
            try (Stream<Path> paths = Files.list(Paths.get(dirPath))) {
                // Оставляем только обычные файлы, получаем их размер и складываем
                return paths
                        .filter(Files::isRegularFile)
                        .mapToLong(path -> {
                            try {
                                return Files.size(path);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .sum();
            } catch (IOException e) {
                throw new RuntimeException("Не удалось прочитать директорию: " + e.getMessage(), e);
            }
        }, executor);
    }

    public static void main(String[] args) throws Exception {
        // BEGIN
        // Указываем пути к файлам
        String file1 = "src/main/resources/file1.txt";
        String file2 = "src/main/resources/file2.txt";
        String destFile = "src/main/resources/result.txt";
        // Вызываем асинхронный метод и выводим результат после завершения
        unionFiles(file1, file2, destFile).thenAccept(result -> {
            System.out.println("Файлы успешно объединены. Результат записан в файл.");
        }).exceptionally(e -> {
            System.out.println("Ошибка при объединении файлов: " + e.getMessage());
            return null;
        });
        // END
    }
}

