import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
    private Account testAccount;
    private final String initialPin = "1234";
    private final String initialAccNum = "12345678"; // 8 digits max as per validation
    private final String initialName = "Dotun";

    @BeforeEach
    void setUp() {
        testAccount = new Account(initialPin, initialAccNum, initialName);
    }

    @Test
    void testThat_AccountInitializes_WithCorrectValues() {
        assertEquals(initialAccNum, testAccount.getAccountNumber());
        assertEquals(initialName, testAccount.getFullName());
        assertEquals(0.0, testAccount.getBalance(initialPin));
    }

    @Test
    void testThat_ConstructorThrowsError_IfPinLength_IsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account("123", initialAccNum, initialName)
        );
        assertThrows(IllegalArgumentException.class, () ->
                new Account("12345", initialAccNum, initialName)
        );
    }

    @Test
    void testThat_ConstructorThrowsError_IfAccountNumberLength_IsGreaterThanEight() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(initialPin, "123456789", initialName)
        );
    }

    @Test
    void testThat_CheckingBalanceWithWrongPin_ThrowsAnError() {
        assertThrows(IllegalArgumentException.class, () ->
                testAccount.getBalance("0000")
        );
    }

    @Test
    void testThat_MoneyCanBe_DepositedSuccessfully() {
        testAccount.deposit(500.0);
        assertEquals(500.0, testAccount.getBalance(initialPin));
    }

    @Test
    void testThat_DepositingNegativeAmount_ThrowsAnError() {
        assertThrows(IllegalArgumentException.class, () ->
                testAccount.deposit(-100.0)
        );
    }

    @Test
    void testThat_MoneyCanBe_WithdrawnSuccessfully() {
        testAccount.deposit(1000.0);
        int withdrawnAmount = testAccount.withdraw(400.0, initialPin);

        assertEquals(400, withdrawnAmount);
        assertEquals(600.0, testAccount.getBalance(initialPin));
    }

    @Test
    void testThat_WithdrawingWithWrongPin_ThrowsAnError() {
        testAccount.deposit(500.0);
        assertThrows(IllegalArgumentException.class, () ->
                testAccount.withdraw(200.0, "0000")
        );
    }

    @Test
    void testThat_WithdrawingNegativeAmount_ThrowsAnError() {
        testAccount.deposit(500.0);
        assertThrows(IllegalArgumentException.class, () ->
                testAccount.withdraw(-50.0, initialPin)
        );
    }

    @Test
    void testThat_WithdrawingMoreThanBalance_ThrowsAnError() {
        testAccount.deposit(200.0);
        assertThrows(IllegalArgumentException.class, () ->
                testAccount.withdraw(300.0, initialPin)
        );
    }

    @Test
    void testThat_AccountNumberValidation_ThrowsErrorOnMismatch() {
        assertThrows(IllegalArgumentException.class, () ->
                testAccount.val1date("99999999")
        );
    }

    @Test
    void testThat_SettingUserPin_ThrowsError_IfPinAlreadyExists() {
        // The constructor already assigns 'initialPin', so userPin is not null
        assertThrows(IllegalArgumentException.class, () ->
                testAccount.setUserPin("5678")
        );
    }

    @Test
    void testThat_PinCanBeChanged_Successfully() {
        String newPin = "5678";
        testAccount.changePin(initialPin, newPin, initialAccNum);

        // Old PIN should now fail, and new PIN should work
        assertThrows(IllegalArgumentException.class, () -> testAccount.getBalance(initialPin));
        assertEquals(0.0, testAccount.getBalance(newPin));
    }

    @Test
    void testThat_ChangingPinWithSameOldPin_ThrowsAnError() {
        assertThrows(IllegalArgumentException.class, () ->
                testAccount.changePin(initialPin, initialPin, initialAccNum)
        );
    }

    @Test
    void testThat_ChangingPinWithInvalidNewPinLength_ThrowsAnError() {
        assertThrows(IllegalArgumentException.class, () ->
                testAccount.changePin(initialPin, "12345", initialAccNum)
        );
    }
}
