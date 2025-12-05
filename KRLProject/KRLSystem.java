import java.util.Scanner;

public class KRLSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataStore store = new DataStore();

        store.addStation(new Station("Tanah Abang"));
        store.addStation(new Station("Manggarai"));
        store.addStation(new Station("Bogor"));
        store.addStation(new Station("Serpong"));

        store.addRoute(new Route("Tanah Abang", "Serpong", 5000));
        store.addRoute(new Route("Manggarai", "Bogor", 8000));
        store.addRoute(new Route("Tanah Abang", "Manggarai", 4000));
        store.addRoute(new Route("Bogor", "Serpong", 12000));

        store.addUser(new Admin("admin", "admin123"));
        store.addUser(new Passenger("fauzi", "1234", 20000));

        System.out.println("=== SISTEM RUTE & PEMBAYARAN KRL ===");

        boolean exitProgram = false;

        // Loop utama program
        do {
            System.out.println("\n1. Login");
            System.out.println("2. Daftar (Passenger)");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            int mainChoice = Utils.readInt(sc);
            sc.nextLine(); // Membersihkan buffer

            if (mainChoice == 1) {
                System.out.print("Username: ");
                String u = sc.nextLine().trim();
                System.out.print("Password: ");
                String p = sc.nextLine().trim();

                User user = store.authenticate(u, p);
                if (user != null) {
                    System.out.println("Login berhasil sebagai " 
                        + user.getRole() + " (" + user.getUsername() + ")");
                    user.showMenu(sc, store);
                } else {
                    System.out.println("Login gagal. Username/Password salah.");
                }

            } else if (mainChoice == 2) {
                boolean ulangDaftar;
                do {
                    ulangDaftar = false;
                    try {
                        System.out.print("Buat username: ");
                        String nu = sc.nextLine().trim();
                        System.out.print("Buat password (min 4): ");
                        String np = sc.nextLine().trim();
                        System.out.print("Top-up saldo awal (Rp): ");
                        double initBal = Utils.readDouble(sc);
                        sc.nextLine();

                        store.addUser(new Passenger(nu, np, initBal));
                        System.out.println("Registrasi berhasil. Silakan login.");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Gagal registrasi: " + ex.getMessage());
                    }

                    System.out.print("Ketik 0 untuk kembali ke menu utama, atau angka lain untuk daftar lagi: ");
                    int kembali = Utils.readInt(sc);
                    sc.nextLine();
                    if (kembali != 0) {
                        ulangDaftar = true;
                    }
                } while (ulangDaftar);

            } else if (mainChoice == 0) {
                System.out.println("Apakah Anda yakin ingin keluar? (y/n): ");
                String confirm = sc.nextLine().trim();
                if (confirm.equalsIgnoreCase("y")) {
                    exitProgram = true;
                    System.out.println("Terima kasih. Program selesai.");
                } else {
                    System.out.println("Kembali ke menu utama...");
                }

            } else {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }

        } while (!exitProgram);

        sc.close();
    }
}
