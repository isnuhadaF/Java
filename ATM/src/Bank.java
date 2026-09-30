import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {
    private List<Account> accounts = new ArrayList<>();

    public Account creatAccount(String pin, String accountNumber, String fullName) {
        return new Account(pin, accountNumber, fullName);
    }

    public void depositMoney(double amount, String accountNumber) {
        Account account = getAccount(accountNumber);
        account.val1date(accountNumber);
        account.deposit(amount);
    }

    public double withdrawMoney(double amount, String accountNumber, String pin) {
        Account account = getAccount(accountNumber);
        account.val1date(accountNumber);
        return account.withdraw(amount, pin);
    }

    public void transferMoney(Account sender, Account recipient, double amount, String senderPin) {
       if (validate(recipient)) recipient.deposit(sender.withdraw(amount, senderPin));

    }

    public double checkBalance(String accNumber, String pin) {
        Account account = getAccount(accNumber);
        account.val1date(accNumber);

        return account.getBalance(pin);
    }

    public String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int digit = 1; digit <= 8; digit++) {
            accountNumber.append(random.nextInt(10));
        }
        IO.println("Your account number: " + accountNumber);
        return accountNumber.toString();
    }

    public String generatePin() {
        Random random = new Random();
        StringBuilder defaultPin = new StringBuilder();
        for (int digit = 1; digit <= 4; digit++) defaultPin.append(random.nextInt(10));
        IO.println("Your default PIN: " + defaultPin);
        return defaultPin.toString();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) { return account; }
        } throw new IllegalArgumentException("Account not found.");
    }

    private boolean validate(Account account) {
        if (accounts.contains(account)) return true;
        else throw new IllegalArgumentException("Recipient account not found.");
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
