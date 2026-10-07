package srcTest;

import static org.junit.jupiter.api.Assertions.*;

import utility.Card;
import utility.Luhn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Bank;

public class BankTest {
    private Bank testBank;
    private String ownerName;
    private long openingAmount;

    @BeforeEach
    void setUp() {
        testBank = new Bank("123", "123456789");
        ownerName = "Dotun";
        openingAmount = 5000;
    }

    // ==========================================
    // OPENING ACCOUNTS
    // ==========================================
    @Test
    void testBuildReturnsValidCardNumber() {
        // Act - Call the public build method
        Bank bank = new Bank("123", "123456789");
        Card generatedCard = bank.build();

        // Print to console to visually check (as originally requested)
        System.out.println("--- Generated Card Credentials ---");
        System.out.println("Card Number: " + generatedCard.getCardNumber());
        System.out.println("Expiry Date: " + generatedCard.getExpiry());
        System.out.println("CVV:         " + generatedCard.getCvv());
        //System.out.println("PIN:         " + generatedCard.getPin());

        // Assert - Verify the card properties
        assertNotNull(generatedCard, "The generated card should not be null.");
        assertNotNull(generatedCard.getCardNumber(), "Card number should not be null.");

        // This line checks if the loop in build() successfully caught a valid number
        assertTrue(Luhn.isValid(generatedCard.getCardNumber()),
                "The generated card number must satisfy the Luhn algorithm check.");
    }

    @Test
    void testThat_openingAnAccount_returnsACard() {
    }

    @Test
    void testThat_twoAccounts_getDifferentCards() {
    }

    @Test
    void testThat_manyAccounts_neverShareACardNumber() {
    }

    @Test
    void testThat_openingWithNegativeAmount_throwsAnError() {
    }

    // ==========================================
    // CARD DETAILS (checked on many cards, because the values are random)
    // ==========================================

    @Test
    void testThat_issuedCardNumbers_areSixteenDigits() {
    }

    @Test
    void testThat_issuedCardNumbers_passTheLuhnCheck() {
    }

    @Test
    void testThat_issuedCvv_isAlwaysThreeDigits() {
    }

    @Test
    void testThat_issuedExpiry_hasTwoDigitMonth_andYearThreeYearsAhead() {
    }

    // ==========================================
    // CARD STOCK
    // ==========================================

    @Test
    void testThat_bankCanOpen2000Accounts_fromItsInitialStock() {
    }

    // ----- RED: fails until you add `if (cards.isEmpty()) stock();` to openAccount -----
    @Test
    void testThat_whenStockRunsOut_bankRestocksInsteadOfCrashing() {
    }

    // ==========================================
    // RED: needs `public src.Account getAccount(Utility.Card card) { return accounts.get(card); }`
    // ==========================================

    @Test
    void testThat_openedAccount_canBeFoundWithItsCard() {
    }

    @Test
    void testThat_openedAccount_hasAnEightDigitAccountNumber() {
    }

    @Test
    void testThat_differentCards_openDifferentAccounts() {
    }

    // ==========================================
    // LUHN ALGORITHM CHECKS (fixed, see note below)
    // ==========================================

    @Test
    void testLuhn_ValidCards_ShouldPassChecksum() {
        assertTrue(Luhn.isValid("4111111111111111"), "Valid Visa test card failed checksum.");
        assertTrue(Luhn.isValid("5500000000000004"), "Valid MasterCard test card failed checksum.");
    }

    @Test
    void testLuhn_InvalidCard_ShouldFailChecksum() {
        assertFalse(Luhn.isValid("1234567890123456"), "Invalid card number was passed as valid.");
    }
}