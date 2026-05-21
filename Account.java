public class Account {

    private String accountId;
    private double balance;
    private String accountType;

    public Account(String accountId, double initialBalance, String accountType) {
        this.accountId = accountId;
        this.balance = initialBalance;
        this.accountType = accountType;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            return false; // insufficient funds
        }
        balance -= amount;
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public boolean transfer(double amount, Account target) {
        if (this.withdraw(amount)) {
            target.deposit(amount);
            return true;
        }
        return false;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public static void main(String[] args) {
        Account checking = new Account("ACC-001", 500.0, "Checking");
        Account savings = new Account("ACC-002", 1000.0, "Savings");

        System.out.println("Initial balances:");
        System.out.println("  " + checking.getAccountId() + " (" + checking.getAccountType()
                + "): $" + checking.getBalance());
        System.out.println("  " + savings.getAccountId() + " (" + savings.getAccountType()
                + "): $" + savings.getBalance());

        checking.deposit(250.0);
        System.out.println("\nAfter depositing $250 into " + checking.getAccountId()
                + ": $" + checking.getBalance());

        boolean withdrawn = savings.withdraw(300.0);
        System.out.println("Withdraw $300 from " + savings.getAccountId()
                + ": " + (withdrawn ? "success" : "insufficient funds")
                + ", balance: $" + savings.getBalance());

        boolean transferred = checking.transfer(400.0, savings);
        System.out.println("\nTransfer $400 from " + checking.getAccountId()
                + " to " + savings.getAccountId() + ": "
                + (transferred ? "success" : "failed"));

        System.out.println("\nFinal balances:");
        System.out.println("  " + checking.getAccountId() + ": $" + checking.getBalance());
        System.out.println("  " + savings.getAccountId() + ": $" + savings.getBalance());
    }
}
