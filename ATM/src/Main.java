import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank scotiaBank = new Bank();
        Scanner keyboard = new Scanner(System.in);
        Luhn luhnCheck = new Luhn();

        scotiaBank.creatAccount("1111", "12345678", "Alice Smith");
        scotiaBank.creatAccount("2222", "87654321", "Bob Jones");

        scotiaBank.getAccount("12345678").deposit(1500.00);
        scotiaBank.getAccount("87654321").deposit(500.00);

        ATM atmTerminal = new ATM(scotiaBank, 5000.00);

        boolean keepAtmRunning = true;

        while (keepAtmRunning) {
            System.out.println("\n=============================================");
            System.out.println("          WELCOME TO SCOTIABANK ATM          ");
            System.out.println("=============================================");

            try {
                atmTerminal.validate();
            } catch (IllegalArgumentException e) {
                System.out.println("\n[ATM NOTICE] " + e.getMessage());
                System.out.println("Sorry! This machine is temporarily out of service.");
                break;
            }

            System.out.print("Please swipe or enter your 16-Digit CARD NUMBER: ");
            String inputCardNumber = keyboard.nextLine();

            try {
                if (!luhnCheck.isValid(inputCardNumber)) {
                    throw new IllegalArgumentException("Invalid card number format check. Card ejected.");
                }
                String cardType = luhnCheck.getCardType(inputCardNumber);
                System.out.println("[ATM STATUS] " + cardType + " detected. Chip read successful!");
            } catch (IllegalArgumentException e) {
                System.out.println("\n[TERMINAL REJECTION] " + e.getMessage());
                continue;
            }

            System.out.print("Please enter the ACCOUNT NUMBER linked to this card: ");
            String accountNumberInput = keyboard.nextLine();

            Account currentUser = null;
            try {
                currentUser = scotiaBank.getAccount(accountNumberInput);
                atmTerminal.login(currentUser);
            } catch (IllegalArgumentException e) {
                System.out.println("\n[BANK DATABASE ERROR] " + e.getMessage());
                System.out.println("Card returned. Session closed.");
                continue;
            }

            System.out.print("Please enter your 4-digit PIN: ");
            String pinInput = keyboard.nextLine();

            try {
                atmTerminal.viewBalance(currentUser, pinInput);
            } catch (IllegalArgumentException e) {
                System.out.println("\n[SECURITY FAILURE] " + e.getMessage());
                System.out.println("Incorrect PIN. Card returned.");
                continue;
            }

            boolean userIsLoggedIn = true;
            System.out.printf("%nHello, %s!%n", currentUser.getFullName());

            while (userIsLoggedIn) {
                System.out.println("\n--- WHAT WOULD YOU LIKE TO DO? ---");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw Cash");
                System.out.println("3. Deposit Money");
                System.out.println("4. Transfer Money to Someone Else");
                System.out.println("5. Exit & Get Card Back");
                System.out.print("Enter option (1-5): ");

                String userChoice = keyboard.nextLine();

                switch (userChoice) {
                    case "1":
                        double currentBalance = atmTerminal.viewBalance(currentUser, pinInput);
                        System.out.printf("%n[BALANCE] Your available balance is: $%,.2f%n", currentBalance);
                        break;

                    case "2":
                        System.out.print("Enter withdrawal amount: $");
                        try {
                            double amountToWithdraw = Double.parseDouble(keyboard.nextLine());
                            double cashGiven = atmTerminal.withdraw(amountToWithdraw, pinInput, accountNumberInput);
                            System.out.printf("%n[SUCCESS] Please take your cash: $%,.2f%n", cashGiven);
                        } catch (NumberFormatException e) {
                            System.out.println("\n[ERROR] Numbers only. Please do not type letters or symbols.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("\n[DECLINED] " + e.getMessage());
                        }
                        break;

                    case "3":
                        System.out.print("Enter envelope cash deposit amount: $");
                        try {
                            double amountToDeposit = Double.parseDouble(keyboard.nextLine());
                            atmTerminal.deposit(amountToDeposit, currentUser);
                            System.out.printf("%n[SUCCESS] Money successfully deposited!%n");
                        } catch (NumberFormatException e) {
                            System.out.println("\n[ERROR] Numbers only. Please do not type letters or symbols.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("\n[ERROR] " + e.getMessage());
                        }
                        break;

                    case "4":
                        System.out.print("Enter recipient's bank account number: ");
                        String targetAccount = keyboard.nextLine();
                        System.out.print("Enter transfer amount: $");
                        try {
                            double amountToSend = Double.parseDouble(keyboard.nextLine());
                            Account friendAccount = scotiaBank.getAccount(targetAccount);

                            atmTerminal.transfer(currentUser, friendAccount, pinInput, amountToSend);
                            System.out.println("\n[SUCCESS] Money successfully transferred!");
                        } catch (NumberFormatException e) {
                            System.out.println("\n[ERROR] Numbers only. Please do not type letters or symbols.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("\n[TRANSFER FAILED] " + e.getMessage());
                        }
                        break;

                    case "5":
                        System.out.println("\nLogging out of terminal session...");
                        System.out.println("Thank you for choosing Scotiabank. Goodbye!");
                        userIsLoggedIn = false;
                        break;

                    default:
                        System.out.println("\nInvalid selection. Please pick a number between 1 and 5.");
                }
            }
        }
        keyboard.close();
    }
}
