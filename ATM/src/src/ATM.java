package src;

public class ATM extends Banks{
    private long cash;



    private void refill(long money) {
        this.cash += money;
    }

    private void transfer(long amount, Account sender, Account recipient) {
        Bank.validate(amount);
        recipient.deposit(sender.withdraw(amount));
    }

    public long withdraw(long amount, Account account) {
        Bank.validate(amount);
        return account.withdraw(amount);
    }

    public void deposit(long amount) {
        Bank.validate(amount);


    }
    //transfer
    //withdraw
    //deposit
    //view balance
//    public long wire(Card card, String pin, long amount) {
//        Account account = find(card);
//        card.validate(pin);              // must throw "Invalid card or PIN"
//        return account.withdraw(amount);
//    }
}