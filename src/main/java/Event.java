public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toDataString() {
        String done = (status == Status.DONE) ? "1" : "0";
        return type.getShortCode() + " | " + done + " | " + description + " | " + from + " | " + to;
    }
}
