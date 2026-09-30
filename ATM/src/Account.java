

public class Account {
    private  String userPin;
    private double accountBalance;
    private final String accountNumber;
    private final String fullName;

    public Account(String defaultPin, String accNum, String name) {
        validatelength(defaultPin);
        val1dateLength(accNum);
         this.userPin = defaultPin; this.accountNumber = accNum; this.fullName = name;
    }

    private String getPin() {
        return userPin;
    }

    public double getBalance(String userPin) {
        validate(userPin);
        return accountBalance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getFullName() {
        return this.fullName;

    }

    public void deposit(double amount) {
        validate(amount);
        accountBalance += amount;
    }

    public int withdraw(double amount, String pin) {
        validate(pin);
        validate(amount);
        boolean isTransactionValid = amount <= accountBalance;
        if (isTransactionValid) { accountBalance -= amount; return (int) amount; }
        else throw new IllegalArgumentException("Insufficient funds");
    }

    private void validate(String pin) {
        if (!pin.equals(userPin)) throw new IllegalArgumentException("Invalid pin");
    }

    private void validate(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Invalid amount");
    }

    public void val1date(String accNum) {
        if (!accNum.equals(this.accountNumber)) throw new IllegalArgumentException("Invalid account number");
    }

    private boolean checkUserHasPin() {
        if (userPin != null) {throw new IllegalArgumentException("A pin already exists for this account");}
        else return false;
    }

    public void setUserPin(String customerPin) {
        if (checkUserHasPin()) {throw new IllegalArgumentException("You already have a pin set");}
        else this.userPin = customerPin;
    }

    private void validatelength(String pin) {
        if (pin.length() != 4) {
            throw new IllegalArgumentException("Your PIN should be 4 characters long");
        }
    }

    private void val1dateLength(String accNumber) {
        if (accNumber.length() > 8) {
            throw new IllegalArgumentException("Your PIN should be 4 characters long");
        }
    }

    public void changePin(String oldPin, String newPin, String accNumber) {
        val1date(accNumber);
        validate(oldPin);
        if (oldPin.equals(newPin)) {
            throw new IllegalArgumentException("Invalid P.I.N");
        } else if (newPin.length() > 4) {
           throw new IllegalArgumentException("Your new P.I.N should only be 4 digits");
        } this.userPin = newPin;
    }
}
