import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
    // typed (if parsable)
    private final LocalDate fromDate;
    private final LocalDateTime fromDateTime;
    private final LocalDate toDate;
    private final LocalDateTime toDateTime;

    // raw fallbacks
    private final String fromRaw;
    private final String toRaw;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.fromRaw = from.trim();
        this.toRaw = to.trim();

        LocalDateTime fdt = DateTimeUtil.parseDateTime(this.fromRaw);
        LocalDateTime tdt = DateTimeUtil.parseDateTime(this.toRaw);

        if (fdt != null) {
            this.fromDateTime = fdt;
            this.fromDate = null;
        } else {
            this.fromDate = DateTimeUtil.parseDate(this.fromRaw);
            this.fromDateTime = null;
        }

        if (tdt != null) {
            this.toDateTime = tdt;
            this.toDate = null;
        } else {
            this.toDate = DateTimeUtil.parseDate(this.toRaw);
            this.toDateTime = null;
        }
    }

    @Override
    public String toString() {
        String fromNice, toNice;

        if (fromDateTime != null) fromNice = DateTimeUtil.pretty(fromDateTime);
        else if (fromDate != null) fromNice = DateTimeUtil.pretty(fromDate);
        else fromNice = fromRaw;

        if (toDateTime != null) toNice = DateTimeUtil.pretty(toDateTime);
        else if (toDate != null) toNice = DateTimeUtil.pretty(toDate);
        else toNice = toRaw;

        return "[E]" + super.toString() + " (from: " + fromNice + " to: " + toNice + ")";
    }

    @Override
    public String toDataString() {
        String done = (status == Status.DONE) ? "1" : "0";
        String fromStored, toStored;

        if (fromDateTime != null) fromStored = DateTimeUtil.canonical(fromDateTime);
        else if (fromDate != null) fromStored = DateTimeUtil.canonical(fromDate);
        else fromStored = fromRaw;

        if (toDateTime != null) toStored = DateTimeUtil.canonical(toDateTime);
        else if (toDate != null) toStored = DateTimeUtil.canonical(toDate);
        else toStored = toRaw;

        return type.getShortCode() + " | " + done + " | " + description + " | " + fromStored + " | " + toStored;
    }
}
