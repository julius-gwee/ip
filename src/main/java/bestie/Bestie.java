package bestie;

/**
 * Entry point of Bestie.
 *
 * <p>The class wires together the user interface, persistent storage, and
 * in-memory task list before handing control over to the {@link Parser} for the
 * command loop.</p>
 */

public class Bestie {
    /**
     * Bootstraps and runs the chatbot.
     *
     * @param args command-line arguments supplied by the user; ignored by Bestie
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList tasks;

        // Load from disk or start fresh.
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }

        // Introduction
        ui.showWelcome();

        Parser parser = new Parser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                isExit = parser.parse(input, tasks, ui, storage);
            } catch (BestieException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Something feels off! Check your input again dear!");
            } finally {
                ui.showLine();
            }
        }
    }

}
