package alert.test;
import alert.Alert;
import alert.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class AlertTest {
    private Alert alert(String id, int severity, String description){
        return new Alert(id, "10.0.0.5", Type.PHISHING, severity, description,
                LocalDateTime.of(2026, 10, 11, 9, 0));
    }

    @Test
    void testThat_alertWith_sameIDbutDifferent_descriptions_areEqual() {
        assertEquals(alert("A-1", 3, "first alert"), alert("A-1", 3, "second alert"));

    }
}
