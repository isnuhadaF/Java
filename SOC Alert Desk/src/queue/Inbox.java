package queue;

import alert.Alert;
import java.util.List;
import java.util.Optional;

public interface Inbox {


    void enqueue(Alert alert);
    Optional<Alert> next();
    Optional<Alert> peek();
    boolean isEmpty();
    int size();

    default void enqueueAll(List<Alert> alerts) {
        for (Alert alert: alerts) {
            enqueue(alert);
        }
    }
}
