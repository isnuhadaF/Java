package alert;

import java.time.LocalDateTime;

public final class Alert {
    private final String id;
    private final String sourceIP;
    private final Type alertType;
    private final int severity;
    private final String description;
    private final LocalDateTime timestamp;

    public Alert(String id, String sourceIP, Type alertType, int severity,
                 String description, LocalDateTime timestamp) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id cannot be empty");
        }
        if (sourceIP == null || sourceIP.isBlank()) {
            throw new IllegalArgumentException("sourceIP cannot be empty");
        }
        if (alertType == null) {
            throw new IllegalArgumentException("alertType cannot be empty");
        }
        if (severity < 1 || severity > 5) {
            throw new IllegalArgumentException("Severity must be between 1 and 5");
        }
        if (description == null) {
            throw new IllegalArgumentException("description cannot be empty");
        }
        if (timestamp == null) {
            throw new IllegalArgumentException("timestamp cannot be empty");
        }
        this.id = id;
        this.sourceIP = sourceIP;
        this.alertType = alertType;
        this.severity = severity;
        this.description = description;
        this.timestamp = timestamp;
    }

    public String getId() { return id; }
    public String getSourceIP() { return sourceIP; }
    public Type getType() { return alertType; }
    public int getSeverity() { return severity; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return id + " [" + alertType + "] severity " + severity + " from " + sourceIP
                + " at " + timestamp + ": " + description;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Alert other = (Alert) object;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}