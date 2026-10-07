package src;

public class Account {
    private final String number;
    private final String name;
    private long balance;

    public Account(String number, String name, long balance) {
        if (number == null || number.length() > 8) throw new IllegalArgumentException("Account number must be exactly 8 digits.");
        if (name.isEmpty())  throw new IllegalArgumentException("Account holder name cannot be blank.");
        if (balance < 0) throw new IllegalArgumentException("Initial balance cannot be negative.");

        this.number = number;
        this.name = name;
        this.balance = balance;
    }

    public void deposit(long amount) {
        validate(amount);
        this.balance += amount;
    }

    public long withdraw(long amount) {
        validate(amount);
        if (amount > balance) throw new IllegalStateException("Insufficient funds.");
        this.balance -= amount;

        return amount;
    }

    public long getBalance() {
        return balance;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    private void validate(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero.");
        }
    }
}