package linkedlist;
import alert.Alert;
import stack.Session;
import java.util.*;

public class Timeline {
    private final Map<String, LinkedList<Alert>> alertsByIp = new HashMap<>();

    public Timeline(Session session) {
        List<Alert> handledAlerts = session.handledAlerts();
        handledAlerts.sort(Comparator.comparing(Alert::getTimestamp));
        for (Alert alert : handledAlerts) {
            alertsByIp.computeIfAbsent(alert.getSourceIP(), ip -> new LinkedList<>())
                    .add(alert);
        }
    }

    public List<Alert> timelineFor(String ip) {
        LinkedList<Alert> alertsForIp = alertsByIp.get(ip);
        if (alertsForIp == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(alertsForIp);
    }

    // Time: O(n) to find earlierAlert, O(1) to insert. Space: O(1).
    public void insertAfter(Alert earlierAlert, Alert lateAlert) {
        if (!earlierAlert.getSourceIP().equals(lateAlert.getSourceIP())) {
            throw new IllegalArgumentException("Alerts come from different IPs");
        }
        LinkedList<Alert> alertsForIp = alertsByIp.get(earlierAlert.getSourceIP());
        if (alertsForIp == null) {
            throw new IllegalArgumentException("Earlier alert not found");
        }

        ListIterator<Alert> position = alertsForIp.listIterator();
        while (position.hasNext()) {
            Alert currentAlert = position.next();      // position now sits just after currentAlert
            if (currentAlert.equals(earlierAlert)) {
                if (lateAlert.getTimestamp().compareTo(currentAlert.getTimestamp()) < 0) {
                    throw new IllegalArgumentException("Late alert is older than the earlier alert");
                }
                if (position.hasNext()) {
                    Alert nextAlert = position.next();
                    position.previous();               // step back to just after currentAlert
                    if (lateAlert.getTimestamp().compareTo(nextAlert.getTimestamp()) > 0) {
                        throw new IllegalArgumentException("Late alert is newer than the next alert");
                    }
                }
                position.add(lateAlert);               // O(1): rewires two pointers
                return;
            }
        }
        throw new IllegalArgumentException("Earlier alert not found");
    }

    public boolean remove(Alert alertToRemove) {
        LinkedList<Alert> alertsForIp = alertsByIp.get(alertToRemove.getSourceIP());
        if (alertsForIp == null) {
            return false;
        }
        boolean wasRemoved = alertsForIp.remove(alertToRemove);
        if (alertsForIp.isEmpty()) {
            alertsByIp.remove(alertToRemove.getSourceIP());
        }
        return wasRemoved;
    }

    public int size() {
        int totalAlerts = 0;
        for (LinkedList<Alert> alertsForIp : alertsByIp.values()) {
            totalAlerts += alertsForIp.size();
        }
        return totalAlerts;
    }

    public List<Alert> backward(String ip) {
        List<Alert> newestFirst = new ArrayList<>();
        LinkedList<Alert> alertsForIp = alertsByIp.get(ip);
        if (alertsForIp == null) {
            return newestFirst;
        }
        ListIterator<Alert> position = alertsForIp.listIterator(alertsForIp.size());
        while (position.hasPrevious()) {
            newestFirst.add(position.previous());
        }
        return newestFirst;
    }
}