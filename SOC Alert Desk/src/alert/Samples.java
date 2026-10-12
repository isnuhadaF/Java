package alert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class Samples {

    private Samples() { }

    public static List<Alert> load() {
        List<Alert> alerts = new ArrayList<>();

        alerts.add(alert("A-01", "203.0.113.7",   Type.BRUTE_FORCE,      3, at(11, 9, 15),  "Repeated failed VPN logins"));
        alerts.add(alert("A-02", "10.0.0.5",      Type.PHISHING,         2, at(11, 8, 40),  "Fake invoice email reported"));
        alerts.add(alert("A-03", "203.0.113.7",   Type.BRUTE_FORCE,      4, at(11, 9, 45),  "Failed logins continue"));
        alerts.add(alert("A-04", "198.51.100.23", Type.PORT_SCAN,        1, at(11, 7, 30),  "Sequential port probe"));
        alerts.add(alert("A-05", "10.0.0.9",      Type.MALWARE,          4, at(11, 11, 5),  "Known malware hash on a laptop"));
        alerts.add(alert("A-06", "10.0.0.5",      Type.PHISHING,         3, at(11, 10, 20), "Second phishing email, same sender"));
        alerts.add(alert("A-07", "172.16.4.8",    Type.DATA_EXFILTRATION, 5, at(11, 14, 10), "Large upload to an unknown host"));
        alerts.add(alert("A-08", "192.168.1.20",  Type.PORT_SCAN,        2, at(11, 12, 0),  "Port probe on the printer subnet"));
        alerts.add(alert("A-09", "203.0.113.44",  Type.MALWARE,          3, at(11, 13, 25), "Suspicious download blocked"));
        alerts.add(alert("A-10", "198.51.100.23", Type.PORT_SCAN,        2, at(11, 7, 55),  "Port probe repeated"));

        alerts.add(alert("A-12", "203.0.113.7",   Type.BRUTE_FORCE,      4, at(12, 8, 10),  "Attack on the admin portal"));
        alerts.add(alert("A-13", "10.0.0.5",      Type.PHISHING,         1, at(12, 9, 0),   "Low-risk spam link"));
        alerts.add(alert("A-14", "192.168.1.20",  Type.BRUTE_FORCE,      3, at(12, 10, 30), "Failed SSH logins"));
        alerts.add(alert("A-15", "203.0.113.44",  Type.DATA_EXFILTRATION, 4, at(12, 11, 15), "Outbound data spike"));
        alerts.add(alert("A-16", "172.16.4.8",    Type.MALWARE,          2, at(12, 12, 40), "Unsigned program executed"));
        alerts.add(alert("A-17", "198.51.100.99", Type.PORT_SCAN,        1, at(12, 13, 5),  "Single probe, no follow-up"));
        alerts.add(alert("A-18", "10.0.0.77",     Type.PHISHING,         3, at(12, 14, 20), "Credential-harvesting page visited"));

        alerts.add(alert("A-12", "203.0.113.7",   Type.BRUTE_FORCE,      4, at(12, 8, 12),  "Same alert, re-sent by the sensor"));

        alerts.add(alert("A-19", "192.168.1.20",  Type.DATA_EXFILTRATION, 5, at(12, 17, 45), "Large transfer at end of day"));

        return alerts;
    }

    private static LocalDateTime at(int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(2026, 10, dayOfMonth, hour, minute);
    }

    private static Alert alert(String id, String ip, Type type, int severity, LocalDateTime timeStamp, String note) {
        return new Alert(id, ip, type, severity, note, timeStamp);
    }
}