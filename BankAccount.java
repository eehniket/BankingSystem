public class BankAccount {
    private String name;
    private double balance;

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }

    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) { balance -= amount; }

    public String toFileString() {
        return name + "," + balance;
    }

    public static BankAccount fromFileString(String line) {
        String[] parts = line.split(",");
        return new BankAccount(parts[0], Double.parseDouble(parts[1]));
    }
}