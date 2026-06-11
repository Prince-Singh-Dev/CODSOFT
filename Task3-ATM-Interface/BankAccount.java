package Task3_ATM_Interface;

public class BankAccount {

    private String accountHolderName;
    private int accountNumber;
    private int pin;
    private double balance;

    public BankAccount(String accountHolderName,
                       int accountNumber,
                       int pin,
                       double balance) {

        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public boolean validatePin(int enteredPin) {
        return pin == enteredPin;
    }

    public void deposit(double amount) {

        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;

            System.out.println("₹" + amount + " withdrawn successfully.");
            System.out.println("Please collect your cash.");
        }
    }

    public String toFileString() {

        return accountHolderName + ","
                + accountNumber + ","
                + pin + ","
                + balance;
    }
}
