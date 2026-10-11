package alert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class Samples {

    private Samples() { }

    public static List<Alert> load() {
        List<Alert> alerts = new ArrayList<>();

        // Day 1 (Oct 11), not in time order on purpose
        alerts.add(new Alert("A-01", "203.0.113.7", Type.BRUTE_FORCE, 3, "Repeated failed VPN logins", LocalDateTime.now()));
        alerts.add(new Alert("A-02", "10.0.0.5", Type.PHISHING, 2, "Fake invoice email reported", LocalDateTime.now()));
        alerts.add(new Alert("A-03", "203.0.113.7", Type.BRUTE_FORCE, 4, "Failed logins continue", LocalDateTime.now()));
        alerts.add(new Alert("A-04", "198.51.100.23", Type.PORT_SCAN, 1, "Sequential port probe", LocalDateTime.now()));
        alerts.add(new Alert("A-05", "10.0.0.9", Type.MALWARE, 4, "Known malware hash on a laptop", LocalDateTime.now()));
        alerts.add(new Alert("A-06", "10.0.0.5", Type.PHISHING, 3, "Second phishing email, same sender", LocalDateTime.now()));
        alerts.add(new Alert("A-07", "172.16.4.8", Type.DATA_EXFILTRATION, 5, "Large upload to an unknown host", LocalDateTime.now()));
        alerts.add(new Alert("A-08", "192.168.1.20", Type.PORT_SCAN, 2, "Port probe on the printer subnet", LocalDateTime.now()));
        alerts.add(new Alert("A-09", "203.0.113.44", Type.MALWARE, 3, "Suspicious download blocked", LocalDateTime.now()));
        alerts.add(new Alert("A-10", "198.51.100.23", Type.PORT_SCAN, 2, "Port probe repeated", LocalDateTime.now()));

        // Day 2 (Oct 12)
        alerts.add(new Alert("A-12", "203.0.113.7", Type.BRUTE_FORCE, 4, "Attack on the admin portal", LocalDateTime.now()));
        alerts.add(new Alert("A-13", "10.0.0.5", Type.PHISHING, 1, "Low-risk spam link", LocalDateTime.now()));
        alerts.add(new Alert("A-14", "192.168.1.20", Type.BRUTE_FORCE, 3, "Failed SSH logins", LocalDateTime.now()));
        alerts.add(new Alert("A-15", "203.0.113.44", Type.DATA_EXFILTRATION, 4, "Outbound data spike", LocalDateTime.now()));
        alerts.add(new Alert("A-16", "172.16.4.8", Type.MALWARE, 2, "Unsigned program executed", LocalDateTime.now()));
        alerts.add(new Alert("A-17", "198.51.100.23", Type.PORT_SCAN, 1, "Single probe, no follow-up", LocalDateTime.now()));
        alerts.add(new Alert("A-18", "10.0.0.9", Type.PHISHING, 3, "Credential-harvesting page visited", LocalDateTime.now()));

        // Same id as A-12, sent twice by the sensor on purpose. Stage 2 removes it.
        alerts.add(alert("A-12", "203.0.113.7", Type.BRUTE_FORCE, 4, LocalDateTime.now(), "Same alert, re-sent by the sensor"));

        // Severity 5, and the latest timestamp of all
        alerts.add(new Alert("A-19", "192.168.1.20", Type.DATA_EXFILTRATION, 5, "Large transfer at end of day", LocalDateTime.now()));

        return alerts;
    }

    // The only place that calls the Alert constructor
    private static Alert alert(String id, String ip, Type type, int severity, LocalDateTime timeStamp, String note) {
        return new Alert(id, ip, type, severity, note, timeStamp);
    }
}