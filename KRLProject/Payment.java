public class Payment {
    private double balance;
    public Payment(double initial) {
        this.balance = Math.max(0, initial);
    }
    public double getBalance() { return balance; }
    public boolean pay(double amount) {
        if (amount <= 0) return false;
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    public void topUp(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Nominal top-up harus > 0");
        balance += amount;
    }
}
