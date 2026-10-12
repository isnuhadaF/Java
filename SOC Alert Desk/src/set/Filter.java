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


    //Unique Alerts
    public List<Alert> uniqueAlerts() {
        return new ArrayList<>(alerts);
    }



    //Unique IPs
    public Set<String> uniqueIps() {
        Set<String> IPs = new LinkedHashSet<>();
        for (Alert alert: this.alerts) {
            IPs.add(alert.getSourceIP());
        }
        return IPs;
    }



    //Sorted
    public SortedSet<String> sortedIps() {
        return new TreeSet<>(this.uniqueIps());
    }



    //Blocked
    public void block(String ip) {
        blockedIPs.add(ip);
    }



    //BlockedIPAlerts
    public List<Alert> alertsFromBlockedIps() {
        List<Alert> result = new ArrayList<>();
        for (Alert alert: this.alerts) {
            if (blockedIPs.contains(alert.getSourceIP())) {
                result.add(alert);
            }
        }
        return result;
    }



    //IP's on select day
    public Set<String> ipsOnDay(LocalDate day) {
        Set<String> ips = new LinkedHashSet<>();
        for (Alert alert : alerts) {
            if (alert.getTimestamp().toLocalDate().equals(day)) {
                ips.add(alert.getSourceIP());
            }
        }
        return ips;
    }



    //IP's on select days
    public Set<String> ipsOnBothDays(LocalDate d1, LocalDate d2) {
        Set<String> IPs = new LinkedHashSet<>(ipsOnDay(d1));
        IPs.retainAll(ipsOnDay(d2));   // keep only IPs also in day 2
        return IPs;
    }


    //IP's on either days
    public Set<String> ipsOnEitherDay(LocalDate d1, LocalDate d2) {
        Set<String> ips = new LinkedHashSet<>(ipsOnDay(d1));
        ips.addAll(ipsOnDay(d2));
        return ips;
    }


    //IP's on the first day
    public Set<String> ipsOnlyOnFirstDay(LocalDate d1, LocalDate d2) {
        Set<String> result = new LinkedHashSet<>(ipsOnDay(d1));
        result.removeAll(ipsOnDay(d2));
        return result;
    }
}
