import java.util.*;
import java.io.*;

public class BankingSystem {
    static List<BankAccount> accounts = new ArrayList<>();

    public static void loadAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                accounts.add(BankAccount.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("No previous accounts found.");
        }
    }

    public static void saveAccounts() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("accounts.txt"))) {
            for (BankAccount acc : accounts) {
                pw.println(acc.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadAccounts();

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show All Accounts");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter initial balance: ");
                    double bal = sc.nextDouble(); sc.nextLine();
                    accounts.add(new BankAccount(name, bal));
                    saveAccounts();
                    System.out.println("Account created.");
                }
                case 2 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    boolean found = false;
                    for (BankAccount acc : accounts) {
                        if (acc.getName().equalsIgnoreCase(name)) {
                            System.out.print("Enter amount: ");
                            acc.deposit(sc.nextDouble()); sc.nextLine();
                            saveAccounts();
                            System.out.println("Deposited.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Account not found.");
                }
                case 3 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    boolean found = false;
                    for (BankAccount acc : accounts) {
                        if (acc.getName().equalsIgnoreCase(name)) {
                            System.out.print("Enter amount: ");
                            acc.withdraw(sc.nextDouble()); sc.nextLine();
                            saveAccounts();
                            System.out.println("Withdrawn.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Account not found.");
                }
                case 4 -> {
                    for (BankAccount acc : accounts) {
                        System.out.println("Name: " + acc.getName() + ", Balance: " + acc.getBalance());
                    }
                }
                case 5 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}