import java.util.ArrayList;
import java.util.List;

public class Passenger extends User {
    private double balance;          
    private List<Transaction> history; 

    // Constructor inisialisasi Passenger
    public Passenger(String username, String password) {
        super(username, password, "USER"); // Panggil constructor parent
        this.balance = 0.0;              
        this.history = new ArrayList<>(); 
    }

    // Implementasi menu khusus Passenger (Polymorphism)
    @Override
    public void showMenu() {
        System.out.println("\n===== MENU USER (" + username + ") =====");
        System.out.println("Saldo: Rp " + balance);     
        System.out.println("1. Lihat Rute KRL");         
        System.out.println("2. Naik KRL (Simulasi)");    
        System.out.println("3. Cek Saldo");         
        System.out.println("4. Top Up Saldo");            
        System.out.println("5. Riwayat Perjalanan");       
        System.out.println("0. Logout");                  
    }

    // Top up saldo
    public void topUp(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    // Kurangi saldo saat bayar tiket
    public boolean deductBalance(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() { return balance; } // Getter saldo

    // Tambah transaksi ke riwayat
    public void addTransaction(Transaction trx) {
        this.history.add(trx);
    }

    // Lihat riwayat perjalanan
    public void viewHistory() {
        System.out.println("\n=== RIWAYAT PERJALANAN ===");
        if (history.isEmpty()) {
            System.out.println("Belum ada perjalanan.");
        } else {
            for (Transaction t : history) {
                System.out.println(t.getDetails()); // Tampilkan detail tiap transaksi
            }
        }
    }
}
