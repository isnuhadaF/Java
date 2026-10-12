package desk;

import alert.Alert;
import alert.Samples;
import arraylist.Log;
import linkedlist.Timeline;
import queue.Inbox;
import queue.Urgent;
import set.Filter;
import stack.Session;

import java.util.List;
import java.util.Scanner;

public class Desk {
    private final Log log;
    private final Inbox inbox = new Urgent();
    private final Filter filter;
    private final Session session;

    public Desk() {
        log = new Log(Samples.load());
        filter = new Filter(log);
        inbox.enqueueAll(filter.uniqueAlerts());
        session = new Session(inbox);
    }

    public static void main(String[] args) {
        new Desk().runMenu();
    }

    private void runMenu() {
        Scanner input = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1" -> printAll(log.sortByTime());
                case "2" -> printAll(log.sortBySeverity());
                case "3" -> System.out.println(filter.sortedIps());
                case "4" -> blockAnIp(input);
                case "5" -> printAll(filter.alertsFromBlockedIps());
                case "6" -> handleNext();
                case "7" -> undo();
                case "8" -> showTimeline(input);
                case "0" -> running = false;
                default -> System.out.println("Please pick a number from the menu.");
            }
        }
    }

    private void printMenu() {
        System.out.println("""

                1  Show the log, oldest first
                2  Show the log, most severe first
                3  Show unique IPs (sorted)
                4  Block an IP
                5  Show alerts from blocked IPs
                6  Handle next alert
                7  Undo
                8  Show a timeline for an IP
                0  Exit""");
    }

    private void blockAnIp(Scanner input) {
        System.out.print("IP to block: ");
        filter.block(input.nextLine().trim());
    }

    private void handleNext() {
        session.handleNext().ifPresentOrElse(
                alert -> System.out.println("Handling: " + alert),
                () -> System.out.println("The inbox is empty."));
    }

    private void undo() {
        try {
            session.undo().ifPresentOrElse(
                    alert -> System.out.println("Undone: " + alert),
                    () -> System.out.println("Nothing to undo."));
        } catch (IllegalStateException tooManyUndos) {
            System.out.println(tooManyUndos.getMessage());
        }
    }

    private void showTimeline(Scanner input) {
        System.out.print("IP: ");
        String ip = input.nextLine().trim();
        List<Alert> alertsForIp = new Timeline(session).timelineFor(ip);
        if (alertsForIp.isEmpty()) {
            System.out.println("No handled alerts for that IP.");
        }
        printAll(alertsForIp);
    }

    private void printAll(List<Alert> alerts) {
        for (Alert alert : alerts) {
            System.out.println(alert);
        }
    }
}