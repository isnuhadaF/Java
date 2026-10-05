package dairytest;
import dairy.Diary;
import dairy.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryTest {

    private Diary diary;

    @BeforeEach
    void setUp() {
        diary = new Diary("user1", "pass123");
    }

    @Test
    void testDiaryIsLockedByDefault() {
        assertTrue(diary.isLocked());
    }

    @Test
    void testUnlockDiaryWithCorrectPassword() {
        diary.unlock("pass123");
        assertFalse(diary.isLocked());
    }

    @Test
    void testUnlockDiaryWithIncorrectPasswordThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> diary.unlock("wrongPass"));
        assertTrue(diary.isLocked());
    }

    @Test
    void testLockDiary() {
        diary.unlock("pass123");
        diary.lock();
        assertTrue(diary.isLocked());
    }

    @Test
    void testCreateEntryWhenLockedThrowsException() {
        diary.lock();

        assertThrows(IllegalStateException.class, () -> diary.create("Title", "Body"));
    }

    @Test
    void testFindEntryByIdWhenLockedThrowsException() {
        assertThrows(IllegalStateException.class, () -> diary.find(1));
    }

    @Test
    void testUpdateEntryWhenLockedThrowsException() {
        assertThrows(IllegalStateException.class, () -> diary.update(1, "Title", "Body"));
    }

    @Test
    void testDeleteEntryWhenLockedThrowsException() {
        assertThrows(IllegalStateException.class, () -> diary.delete(1));
    }

    @Test
    void testCreateAndFindEntryWhenUnlocked() {
        diary.unlock("pass123");
        diary.create("My Title", "My Body");

        Entry entry = diary.find(1);

        assertNotNull(entry);
        assertEquals(1, entry.getId());
        assertEquals("My Title", entry.getTitle());
        assertEquals("My Body", entry.getBody());
    }

    @Test
    void testFindEntryByIdNotFoundThrowsException() {
        diary.unlock("pass123");
        assertThrows(IllegalArgumentException.class, () -> diary.find(999));
    }

    @Test
    void testUpdateEntryWhenUnlocked() {
        diary.unlock("pass123");
        diary.create("Old Title", "Old Body");

        diary.update(1, "New Title", "New Body");

        Entry updatedEntry = diary.find(1);
        assertEquals("New Title", updatedEntry.getTitle());
        assertEquals("New Body", updatedEntry.getBody());
    }

    @Test
    void testUpdateNonExistentEntryThrowsException() {
        diary.unlock("pass123");
        assertThrows(IllegalArgumentException.class, () -> diary.update(999, "Title", "Body"));
    }

    @Test
    void testDeleteEntryWhenUnlocked() {
        diary.unlock("pass123");
        diary.create("Title to Delete", "Body");

        diary.delete(1);

        assertThrows(IllegalArgumentException.class, () -> diary.find(1));
    }

    @Test
    void testDeleteNonExistentEntryThrowsException() {
        diary.unlock("pass123");
        assertThrows(IllegalArgumentException.class, () -> diary.delete(999));
    }
}