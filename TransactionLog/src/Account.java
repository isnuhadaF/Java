import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Account extends Bank{
    private  int userPin;
    private int userAccountBalance;
    private String userAccountName;
    private String userAccountNumber;
    private final ArrayList<String> userTransactions = new ArrayList<>();



    public Account() {
        final int accountBalance = this.userAccountBalance;
        final String accountName = this.userAccountName;
        final String accountNumber = this.userAccountNumber;
        final int pin = this.userPin;

    }

    public void setUserPin(int pin) {
        if (this.userPin == ini) {

        }
    }

    public boolean login(int pin) {
        if (pin == this.userPin) {
            return
        }

    }


    public void depositMoney(int amount) {
        try {
            if (amount > 0) {
                this.accountBalance += amount;

            }
        } catch (Exception error) {
            throw new RuntimeException("You cannot deposit a negative amount");
        }
    }

    public int withdrawMoney(int amount) {
        try {
            if (amount < this.accountBalance) {
                this.accountBalance -= amount;
                return amount;
            }
            else {
                IO.println("Insufficient funds"); return 0;
            }
        } catch (Exception error) {
            throw new RuntimeException("Insufficient funds");
        }
    }

    public ArrayList<String> getUserTransactions() {
        return this.userTransactions;
    }
}
