package Task3_ATM_Interface;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATM {

    private ArrayList<BankAccount> accounts;
    private Scanner sc;
    private final String FILE_NAME = "src/Task3_ATM_Interface/accounts.txt";

    public ATM() {

        accounts = new ArrayList<>();
        sc = new Scanner(System.in);

        loadAccounts();
    }

    public void start() {

        int choice;

        do {

            System.out.println("\n===== ATM SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    saveAccounts();
                    System.out.println("Thank you for using our ATM!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 3);
    }

    private void createAccount() {

        sc.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        int pin;

        while (true) {

            System.out.print("Set a 4-digit PIN: ");
            pin = sc.nextInt();

            if (pin >= 1000 && pin <= 9999) {
                break;
            }

            System.out.println("PIN must be exactly 4 digits.");
        }

        System.out.print("Enter Opening Balance: ₹");
        double balance = sc.nextDouble();

        while (balance < 0) {

            System.out.print("Invalid amount. Enter again: ₹");
            balance = sc.nextDouble();
        }

        int accountNumber = generateUniqueAccountNumber();

        BankAccount account =
                new BankAccount(name,
                        accountNumber,
                        pin,
                        balance);

        accounts.add(account);

        saveAccounts();

        System.out.println("\nAccount Created Successfully!");
        System.out.println("Account Holder : " + name);
        System.out.println("Account Number : " + accountNumber);
    }

    private int generateUniqueAccountNumber() {

        Random random = new Random();

        while (true) {

            int number = 100000 + random.nextInt(900000);

            boolean exists = false;

            for (BankAccount account : accounts) {

                if (account.getAccountNumber() == number) {

                    exists = true;
                    break;
                }
            }

            if (!exists) {
                return number;
            }
        }
    }

    private void login() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        BankAccount currentAccount = null;

        for (BankAccount account : accounts) {

            if (account.getAccountNumber() == accNo
                    && account.validatePin(pin)) {

                currentAccount = account;
                break;
            }
        }

        if (currentAccount == null) {

            System.out.println("Invalid Account Number or PIN.");
            return;
        }

        System.out.println("\nWelcome, "
                + currentAccount.getAccountHolderName());

        bankingMenu(currentAccount);
    }

    private void bankingMenu(BankAccount account) {

        int choice;

        do {

            System.out.println("\n===== BANKING MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Logout");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("Current Balance: ₹"
                            + account.getBalance());
                    break;

                case 2:

                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = sc.nextDouble();

                    account.deposit(depositAmount);

                    saveAccounts();

                    break;

                case 3:

                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = sc.nextDouble();

                    account.withdraw(withdrawAmount);

                    saveAccounts();

                    break;

                case 4:

                    System.out.println("Logged out successfully.");
                    break;

                default:

                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);
    }

    private void saveAccounts() {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (BankAccount account : accounts) {

                writer.write(account.toFileString());
                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println("Error saving account data.");
        }
    }

    private void loadAccounts() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 4) {

                    String name = data[0];
                    int accountNumber = Integer.parseInt(data[1]);
                    int pin = Integer.parseInt(data[2]);
                    double balance = Double.parseDouble(data[3]);

                    accounts.add(new BankAccount(
                            name,
                            accountNumber,
                            pin,
                            balance
                    ));
                }
            }

        } catch (IOException e) {

            System.out.println("Error loading account data.");
        }
    }
}
