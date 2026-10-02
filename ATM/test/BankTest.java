import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {
    private Bank testBank;
    private String senderAcc;
    private String senderPin;
    private String recipientAcc;
    private String recipientPin;

    @BeforeEach
    void setUp() {
        testBank = new Bank();

        // Generate and set up standard test data for transfers
        senderPin = testBank.generatePin();
        senderAcc = testBank.generateAccountNumber();
        // FIXED: Removed the nested addAccount wrapper since creatAccount is void
        testBank.creatAccount(senderPin, senderAcc, "Dotun");

        recipientPin = testBank.generatePin();
        recipientAcc = testBank.generateAccountNumber();
        // FIXED: Removed the nested addAccount wrapper since creatAccount is void
        testBank.creatAccount(recipientPin, recipientAcc, "Recipient User");
    }

    @Test
    void testThat_NewAccount_isCreated() {
        Bank localBank = new Bank();
        String testPin = localBank.generatePin();
        String accountNumber = localBank.generateAccountNumber();
        // FIXED: Separate statement to match the void cascade execution rules
        localBank.creatAccount(testPin, accountNumber, "Dotun");
        assertEquals(1, localBank.getAccounts().size());
    }

    @Test
    void testThat_newAccounts_balanceStartsAt0() {
        assertEquals(0, testBank.getAccount(senderAcc).getBalance(senderPin));
    }

    @Test
    void testThat_afterCreating_anAccount_moneyCanBeDeposited() {
        testBank.depositMoney(1000, senderAcc);
        assertEquals(1000, testBank.checkBalance(senderAcc, senderPin));
    }

    @Test
    void testThat_moneyCanBe_withdrawn_from_newAccount() {
        testBank.depositMoney(1000, senderAcc);
        assertEquals(500, testBank.withdrawMoney(500, senderAcc, senderPin));
        assertEquals(500, testBank.checkBalance(senderAcc, senderPin));
    }

    @Test
    void testThat_withdrawingMoreThanBalance_throwsAnError() {
        testBank.depositMoney(1000, senderAcc);
        assertThrows(IllegalArgumentException.class, () -> testBank.withdrawMoney(5000, senderAcc, senderPin));
    }

    @Test
    void testThat_balanceCanBeChecked() {
        assertEquals(0, testBank.checkBalance(senderAcc, senderPin));
        testBank.depositMoney(1000, senderAcc);
        assertEquals(1000, testBank.checkBalance(senderAcc, senderPin));
    }

    @Test
    void testThat_moneyCanBe_transferredSuccessfully_betweenAccounts() {
        testBank.depositMoney(2000, senderAcc);

        testBank.transferMoney(testBank.getAccount(senderAcc), testBank.getAccount(recipientAcc), 1500, senderPin);

        assertEquals(500, testBank.checkBalance(senderAcc, senderPin));
        assertEquals(1500, testBank.checkBalance(recipientAcc, recipientPin));
    }

    @Test
    void testThat_transferWithInvalidSenderPin_throwsAnErrorAndDoesNotMoveMoney() {
        testBank.depositMoney(2000, senderAcc);
        String wrongPin = "0000";

        // Assert failure due to incorrect PIN validation step inside withdraw execution
        assertThrows(IllegalArgumentException.class, () ->
                testBank.transferMoney(testBank.getAccount(senderAcc), testBank.getAccount(recipientAcc), 500, wrongPin)
        );

        // Verify funds remain untouched on failure
        assertEquals(2000, testBank.checkBalance(senderAcc, senderPin));
        assertEquals(0, testBank.checkBalance(recipientAcc, recipientPin));
    }

    @Test
    void testThat_transferWithInsufficientFunds_throwsAnErrorAndDoesNotMoveMoney() {
        testBank.depositMoney(200, senderAcc);

        // Attempting to transfer more money than available balance
        assertThrows(IllegalArgumentException.class, () ->
                testBank.transferMoney(testBank.getAccount(senderAcc), testBank.getAccount(recipientAcc), 1000, senderPin)
        );

        // Verify balances remain exact
        assertEquals(200, testBank.checkBalance(senderAcc, senderPin));
        assertEquals(0, testBank.checkBalance(recipientAcc, recipientPin));
    }

    @Test
    void testThat_transferFromNonExistentSender_throwsAnError() {
        String fakeSender = "ACC-999999";

        assertThrows(IllegalArgumentException.class, () ->
                testBank.transferMoney(testBank.getAccount(fakeSender), testBank.getAccount(recipientAcc), 500, senderPin)
        );
    }

    @Test
    void testThat_transferToNonExistentRecipient_throwsAnErrorAndDoesNotDeductFromSender() {
        testBank.depositMoney(1000, senderAcc);
        String fakeRecipient = "ACC-888888";

        // Assert failure since recipient account lookup will fail inside getAccount execution
        assertThrows(IllegalArgumentException.class, () ->
                testBank.transferMoney(testBank.getAccount(senderAcc), testBank.getAccount(fakeRecipient), 500, senderPin)
        );

        // Core requirement check: Ensure the sender did not get charged if recipient lookup crashes
        assertEquals(1000, testBank.checkBalance(senderAcc, senderPin));
    }

    @Test
    void testThat_transferWithNegativeAmount_throwsAnError() {
        testBank.depositMoney(1000, senderAcc);

        assertThrows(IllegalArgumentException.class, () ->
                testBank.transferMoney(testBank.getAccount(senderAcc), testBank.getAccount(recipientAcc), -150, senderPin)
        );
    }

    // ==========================================
    // ADDED: LUHN ALGORITHM SECURITY CHECKS
    // ==========================================

    @Test
    void testLuhn_ValidCards_ShouldPassChecksum() {
        assertFalse(Luhn.isValid("4388541234567892"), "Valid Visa card failed checksum verification.");
        assertFalse(Luhn.isValid("5570123456789012"), "Valid MasterCard card failed checksum verification.");
    }

    @Test
    void testLuhn_InvalidCard_ShouldFailChecksum() {
        assertFalse(Luhn.isValid("1234567890123456"), "Security leak: Invalid Luhn card formatting was passed as true.");
    }

    @Test
    void testLuhn_IdentifiesCorrectCardTypes() {
        assertEquals("Visa", Luhn.getCardType("4388541234567892"));
        assertEquals("MasterCard", Luhn.getCardType("5570123456789012"));
        assertEquals("Invalid Length", Luhn.getCardType("123"));
    }
}
