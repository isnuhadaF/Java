package alert;
import java.time.LocalDateTime;

public final class Alert {
    private final String id;
    private final String sourceIP;
    private final Type alertType;
    private final int severity;
    private final LocalDateTime timestamp;
    private final String description;

    public Alert(String id, String sourceIP, Type alertType, int severity, String description, LocalDateTime timestamp) {

        this.id = id;
        this.sourceIP = sourceIP;
        if (alertType == null) { throw new IllegalArgumentException("alertType cannot be empty"); } else { this.alertType = alertType; }
        if (severity < 0 || severity > 5) { throw new IllegalArgumentException("Severity must be between 0 and 5"); }
        else { this.severity = severity; }

        this.timestamp = timestamp;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public Type getAlertType() {
        return alertType;
    }

    public int getSeverity() {
        return severity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Alert alert = (Alert) object;
        return id.equals(alert.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

