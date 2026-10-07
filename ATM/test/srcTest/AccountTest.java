package srcTest;

import org.junit.jupiter.api.Test;
import src.Account;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void constructor_validInputs_createsAccount() {
        Account account = new Account("12345678", "John Doe", 1000L);
        assertEquals("12345678", account.getNumber());
        assertEquals("John Doe", account.getName());
        assertEquals(1000L, account.getBalance());
    }

    @Test
    void constructor_invalidAccountNumber_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Account("1234", "John Doe", 1000L));
        assertThrows(IllegalArgumentException.class, () -> new Account("ABCDEFGH", "John Doe", 1000L));
        assertThrows(IllegalArgumentException.class, () -> new Account(null, "John Doe", 1000L));
    }

    @Test
    void deposit_validAmount_increasesBalance() {
        Account account = new Account("12345678", "John Doe", 1000L);
        account.deposit(500L);
        assertEquals(1500L, account.getBalance());
    }

    @Test
    void deposit_negativeOrZeroAmount_throwsException() {
        Account account = new Account("12345678", "John Doe", 1000L);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0L));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-200L));
    }

    @Test
    void withdraw_validAmount_decreasesBalance() {
        Account account = new Account("12345678", "John Doe", 1000L);
        account.withdraw(400L);
        assertEquals(600L, account.getBalance());
    }

    @Test
    void withdraw_insufficientFunds_throwsIllegalStateException() {
        Account account = new Account("12345678", "John Doe", 500L);
        assertThrows(IllegalStateException.class, () -> account.withdraw(600L));
    }
}