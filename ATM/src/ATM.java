public class ATM {
    private Bank bank;
    private double atmBalance;
    private Card insertedCard; // Active hardware slot tracker

    public ATM(Bank scotiaBank, double cash) {
        this.bank = scotiaBank;
        this.atmBalance = cash;
    }

    public void login(Card card) {
        bank.authenticateCardDetails(card);
        this.insertedCard = card;
    }

    public void logout() {
        this.insertedCard = null;
    }

    public double withdraw(Double amount, String pin) {
        // IDOR Check 1: Ensure a card session is actually sitting in the hardware slot
        if (insertedCard == null) {
            throw new IllegalStateException("Transaction Denied: No card session detected.");
        }

        // IDOR Check 2: Pass the pin down to authorize the direct object reference resolution
        Account account = insertedCard.getAuthorizedAccount(pin);

        account.validate(amount);

        if (amount > this.atmBalance) {
            throw new IllegalArgumentException("ATM has insufficient physical cash.");
        }

        this.atmBalance -= amount;
        return account.withdraw(amount, pin);
    }

    public double viewBalance(String pin) {
        if (insertedCard == null) {
            throw new IllegalStateException("No active card session.");
        }

        // Enforces access control right at the fetch step
        return insertedCard.getAuthorizedAccount(pin).getBalance(pin);
    }
}
