public class Account {
    private String userPin;
    private double accountBalance;
    private static String accountNumber;
    private String username;
    private Card card;

    public Account(Card userCard, String username) {
        
    }

    public double getBalance(String userPin) {
        validate(userPin);
        return accountBalance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getFullName() {
        return this.username;
    }

    public void deposit(double amount) {
        validate(amount);
        accountBalance += amount;
    }

    public double withdraw(double amount, String pin) {
        validate(pin);
        validate(amount);
        if (amount > accountBalance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        accountBalance -= amount;
        return amount;
    }

    public void validate(String pin) {
        if (!pin.equals(userPin)) throw new IllegalArgumentException("Invalid pin");
    }

    public void validate(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid amount. Must be greater than zero.");
    }

    public void validateAccountNumber(String accNum) {
        validateAccountNumberLength(accNum);
        if (!accNum.equals(this.accountNumber)) throw new IllegalArgumentException("Invalid account number");
    }

    private boolean checkUserHasPin() {
        return userPin != null;
    }

    public void setUserPin(String customerPin) {
        validateLength(customerPin);
        if (checkUserHasPin()) {
            throw new IllegalArgumentException("You already have a pin set");
        }
        this.userPin = customerPin;
    }

    private void validateLength(String pin) {
        if (pin == null || pin.length() != 4) {
            throw new IllegalArgumentException("Your PIN should be exactly 4 characters long");
        }
    }

    private void validateAccountNumberLength(String accNumber) {
        if (accNumber == null || accNumber.length() > 8) {
            throw new IllegalArgumentException("Your Account Number should be maximum 8 characters long");
        }
    }

    public void changePin(String oldPin, String newPin, String accNumber) {
        validateAccountNumber(accNumber);
        validate(oldPin);
        validateLength(newPin);
        if (oldPin.equals(newPin)) {
            throw new IllegalArgumentException("Invalid P.I.N: New PIN cannot match the old one.");
        }
        this.userPin = newPin;
    }
}
