import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    private  String userPin;
    private int userAccountBalance;
    private String userAccountName;
    private String userAccountNumber;
    private final ArrayList<String> userTransactions = new ArrayList<>();



    public Account(String customerPin) {
       if (login(customerPin)) {
           int accountBalance = this.userAccountBalance;
           final String accountName = this.userAccountName;
           final String accountNumber = this.userAccountNumber;
       }

    }

    public void setUserPin(String customerPin) {
        this.userPin = customerPin;
    }

    public boolean login(String customerPin) {
        return Objects.equals(customerPin, this.userPin);
    }


    public void depositMoney(int amount) {
        try {
            if (amount > 0) {
                this.userAccountBalance += amount;

            }
        } catch (Exception error) {
            throw new RuntimeException("You cannot deposit a negative amount");
        }
    }

    public int withdrawMoney(int amount) {
        try {
            if (amount < this.userAccountBalance) {
                this.userAccountBalance -= amount;
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
