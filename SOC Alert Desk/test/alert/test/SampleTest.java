package alert.test;
import alert.Alert;
import alert.Samples;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SampleTest {

    @Test
    void loadReturnsTwentyAlerts() {
        assertEquals(20, Samples.load().size());
    }

    @Test
    void exactlyOneIdAppearsTwice() {
        List<Alert> alerts = Samples.load();
        long distinctIds = alerts.stream().map(Alert::getId).distinct().count();
        assertEquals(19, distinctIds);
    }

    @Test
    void alertsSpanTwoDays() {
        long days = Samples.load().stream()
                .map(a -> a.getTimestamp().toLocalDate())
                .distinct()
                .count();
        assertEquals(2, days);
    }

    @Test
    void everyCallReturnsANewList() {
        assertNotSame(Samples.load(), Samples.load());
    }
}