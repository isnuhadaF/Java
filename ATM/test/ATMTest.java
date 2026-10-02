import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ATMTest {

    private Bank bank;
    private Account senderAccount;
    private Account recipientAccount;
    private ATM atm;
    private final double INITIAL_ATM_BALANCE = 5000.0;

    @BeforeEach
    void setUp() {
        bank = new Bank();

        senderAccount = new Account("1111", "12345678", "Alice Smith");
        recipientAccount = new Account("2222", "87654321", "Bob Jones");

        bank.addAccount(senderAccount);
        bank.addAccount(recipientAccount);


        atm = new ATM(bank, INITIAL_ATM_BALANCE);
    }

    @Test
    void login_WithValidAccount_ShouldSucceedSilently() {
        assertDoesNotThrow(() -> atm.login(senderAccount));
    }

    @Test
    void login_WithUnregisteredAccount_ShouldThrowException() {
        Account externalAccount = new Account("4444", "00000000", "Stranger");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                atm.login(externalAccount)
        );
        assertEquals("Recipient account not found.", exception.getMessage());
    }

    @Test
    void validate_WithZeroBalance_ShouldThrowException() {
        ATM emptyAtm = new ATM(bank, 0.0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, emptyAtm::validate);
        assertEquals("Temporarily unable to dispense cash.", exception.getMessage());
    }

    @Test
    void validate_WithPositiveBalance_ShouldNotThrowException() {
        assertDoesNotThrow(() -> atm.validate());
    }

    @Test
    void withdraw_WithValidDetails_ShouldDeductFromAtmAndAccountBalances() {
        senderAccount.deposit(1000.0);

        double dispensedAmount = atm.withdraw(200.0, "1111", "12345678");

        assertEquals(200.0, dispensedAmount);
        assertEquals(800.0, senderAccount.getBalance("1111"));
    }

    @Test
    void withdraw_WithInsufficientFunds_ShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                atm.withdraw(200.0, "1111", "12345678")
        );
        assertEquals("Insufficient funds", exception.getMessage());
    }

    @Test
    void withdraw_WithInvalidPin_ShouldThrowException() {
        senderAccount.deposit(500.0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                atm.withdraw(100.0, "9999", "12345678")
        );
        assertEquals("Invalid pin", exception.getMessage());
    }

    @Test
    void transfer_BetweenValidAccounts_ShouldShiftBalances() {
        senderAccount.deposit(600.0);

        atm.transfer(senderAccount, recipientAccount, "1111", 400.0);

        assertEquals(200.0, senderAccount.getBalance("1111"), "Sender balance should drop");
        assertEquals(400.0, recipientAccount.getBalance("2222"), "Recipient balance should rise");
    }

    @Test
    void deposit_ShouldUpdateAccountStateViaBankPipeline() {
        atm.deposit(350.0, senderAccount);

        assertEquals(350.0, senderAccount.getBalance("1111"));
    }

    @Test
    void viewBalance_WithCorrectPin_ShouldReturnExactBalance() {
        senderAccount.deposit(850.50);

        double balanceChecked = atm.viewBalance(senderAccount, "1111");

        assertEquals(850.50, balanceChecked);
    }
}
