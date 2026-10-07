package utility;

import src.Account;

public class Card extends Account {
    private final String cardNumber;
    private final String expiry;
    private final int cvv;
    private final String pin;

    public Card(String cardNumber, String expiry, int cvv, String pin) {
        super(pin);
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
        this.pin = pin;
    }

    public void validate(String pin) {
        if (pin.length() != 4) throw new IllegalArgumentException("Invalid pin");
    }
    public void confirm(String pin) {
        if (!pin.equals(this.pin)) throw new IllegalArgumentException("Incorrect pin");
    }
    public String getCardNumber() { return cardNumber; }
    public String getExpiry() { return expiry; }
    public int getCvv() { return cvv; }
}