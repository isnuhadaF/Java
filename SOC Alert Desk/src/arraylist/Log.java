package arraylist;

import alert.Alert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Log {
    private final List<Alert> alerts;

    public Log(List<Alert> alerts) {
        this.alerts = new ArrayList<>(alerts);
    }

    public void addAlert(Alert alert) {
        if (alert == null) {
            throw new IllegalArgumentException("Alert is empty");
        }
        alerts.add(alert);
    }

    public void addAll(List<Alert> newAlerts) {
        alerts.addAll(newAlerts);
    }

    public int count() {
        return alerts.size();
    }

    public Alert getAlertID(String id) {
        for (Alert alert : alerts) {
            if (alert.getId().equals(id)) {
                return alert;
            }
        }
        throw new IllegalArgumentException("Alert " + id + " not found");
    }

    public void removeBy(String id) {
        boolean wasRemoved = alerts.removeIf(alert -> alert.getId().equals(id));
        if (!wasRemoved) {
            throw new IllegalArgumentException("Alert " + id + " not found");
        }
    }

    public List<Alert> severityFilter(int minSeverity) {
        List<Alert> matchingAlerts = new ArrayList<>();
        for (Alert alert : alerts) {
            if (alert.getSeverity() >= minSeverity) {
                matchingAlerts.add(alert);
            }
        }
        return matchingAlerts;
    }

    public List<Alert> sortBySeverity() {
        List<Alert> sortedAlerts = new ArrayList<>(alerts);
        sortedAlerts.sort(Comparator.comparing(Alert::getSeverity).reversed());
        return sortedAlerts;
    }

    public List<Alert> sortByTime() {
        List<Alert> sortedAlerts = new ArrayList<>(alerts);
        sortedAlerts.sort(Comparator.comparing(Alert::getTimestamp));
        return sortedAlerts;
    }

    public List<Alert> getAlerts() {
        return List.copyOf(alerts);
    }
}