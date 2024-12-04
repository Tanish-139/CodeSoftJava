import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + ": $" + amount;
    }
}

class BankAccount {
    private double balance;
    private List<Transaction> transactions;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount));
            System.out.println("Your money has been successfully deposited.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            System.out.println("Please collect your money.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    public double checkBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nAutomated Teller Machine");
            System.out.println("Choose 1 for Withdraw");
            System.out.println("Choose 2 for Deposit");
            System.out.println("Choose 3 for Check Balance");
            System.out.println("Choose 4 for Transaction History");
            System.out.println("Choose 5 for EXIT");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    handleWithdraw();
                    break;

                case 2:
                    handleDeposit();
                    break;

                case 3:
                    System.out.printf("Current Balance: $%.2f\n", account.checkBalance());
                    break;

                case 4:
                    displayTransactionHistory();
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleWithdraw() {
        System.out.print("Enter money to be withdrawn: ");
        double withdrawAmount = scanner.nextDouble();
        account.withdraw(withdrawAmount);
    }

    private void handleDeposit() {
        System.out.print("Enter money to be deposited: ");
        double depositAmount = scanner.nextDouble();
        account.deposit(depositAmount);
    }

    private void displayTransactionHistory() {
        List<Transaction> transactions = account.getTransactionHistory();
        if (transactions.isEmpty()) {
            System.out.println("No transaction history available.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}

public class MainATM {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount(10000);
        ATM atm = new ATM(myAccount);
        
        if (authenticateUser()) { 
            atm.showMenu();
        } else {
            System.out.println("Authentication failed. Access denied.");
        }
    }

    private static boolean authenticateUser() {
        
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();
        
        return pin == 6969;
    }
}

