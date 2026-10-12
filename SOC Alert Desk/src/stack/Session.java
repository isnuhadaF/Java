package stack;
import alert.Alert;
import queue.Inbox;

import java.util.*;

public class Session {
    private final Inbox inbox;
    private final Deque<Alert> handled = new ArrayDeque<>();
    private int undoCount;

    public Session(Inbox inbox) {
        this.inbox = inbox;
    }

    public Optional<Alert> handleNext() {
        Optional<Alert> next = inbox.next();
        if (next.isPresent()) {
            handled.push(next.get());
            undoCount = 0;
        }
        return next;
    }

    public Optional<Alert> undo() {
        if (handled.isEmpty()) {
            return Optional.empty();
        }
        if (undoCount >= 3) throw new IllegalStateException("Cannot undo more than 3 times in a row");
        Alert undone = handled.pop();
        inbox.enqueue(undone);
        undoCount++;
        return Optional.of(undone);
    }

    public Optional<Alert> lastHandled() {
        return Optional.ofNullable(handled.peek());
    }

    public List<Alert> handledAlerts() {
        return new ArrayList<>(handled);
    }
}