

public class Account extends Bank {
    private  String userPin;
    private double userAccountBalance;
    private final int accountNumber;



    public Account(String defaultPin, int accNum) {
        if (defaultPin.length() != 4) {
           throw new IllegalArgumentException("Your PIN should be 4 characters long");
       } this.userPin = defaultPin; this.accountNumber = accNum;
    }

    public double getBalance(String userPin) {
        validate(userPin);
        return userAccountBalance;
    }

    public void deposit(double amount) {
        validate(amount);
        userAccountBalance += amount;
    }

    public int withdraw(double amount, String pin) {
        validate(pin);
        validate(amount);
        boolean isTransactionValid = amount <= userAccountBalance;
        if (isTransactionValid) userAccountBalance -= amount; return (int) amount;
    }

    private void validate(String pin) {
        if (!pin.equals(userPin)) throw new IllegalArgumentException("Invalid pin");
    }

    private void validate(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Invalid amount");
    }

    public void validate(int accNum) {
        if (accNum != this.accountNumber) throw new IllegalArgumentException("Invalid account number");
    }

    private boolean checkUserHasPin() {
        if (userPin != null) {throw new IllegalArgumentException("A pin already exists for this account");}
        else return false;
    }

    public void setUserPin(String customerPin) {
        if (checkUserHasPin()) {throw new IllegalArgumentException("You already have a pin set");}
        else this.userPin = customerPin;
    }




}
