package dairytest;
import dairy.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryTest {

    private Entry entry;

    @BeforeEach
    void setUp() {
        entry = new Entry(1, "First Entry", "This is the body");
    }

    @Test
    void testEntryInitialization() {
        assertEquals(1, entry.getId());
        assertEquals("First Entry", entry.getTitle());
        assertEquals("This is the body", entry.getBody());
        assertNotNull(entry.getDate());
    }

    @Test
    void testSetTitle() {
        entry.setTitle("Updated Title");
        assertEquals("Updated Title", entry.getTitle());
    }

    @Test
    void testSetBody() {
        entry.setBody("Updated body content");
        assertEquals("Updated body content", entry.getBody());
    }
}