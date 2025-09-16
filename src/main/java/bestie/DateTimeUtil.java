package bestie;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeUtil {
    private DateTimeUtil() {}

    // Accepted input formats (you can add more if you like)
    private static final DateTimeFormatter[] DATE_PATTERNS = new DateTimeFormatter[] {
            DateTimeFormatter.ISO_LOCAL_DATE,             // yyyy-MM-dd
            DateTimeFormatter.ofPattern("d/M/yyyy"),      // 2/12/2019
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),    // 02/12/2019
            DateTimeFormatter.ofPattern("M/d/yyyy"),      // 12/2/2019
            DateTimeFormatter.ofPattern("MM/dd/yyyy")     // 12/02/2019
    };

    private static final DateTimeFormatter[] DATETIME_PATTERNS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"), // 2019-12-02 1800
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),   // 2/12/2019 1800
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"), // 02/12/2019 1800
            DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),   // 12/2/2019 1800
            DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm")  // 12/02/2019 1800
    };

    // Pretty output formats
    private static final DateTimeFormatter PRETTY_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter PRETTY_DATETIME = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    // Canonical storage formats
    private static final DateTimeFormatter CANON_DATE = DateTimeFormatter.ISO_LOCAL_DATE; // yyyy-MM-dd
    private static final DateTimeFormatter CANON_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static LocalDate parseDate(String s) {
        String x = s.trim();
        for (DateTimeFormatter f : DATE_PATTERNS) {
            try { return LocalDate.parse(x, f); } catch (DateTimeParseException ignored) {}
        }
        return null;
    }

    public static LocalDateTime parseDateTime(String s) {
        String x = s.trim();
        for (DateTimeFormatter f : DATETIME_PATTERNS) {
            try { return LocalDateTime.parse(x, f); } catch (DateTimeParseException ignored) {}
        }
        return null;
    }

    public static String pretty(LocalDate d) {
        return d.format(PRETTY_DATE);
    }

    public static String pretty(LocalDateTime dt) {
        return dt.format(PRETTY_DATETIME);
    }

    public static String canonical(LocalDate d) {
        return d.format(CANON_DATE);
    }

    public static String canonical(LocalDateTime dt) {
        return dt.format(CANON_DATETIME);
    }
}
