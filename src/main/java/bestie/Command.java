package bestie;

public abstract class Command {
    /** Override if this command should exit the app */
    public boolean isExit() {
        return false;
    }

    /** Execute the command logic. */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BestieException;
}
