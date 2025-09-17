package bestie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ParserTest {
    @TempDir
    Path tempDir;

    private Storage storage;
    private Parser parser;
    private Ui ui;

    @BeforeEach
    void setup() {
        Path dataFile = tempDir.resolve("tasks.txt");
        storage = new Storage(dataFile);
        parser = new Parser();
        ui = new Ui();
    }

    @Test
    void parse_todoCommand_addsTaskAndPersistsToDisk() throws Exception {
        TaskList tasks = new TaskList();

        boolean shouldExit = parser.parse("todo finish writing tests", tasks, ui, storage);

        assertFalse(shouldExit, "todo command should not exit the app");
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertTrue(task instanceof Todo);
        assertEquals("finish writing tests", task.description);
        assertEquals(" ", task.getStatusIcon());

        List<String> lines = Files.readAllLines(tempDir.resolve("tasks.txt"), StandardCharsets.UTF_8);
        assertEquals(List.of("T | 0 | finish writing tests"), lines);
    }

    @Test
    void parse_todoMissingDescription_throwsBestieException() throws Exception {
        TaskList tasks = new TaskList();

        BestieException ex = assertThrows(BestieException.class,
                () -> parser.parse("todo   ", tasks, ui, storage));
        assertTrue(ex.getMessage().contains("cannot be empty"));
        assertEquals(0, tasks.size(), "No task should be added when parsing fails");
        assertTrue(Files.notExists(tempDir.resolve("tasks.txt")));
    }
}