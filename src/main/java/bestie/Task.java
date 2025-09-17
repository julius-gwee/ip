package bestie;

/**
 * Represents a single actionable item tracked by Bestie.
 */
public class Task {
    protected String description;
    protected Status status;
    protected TaskType type;

    /**
     * Creates a new task with the given description and type.
     *
     * @param description user-entered description of the task
     * @param type        type discriminator used for formatting and storage
     */
    public Task(String description, TaskType type) {
        assert description != null : "Task description must not be null";
        assert type != null : "Task type must not be null";
        this.description = description;
        this.status = Status.NOT_DONE;
        this.type = type;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.status = Status.DONE;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsUndone() {
        this.status = Status.NOT_DONE; // bugfix
    }

    /**
     * Returns the status icon used when displaying the task.
     *
     * @return {@code "X"} if done, otherwise a single space
     */
    public String getStatusIcon() {
        return status == Status.DONE ? "X" : " "; // mark done task with X
    }

    /**
     * Returns the human-readable description of the task.
     *
     * @return description entered by the user
     */
    public String getDescription() {
        return description;
    }

    /**
     * Serializes the task into the pipe-delimited format used by
     * {@link Storage}.
     *
     * @return representation such as {@code T | 1 | read book}
     */
    public String toDataString() {
        String done = (status == Status.DONE) ? "1" : "0";
        return type.getShortCode() + " | " + done + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
