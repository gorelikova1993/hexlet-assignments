package exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.concurrent.ExecutionException;

class AppTest {
    private String destPath;

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    @BeforeEach
    void beforeEach() throws Exception {
        destPath = Files.createTempFile("test", "tmp").toString();
    }

    @Test
    void testUnion() throws Exception {
        CompletableFuture<String> result = App.unionFiles(
            "src/test/resources/file1.txt",
            "src/test/resources/file2.txt",
            destPath
        );
        result.get();

        String actual = Files.readString(getFullPath(destPath));
        assertThat(actual).contains("Test", "Message");
    }

    @Test
    void testUnionWithNonExistedFile() throws Exception {

        String result = tapSystemOut(() -> {
            App.unionFiles("nonExistingFile", "file", destPath).get();
        });

        assertThat(result.trim()).contains("NoSuchFileException");
    }

    // BEGIN
    /**
     * Проверяет корректность вычисления размера директории без поддиректорий.
     */
    @Test
    void testGetDirectorySize() throws IOException, ExecutionException, InterruptedException {
        // Создаём временную директорию
        Path tempDir = Files.createTempDirectory("testDir");
        // Создаём два файла и записываем в них строки
        Path file1 = Files.createFile(tempDir.resolve("file1.txt"));
        Path file2 = Files.createFile(tempDir.resolve("file2.txt"));
        Files.writeString(file1, "Hello");
        Files.writeString(file2, "World!");
        // Ожидаемый размер — сумма размеров двух файлов
        long expectedSize = Files.size(file1) + Files.size(file2);
        // Получаем результат от асинхронного метода
        long actualSize = App.getDirectorySize(tempDir.toString()).get();
        // Проверяем совпадение
        assertEquals(expectedSize, actualSize);
        // Удаляем файлы и директорию после теста
        Files.deleteIfExists(file1);
        Files.deleteIfExists(file2);
        Files.deleteIfExists(tempDir);
    }
    // END
}
