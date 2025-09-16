package bestie;

public class Parser {
    public boolean parse(String input, TaskList tasks, Ui ui, Storage storage) throws BestieException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        switch (command) {
            case "bye":
                ui.showBye();
                return true;
            case "list":
                ui.showList(tasks);
                return false;
            case "mark":
                if (parts.length < 2) {
                    throw new BestieException("Please specify which task to mark!");
                }
                int markIndex = Integer.parseInt(parts[1]) - 1;
                Task markTask = tasks.get(markIndex);
                markTask.markAsDone();
                saveQuiet(storage, tasks);
                ui.showMark(markTask);
                return false;
            case "unmark":
                if (parts.length < 2) {
                    throw new BestieException("Please specify which task to unmark!");
                }
                int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                Task unmarkTask = tasks.get(unmarkIndex);
                unmarkTask.markAsUndone();
                saveQuiet(storage, tasks);
                ui.showUnmark(unmarkTask);
                return false;
            case "delete":
                if (parts.length < 2) {
                    throw new BestieException("Please specify which task to delete!");
                }
                int delIndex = Integer.parseInt(parts[1]) - 1;
                Task removed = tasks.remove(delIndex);
                saveQuiet(storage, tasks);
                ui.showDelete(removed, tasks.size());
                return false;
            case "todo":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new BestieException("The description of a todo cannot be empty!");
                }
                Task todo = new Todo(parts[1].trim());
                tasks.add(todo);
                saveQuiet(storage, tasks);
                ui.showAdd(todo, tasks.size());
                return false;
            case "deadline":
                if (parts.length < 2 || !parts[1].contains("/by")) {
                    throw new BestieException("Deadline command must have a /by date/time!");
                }
                String[] deadlineParts = parts[1].split("/by", 2);
                if (deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                    throw new BestieException("Deadline description and date/time cannot be empty!");
                }
                Task deadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                tasks.add(deadline);
                saveQuiet(storage, tasks);
                ui.showAdd(deadline, tasks.size());
                return false;
            case "event":
                if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                    throw new BestieException("Event must have /from and /to times!");
                }
                String[] eventParts = parts[1].split("/from", 2);
                String descr = eventParts[0].trim();
                String[] fromTo = eventParts[1].split("/to", 2);
                if (descr.isEmpty() || fromTo[0].trim().isEmpty() || fromTo[1].trim().isEmpty()) {
                    throw new BestieException("Event description and times cannot be empty!");
                }
                Task event = new Event(descr, fromTo[0].trim(), fromTo[1].trim());
                tasks.add(event);
                saveQuiet(storage, tasks);
                ui.showAdd(event, tasks.size());
                return false;
            default:
                throw new BestieException("OOPS!!! I'm sorry, but I don't know what you are sayin :-(");
        }
    }

    private void saveQuiet(Storage storage, TaskList tasks) {
        try {
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            System.err.println("Failed to save tasks: " + e.getMessage());
        }
    }
}
