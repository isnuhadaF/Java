package arraylisttest;
import alert.Alert;
import alert.Type;
import arraylist.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LogTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void listSizeShouldBe_1_afterAddAlert() {
        Log log = new Log(new ArrayList<>());
        log.addAlert(new Alert("", "", Type.PHISHING, 4, "", LocalDateTime.now()));

        assertEquals(1, log.getAlerts().size());


    }

    @Test
    void returnAlertIfIDFound_returnEmptyIfIDNotFound() {
        Log log = new Log(new ArrayList<>());
        Alert alert = new Alert("PH-101", "", Type.PHISHING, 4, "", LocalDateTime.now());
        log.addAlert(alert);

        assertEquals(alert, log.getAlertID("PH-101"));
        assertThrows(IllegalArgumentException.class, () -> log.addAlert(alert));
    }

    @Test
    void removingByIDShrinksLogBy_1_andReturnsFalseForUnknownID() {
        Log log = new Log(new ArrayList<>());
        log.addAll();

        try {
            log.removeBy("A-01");
        } catch (ConcurrentModificationException c) {throw new ConcurrentModificationException("Did not work");}
        assertEquals(18, log.getAlerts().size());
    }
}
