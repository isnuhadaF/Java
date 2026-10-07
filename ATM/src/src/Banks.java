package src;
import src.Bank;

import java.util.ArrayList;
import java.util.List;


public class Banks {
    private List<Bank> banks = new ArrayList<>(10);

    public Bank getBank(Bank bank) {
        if (banks.contains(bank)) return bank;
        return null;
    }
}
