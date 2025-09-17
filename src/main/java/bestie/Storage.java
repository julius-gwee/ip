package bestie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage() {
        // ./data/bestie.txt (relative + OS-independent)
        this(Paths.get("data", "bestie.txt"));
    }

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /** Load tasks from disk. If file is missing, return empty list. */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return tasks; // first run
        }

        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        int lineNo = 0;
        for (String raw : lines) {
            lineNo++;
            String line = raw.trim();
            if (line.isEmpty()) continue;

            try {
                tasks.add(parseLine(line));
            } catch (BestieException ex) {
                // Corrupted line: skip but don't crash; log to stderr so UI output stays clean.
                System.err.println("Skipping corrupted line " + lineNo + ": " + ex.getMessage());
            }
        }
        return tasks;
    }

    /** Save all tasks to disk (overwrites). Creates the ./data folder if needed. */
    public void save(List<Task> tasks) throws IOException {
        Path parent = filePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(t.toDataString());
        }
        Files.write(
                filePath,
                lines,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
        );
    }

    /** Parse a line like:
     *  T | 1 | read book
     *  D | 0 | return book | Sunday
     *  E | 0 | project meeting | Mon 2pm | 4pm
     */
    private Task parseLine(String line) throws BestieException {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new BestieException("Too few fields: " + line);
        }
        String type = parts[0].trim();
        String doneFlag = parts[1].trim();

        boolean isDone;
        if ("1".equals(doneFlag)) {
            isDone = true;
        } else if ("0".equals(doneFlag)) {
            isDone = false;
        } else {
            throw new BestieException("Invalid done flag (must be 0/1): " + doneFlag);
        }

        Task t;
        switch (type) {
        case "T":
            if (parts.length < 3) {
                throw new BestieException("Todo missing description");
            }
            t = new Todo(parts[2]);
            break;
        case "D":
            if (parts.length < 4) {
                throw new BestieException("Deadline missing /by");
            }
            t = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            if (parts.length < 5) {
                throw new BestieException("Event missing /from or /to");
            }
            t = new Event(parts[2], parts[3], parts[4]);
            break;
        default:
            throw new BestieException("Unknown task type: " + type);
        }

        if (isDone) {
            t.markAsDone();
        }
        return t;
    }
}