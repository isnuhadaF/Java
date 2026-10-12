package queue;
import alert.Alert;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class Urgent implements Inbox {
    private final Queue<Alert> waitingAlerts = new PriorityQueue<>(
            Comparator.comparingInt(Alert::getSeverity).reversed()
                    .thenComparing(Alert::getTimestamp));

    @Override
    public void enqueue(Alert alert) {
        waitingAlerts.offer(alert);
    }

    @Override
    public Optional<Alert> next() {
        return Optional.ofNullable(waitingAlerts.poll());
    }

    @Override
    public Optional<Alert> peek() {
        return Optional.ofNullable(waitingAlerts.peek());
    }

    @Override
    public boolean isEmpty() {
        return waitingAlerts.isEmpty();
    }

    @Override
    public int size() {
        return waitingAlerts.size();
    }
}
