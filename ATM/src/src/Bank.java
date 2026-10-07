package src;
import java.security.SecureRandom;
import java.util.*;
import utility.Card;
import utility.Luhn;

public class Bank {
    private List<Account> accounts;
    private final String code;
    private final String serial;

    public Bank(String bankCode, String bankSerial) {
        this.code = bankCode;
        this.serial = bankSerial;
        accounts = new ArrayList<>();
    }
    //Open a new src.Account with the name and amount to be deposited
    public void openAccount(String name, long amount) {
        validate(amount);
        accounts.add(new Account(generate(new SecureRandom()), name, amount));
    }

    public static void validate(long amount) {
        if (amount < 0) throw new IllegalArgumentException("Invalid amount");
    }

    //generate account number
    private String generate(SecureRandom random) {
        random = new SecureRandom();
        StringBuilder accNum = new StringBuilder();
        for (int number = 1; number < 9; number++) {
            accNum.append(random.nextInt(10));
        }

        return accNum.toString();
    }

    //generate a card number
    private String generate() {
        SecureRandom random = new SecureRandom();
        StringBuilder number = new StringBuilder();
        for (int index = 0; index < 16; index++) {
            number.append(random.nextInt(10));
        }

        return number.toString();
    }

    public String getCode() {
        return this.code;
    }

    public String getSerial() {
        return this.serial;
    }

    //Generate CVV
    private int generateCvv() {
        SecureRandom random = new SecureRandom();

        return 100 + random.nextInt(900);
    }

    //Generate expiry
    private String generateEx() {
        SecureRandom random = new SecureRandom();
        StringBuilder expiry = new StringBuilder();
        expiry.append(String.format("%02d", random.nextInt(1, 13)));
        expiry.append("/");
        expiry.append("2031");

        return expiry.toString();
    }

    //Generate a pin for the card
    private String create() {
        SecureRandom random = new SecureRandom();
        StringBuilder pin = new StringBuilder();
        pin.append(String.format("%04d", random.nextInt(10000)));

        return pin.toString();
    }

    //Creates a new card object with the generated info
    public Card build() {
        String cardNumber = generate();
        while (!Luhn.isValid(cardNumber)) {
            cardNumber = generate();
        }
        int cvv = generateCvv();
        String expiry = generateEx();
        String pin = create();

        return new Card(cardNumber, expiry, cvv, pin);
    }

    public Account find(String accNum) {
        for (Account account : accounts) {
            if (accNum.equals(account.getNumber())) return account;
            else throw new IllegalArgumentException("No account exists under that number");
        }
        return null;
    }
}