public class Task {
    protected String description;
    protected Status status;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.status = Status.NOT_DONE;
        this.type = type;
    }

    public void markAsDone() {
        this.status = Status.DONE;
    }

    public void markAsUndone() {
        this.status = Status.NOT_DONE; // bugfix
    }

    public String getStatusIcon() {
        return status == Status.DONE ? "X" : " "; // mark done task with X
    }

    /** Pipe-delimited line used by Storage: T|1|description */
    public String toDataString() {
        String done = (status == Status.DONE) ? "1" : "0";
        return type.getShortCode() + " | " + done + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
