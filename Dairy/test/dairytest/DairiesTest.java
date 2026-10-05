package dairytest;
import dairy.Diaries;
import dairy.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiariesTest {

    private Diaries diaries;

    @BeforeEach
    void setUp() {
        diaries = new Diaries();
    }

    @Test
    void testAddDiary() {
        diaries.add("john_doe", "password123");
        Diary diary = diaries.find("john_doe");

        assertNotNull(diary);
    }

    @Test
    void testFindByUsernameSuccess() {
        diaries.add("jane_doe", "securePass");
        Diary foundDiary = diaries.find("jane_doe");

        assertNotNull(foundDiary);
    }

    @Test
    void testFindByUsernameNotFoundThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> diaries.find("non_existent_user"));
    }

    @Test
    void testDeleteDiarySuccess() {
        diaries.add("user_to_delete", "password123");

        diaries.delete("user_to_delete", "password123");

        assertThrows(IllegalArgumentException.class, () -> diaries.find("user_to_delete"));
    }

    @Test
    void testDeleteDiaryWithIncorrectPasswordThrowsException() {
        diaries.add("protected_user", "correctPassword");

        assertThrows(IllegalArgumentException.class, () -> diaries.delete("protected_user", "wrongPassword"));
        assertNotNull(diaries.find("protected_user"));
    }

    @Test
    void testDeleteNonExistentDiaryThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> diaries.delete("ghost_user", "password123"));
    }
}
