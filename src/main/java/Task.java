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
        this.status = Status.DONE;
    }

    public String getStatusIcon() {
        return status == Status.DONE ? "X" : " "; // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

