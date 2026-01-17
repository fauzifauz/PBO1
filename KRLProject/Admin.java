import java.util.Scanner;

public class Admin extends User {

    // Constructor untuk inisialisasi Admin
    public Admin(String username, String password) {
        super(username, password, "ADMIN"); // Panggil constructor parent
    }

    // Implementasi menu khusus Admin (Polymorphism)
    @Override
    public void showMenu() {
        System.out.println("\n===== MENU ADMIN (" + username + ") =====");
        System.out.println("1. Kelola Stasiun");         
        System.out.println("2. Kelola Rute/Jarak");       
        System.out.println("3. Kelola Tarif");         
        System.out.println("4. Lihat Data User");      
        System.out.println("5. Laporan Transaksi");     
        System.out.println("0. Logout");                 
    }
}
