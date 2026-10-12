package queue;
import alert.Alert;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

public class Oldest implements Inbox {
    private final Queue<Alert> waitingAlerts = new ArrayDeque<>();


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
