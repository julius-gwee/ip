package bestie;

public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String shortCode;

    TaskType(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getShortCode() {
        return shortCode;
    }
}
