public class ATM {
    private Bank bank;
    private double atmBalance;
    private Card insertedCard;

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
        if (insertedCard == null) {
            throw new IllegalStateException("Transaction Denied: No card session detected.");
        }

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

        return insertedCard.getAuthorizedAccount(pin).getBalance(pin);
    }
}
