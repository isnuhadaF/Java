package arraylist;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import alert.Alert;
import alert.Samples;

public class Log {
    private List<Alert> alerts = new ArrayList<>();

    public Log(List<Alert> alerts) {
        this.alerts = alerts;
    }
    public void addAlert(Alert alert) {
        if (alerts.contains(alert)) throw new IllegalArgumentException("Alert already exists");
        if (alert == null) throw new IllegalArgumentException("Alert is empty");
        alerts.add(alert);
    }

    public int count() {
        return alerts.size();
    }

    public Alert getAlertID(String id) {
        for (Alert alert:alerts) {
            if (alert.getId().equals(id)) { return alert; } else throw  new IllegalArgumentException("Alert " + id + " not found");
        }
        return null;
    }

    public void removeByID(String id) {
        for (Alert alert:alerts) {
            if (alert.getId().equals(id)) {  alerts.remove(alert);}
            else throw  new IllegalArgumentException("Alert " + id + " not found");
        }

    }

    public List<Alert> sortSeverity(int severity) {
        List<Alert> filteredAlerts = new ArrayList<>(List.copyOf(alerts));
        filteredAlerts.sort(Comparator.comparing(Alert::getSeverity));

        return filteredAlerts;
    }

    public List<Alert> sortByTime() {
        List<Alert> sortedAlerts = new ArrayList<>(List.copyOf(alerts));
        sortedAlerts.sort(Comparator.comparing(Alert::getTimestamp));
        return sortedAlerts;
    }

    public List<Alert> getAlerts() {
        return List.copyOf(alerts);
    }

    public void addAll() {
        this.alerts = Samples.load();
    }

}
