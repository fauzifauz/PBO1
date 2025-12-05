import java.util.Scanner;
import java.util.List;

// Class Passenger merupakan turunan dari User
// Mewakili penumpang yang dapat melihat rute, memesan tiket, dan mengatur saldo
public class Passenger extends User {
    private Payment wallet; // Menyimpan saldo penumpang

    // Konstruktor untuk membuat objek Passenger
    public Passenger(String username, String password, double initialBalance) {
        super(username, password, "Passenger"); // Memanggil konstruktor dari User
        if (initialBalance < 0) initialBalance = 0; // Jika saldo awal negatif, diatur ke 0
        this.wallet = new Payment(initialBalance); // Inisialisasi dompet
    }

    // Getter saldo
    public double getBalance() { return wallet.getBalance(); }

    // Method untuk menambah saldo
    public void topUp(double amount) { wallet.topUp(amount); }

    // Override dari User: menampilkan menu utama Passenger
    @Override
    public void showMenu(Scanner sc, DataStore store) {
        int choice;
        do {
            System.out.println("\n=== PASSENGER MENU ===");
            System.out.println("1. Lihat Stasiun");
            System.out.println("2. Lihat Rute");
            System.out.println("3. Hitung & Pesan Tiket (pilih rute)");
            System.out.println("4. Cari Jalur Terpendek (otomatis)");
            System.out.println("5. Cek Saldo / Top-up");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            choice = Utils.readInt(sc); // Input pilihan user dengan validasi angka

            try {
                // Proses menu sesuai pilihan user
                switch (choice) {
                    case 1: store.printStations(); break; // Menampilkan daftar stasiun
                    case 2: store.printRoutes(); break;   // Menampilkan daftar rute
                    case 3: bookTicket(sc, store); break; // Memesan tiket manual
                    case 4: autoRoute(sc, store); break;  // Mencari rute otomatis (jalur terpendek)
                    case 5: walletMenu(sc); break;        // Menu saldo & top up
                    case 0: System.out.println("Logout Passenger."); break; // Keluar
                    default: System.out.println("Pilihan tidak valid."); break;
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } while (choice != 0); // Ulangi hingga user memilih logout
    }

    // Method untuk memesan tiket dari daftar rute yang tersedia
    private void bookTicket(Scanner sc, DataStore store) {
        store.printRoutes(); // Tampilkan semua rute
        if (store.getRoutes().isEmpty()) { // Jika belum ada rute
            System.out.println("Belum ada rute.");
            return;
        }

        System.out.print("Pilih index rute yang ingin dipesan: ");
        int idx = Utils.readInt(sc); // Pilih rute berdasarkan index
        Route r = store.getRoute(idx); // Ambil data rute yang dipilih
        System.out.println("Rute: " + r);

        System.out.print("Konfirmasi bayar? (y/n): ");
        String confirm = sc.nextLine().trim().toLowerCase();
        if (!confirm.equals("y")) { // Jika batal
            System.out.println("Pembayaran dibatalkan.");
            return;
        }

        // Jika saldo cukup, lakukan pembayaran
        if (wallet.pay(r.getHarga())) {
            System.out.println("Pembayaran berhasil. Sisa saldo: Rp" + String.format("%.0f", wallet.getBalance()));
        } else {
            System.out.println("Saldo tidak cukup. Silakan top-up.");
        }
    }

    // Method untuk mencari jalur otomatis menggunakan algoritma Dijkstra (GraphUtils)
    private void autoRoute(Scanner sc, DataStore store) {
        store.printStations(); // Tampilkan stasiun
        System.out.print("Index stasiun asal: ");
        int a = Utils.readInt(sc);
        System.out.print("Index stasiun tujuan: ");
        int b = Utils.readInt(sc);

        // Ambil nama stasiun berdasarkan index
        String start = store.getStation(a).getName();
        String end = store.getStation(b).getName();

        // Cari jalur terpendek dari GraphUtils
        java.util.List<GraphUtils.PathNode> path = GraphUtils.findShortestPath(store, start, end);
        if (path == null || path.isEmpty()) {
            System.out.println("Tidak ditemukan jalur antara " + start + " dan " + end);
            return;
        }

        // Tampilkan jalur yang ditemukan
        System.out.println("Jalur terpendek:");
        double total = 0;
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).node);
            if (i < path.size() - 1) System.out.print(" -> ");
            if (i < path.size() - 1)
                total += GraphUtils.getEdgeCost(store, path.get(i).node, path.get(i+1).node);
        }

        System.out.println("\nTotal biaya: Rp" + String.format("%.0f", total));
        System.out.print("Bayar tiket untuk jalur ini? (y/n): ");
        String confirm = sc.nextLine().trim().toLowerCase();

        // Konfirmasi pembayaran otomatis
        if (confirm.equals("y")) {
            if (wallet.pay(total))
                System.out.println("Pembayaran berhasil. Sisa saldo: Rp" + String.format("%.0f", wallet.getBalance()));
            else
                System.out.println("Saldo tidak cukup. Silakan top-up.");
        } else {
            System.out.println("Pembayaran dibatalkan.");
        }
    }

    // Menu khusus untuk mengatur saldo
    private void walletMenu(Scanner sc) {
        int ch;
        do {
            System.out.println("\n-- Wallet --");
            System.out.println("1. Cek Saldo");
            System.out.println("2. Top-up Saldo");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            ch = Utils.readInt(sc);

            switch (ch) {
                case 1: // Menampilkan saldo saat ini
                    System.out.println("Saldo Anda: Rp" + String.format("%.0f", wallet.getBalance()));
                    break;
                case 2: // Menambah saldo
                    System.out.print("Masukkan jumlah top-up (Rp): ");
                    double amt = Utils.readDouble(sc);
                    wallet.topUp(amt);
                    System.out.println("Top-up berhasil. Saldo sekarang: Rp" + String.format("%.0f", wallet.getBalance()));
                    break;
                case 0: break; // Kembali ke menu utama
                default: System.out.println("Pilihan tidak valid."); break;
            }
        } while (ch != 0);
    }
}
