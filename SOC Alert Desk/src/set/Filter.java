package set;
import alert.Alert;
import arraylist.Log;

import java.time.LocalDate;
import java.util.*;

public class Filter {
    private final Set<Alert> alerts;
    private final Set<String> blockedIPs = new HashSet<>();

    public Filter(Log log) {
        this.alerts = new LinkedHashSet<>(log.getAlerts());
    }

    public List<Alert> uniqueAlerts() {
        return new ArrayList<>(alerts);
    }

    public Set<String> uniqueIps() {
        Set<String> IPs = new LinkedHashSet<>();
        for (Alert alert:alerts) {
            IPs.add(alert.getSourceIP());
        }
        return new HashSet<>(blockedIPs);
    }
    public SortedSet<String> sortedIps() {
        return new TreeSet<>(blockedIPs);
    }
    public void block(String ip) {}
    public List<Alert> alertsFromBlockedIps() {
        return new ArrayList<>(alerts);
    }
    public Set<String> ipsOnDay(LocalDate day) {
        return new HashSet<>(blockedIPs);
    }
    public Set<String> ipsOnBothDays(LocalDate d1, LocalDate d2) {
        return new HashSet<>(blockedIPs);
    }
    public Set<String> ipsOnEitherDay(LocalDate d1, LocalDate d2) {
        return new HashSet<>(blockedIPs);
    }
    public Set<String> ipsOnlyOnFirstDay(LocalDate d1, LocalDate d2) {
        return new HashSet<>(blockedIPs);
    }
}
