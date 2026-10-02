import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {
    private static List<Account> accounts = new ArrayList<>();

    private void record(Account account) {
        accounts.add(account);
    }

    public void creatAccount(String fullName) {
        Random random = new Random();
        String accountNumber = generate(fullName);

        String generatedCardNumber = generate(random);
        String generatedExpiry = "12/31";
        int generatedCvv = 100 + random.nextInt(900);
        String generatedPin = generate();

        Card newCard = new Card(generatedCardNumber, generatedExpiry, generatedCvv);
        Account newAccount = new Account(newCard, generatedPin);

        record(newAccount);
        newCard.link(newAccount, this);

        System.out.println("\n=============================================");
        System.out.println("      NEW USER ACCOUNT ONBOARDED SUCCESSFULLY! ");
        System.out.println("=============================================");
        System.out.println(" CUSTOMER NAME    : " + fullName);
        System.out.println(" ACCOUNT ID NO.   : " + accountNumber);
        System.out.println(" DYNAMIC CARD NO. : " + generatedCardNumber);
        System.out.println(" CARD TYPE LAYOUT : " + newCard.getCardType());
        System.out.println(" SAFETY CVV MATRIX: " + generatedCvv);
        System.out.println(" EXPIRY DATE CODE : " + generatedExpiry);
        System.out.println(" SECURITY PIN FLAG: " + generatedPin);
        System.out.println("=============================================");
    }
    public void depositMoney(double amount, Card card) {
        // Blocks an attacker from passing an arbitrary account ID string
        Account account = getAccountByCardNumber(card.getCardNumber());
        account.deposit(amount);
    }
    public double withdrawMoney(double amount, Card card, String pin) {
        // Crucial Fix: Card will refuse to return the account reference unless PIN matches
        Account account = card.getAuthorizedAccount(pin);
        return account.withdraw(amount, pin);
    }
    public double checkBalance(Card activeCard, String pin) {
        Account account = activeCard.getAuthorizedAccount(pin);
        return account.getBalance(pin);
    }
    public void transferMoney(Account sender, Account recipient, double amount, String senderPin) {
        if (validate(recipient)) {
            recipient.deposit(sender.withdraw(amount, senderPin));
        }
    }
    public Account getAccount(String cardNum) {
        for (Account account : accounts) {
            if (account.getAssociatedCard().getCardNumber().equals(cardNum)) {
                return account;
            }
        }
        throw new IllegalArgumentException("Security Error: Card registry not recognized.");
    }


    private String generate(String fullName) {
        Random random = new Random();
        String accountNumber = "";

        while (true) {
            StringBuilder numberBuilder = new StringBuilder();
            for (int digit = 1; digit <= 8; digit++) {
                numberBuilder.append(random.nextInt(10));
            }
            accountNumber = numberBuilder.toString();

            boolean isDuplicate = false;
            for (Account account : accounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                break;
            }
        }
        return accountNumber;
    }

    private String generate(Random random) {
        while (true) {
            StringBuilder cardBuilder = new StringBuilder();
            cardBuilder.append("4");

            for (int i = 0; i < 14; i++) {
                cardBuilder.append(random.nextInt(10));
            }

            cardBuilder.append("0");
            String baseNumber = cardBuilder.toString();

            int sumOfDoubled = 0;
            int sumOfOdd = 0;

            for (int i = 14; i >= 0; i--) {
                int digit = baseNumber.charAt(i) - '0';
                int positionFromRight = 16 - i;

                if (positionFromRight % 2 == 0) {
                    int doubled = digit * 2;
                    if (doubled > 9) {
                        doubled = doubled - 9;
                    }
                    sumOfDoubled += doubled;
                } else {
                    sumOfOdd += digit;
                }
            }

            int total = sumOfDoubled + sumOfOdd;
            int checkDigit = (10 - (total % 10)) % 10;
            String finalCardString = baseNumber.substring(0, 15) + checkDigit;

            if (Luhn.isValid(finalCardString)) {
                return finalCardString;
            }
        }
    }

    public String generate() {
        Random random = new Random();
        StringBuilder generatedPin = new StringBuilder();
        for (int digit = 1; digit <= 4; digit++) {
            generatedPin.append(random.nextInt(10));
        }
        return generatedPin.toString();
    }

    public void set(Random expiry) {
        



    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public boolean validate(Account account) {
        if (accounts.contains(account)) return true;
        else throw new IllegalArgumentException("Recipient account not found.");
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
