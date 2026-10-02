public class Card {
    private Account account; // Keep strictly private to prevent direct object manipulation
    private final String cardNumber;
    private final String expiration;
    private final int cvv;
    private final String cardType;

    public Card(String cardNumber, String expiration, int cvv) {
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.cvv = cvv;
        this.cardType = Luhn.getCardType(cardNumber);
        verify(cardNumber);
    }

    private void verify(String cardNumber) {
        if (!Luhn.isValid(cardNumber)) {
            throw new IllegalArgumentException("Invalid card number.");
        }
    }

    public void link(Account account, Bank bank) {
        if (verify(account, bank)) {
            this.account = account;
        }
    }

    private boolean verify(Account userAccount, Bank bank) {
        if (!bank.getAccounts().contains(userAccount)) {
            throw new IllegalArgumentException("No account associated with this card");
        }
        return true;
    }

    // IDOR Protection Gateway:
    // Forces the caller to prove ownership by providing the PIN before granting access to the Account object
    public Account getAuthorizedAccount(String pin) {
        if (this.account == null) {
            throw new IllegalStateException("Card is not linked to an active account.");
        }

        // This challenge line blocks authorization if the PIN is incorrect
        this.account.validate(pin);

        return this.account; // Only returned if validate() passes without throwing an error
    }

    public String getCardNumber() { return cardNumber; }
    public String getExpiration() { return expiration; }
    public int getCvv() { return cvv; }
    public String getCardType() { return cardType; }
}
