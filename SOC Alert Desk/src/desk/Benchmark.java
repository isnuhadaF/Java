package desk;

import alert.Alert;
import alert.Type;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Benchmark {
    private static final int BIG = 100_000;
    private static final int SMALL = 20_000;
    private static final int RUNS = 5;
    private static long sink;   // stops Java from skipping the "read" work

    public static void run() {
        List<Alert> bigSet = makeAlerts(BIG);
        List<Alert> smallSet = makeAlerts(SMALL);

        System.out.printf("%-45s %12s %12s%n", "Test (milliseconds, median)", "ArrayList", "LinkedList");
        row("Insert 100,000 at the front", false, bigSet,
                list -> { for (Alert alert : bigSet) list.add(0, alert); });
        row("Insert 100,000 at the end", false, bigSet,
                list -> { for (Alert alert : bigSet) list.add(alert); });
        row("Remove 100,000 from the front", true, bigSet,
                list -> { while (!list.isEmpty()) list.remove(0); });
        row("Read 100,000 with an iterator", true, bigSet,
                list -> { for (Alert alert : list) sink += alert.getSeverity(); });
        row("Read 20,000 with get(i)", true, smallSet,
                list -> { for (int i = 0; i < list.size(); i++) sink += list.get(i).getSeverity(); });
    }

    private static void row(String label, boolean startsFilled, List<Alert> alerts,
                            Consumer<List<Alert>> test) {
        double arrayListMs = medianMillis(startingList(ArrayList::new, startsFilled, alerts), test);
        double linkedListMs = medianMillis(startingList(LinkedList::new, startsFilled, alerts), test);
        System.out.printf("%-45s %12.2f %12.2f%n", label, arrayListMs, linkedListMs);
    }

    private static Supplier<List<Alert>> startingList(Supplier<List<Alert>> newList,
                                                      boolean startsFilled, List<Alert> alerts) {
        return () -> {
            List<Alert> list = newList.get();
            if (startsFilled) {
                list.addAll(alerts);
            }
            return list;
        };
    }

    private static double medianMillis(Supplier<List<Alert>> startingList, Consumer<List<Alert>> test) {
        test.accept(startingList.get());                  // warm-up run, thrown away
        long[] times = new long[RUNS];
        for (int run = 0; run < RUNS; run++) {
            List<Alert> list = startingList.get();        // setup is NOT timed
            long start = System.nanoTime();
            test.accept(list);
            times[run] = System.nanoTime() - start;
        }
        Arrays.sort(times);
        return times[RUNS / 2] / 1_000_000.0;
    }

    private static List<Alert> makeAlerts(int count) {    // created before any timer starts
        List<Alert> alerts = new ArrayList<>();
        LocalDateTime start = LocalDateTime.of(2026, 10, 11, 0, 0);
        for (int i = 0; i < count; i++) {
            alerts.add(new Alert("B-" + i, "10.0.0." + (i % 250), Type.values()[0],
                    (i % 5) + 1, "benchmark alert", start.plusSeconds(i)));   // assumption 3
        }
        return alerts;
    }
}