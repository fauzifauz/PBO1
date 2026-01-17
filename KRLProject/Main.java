import java.util.Scanner;

public class Main {
    private static KRLSystem system = new KRLSystem();  // Objek sistem KRL
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            // Tampilan menu utama
            System.out.println("\n========================================");
            System.out.println(" SISTEM KRL - SISTEM INFORMASI TERPADU");
            System.out.println("========================================");
            System.out.println("1. Login Admin");
            System.out.println("2. Login User (Penumpang)");
            System.out.println("3. Registrasi User Baru");
            System.out.println("0. Exit");
            System.out.print("Pilih: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": handleLogin("ADMIN"); break;   // Login Admin
                case "2": handleLogin("USER"); break;    // Login Passenger
                case "3": handleRegistration(); break;   // Registrasi user baru
                case "0": running = false; break;        // Exit
                default: System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void handleRegistration() {
        // Form registrasi user baru
        System.out.print("Masukkan Username: ");
        String username = scanner.nextLine();
        if (username.trim().isEmpty()) { System.out.println("Username tidak boleh kosong."); return; }

        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        boolean success = system.registerPassenger(username, password);
        System.out.println(success ? "Registrasi Berhasil! Silakan Login." : "Username sudah digunakan.");
    }

    private static void handleLogin(String expectedRole) {
        // Form login
        System.out.print("Username: "); String username = scanner.nextLine();
        System.out.print("Password: "); String password = scanner.nextLine();

        User user = system.login(username, password);
        if (user != null) {
            // Cek role sesuai
            if (expectedRole.equals("ADMIN") && !(user instanceof Admin)) {
                System.out.println("Login Gagal! Bukan akun Admin."); return;
            }
            if (expectedRole.equals("USER") && !(user instanceof Passenger)) {
                System.out.println("Login Gagal! Bukan akun User."); return;
            }

            System.out.println("Login Berhasil! Selamat datang, " + user.getUsername());
            if (user instanceof Admin) adminMenu((Admin) user);       // Menu Admin
            else if (user instanceof Passenger) passengerMenu((Passenger) user); // Menu Passenger
        } else System.out.println("Login Gagal! Username atau password salah.");
    }

    // ===== ADMIN MENU =====
    private static void adminMenu(Admin admin) {
        boolean back = false;
        while (!back) {
            admin.showMenu(); // Tampilkan menu (Polymorphism)
            System.out.print("Pilih menu: "); String choice = scanner.nextLine();

            switch (choice) {
                case "1": case "2": manageStations(); break; // Kelola stasiun/rute
                case "3": manageTariff(); break;            // Kelola tarif
                case "4": viewUsers(); break;               // Lihat/hapus user
                case "5":                                   // Laporan transaksi
                    system.printAllTransactions();
                    System.out.println("Total Pendapatan Sistem: Rp " + system.getTotalRevenue());
                    break;
                case "0": back = true; break;
                default: System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void manageStations() {
        // CRUD Stasiun
        System.out.println("\n--- DAFTAR STASIUN ---");
        for (int i = 0; i < system.getStations().size(); i++)
            System.out.println((i + 1) + ". " + system.getStations().get(i));

        System.out.println("1. Tambah 2. Hapus 3. Ubah Nama 0. Kembali");
        String opt = scanner.nextLine();

        try {
            if (opt.equals("1")) {
                System.out.print("Kode Stasiun: "); String id = scanner.nextLine();
                System.out.print("Nama Stasiun: "); String name = scanner.nextLine();
                System.out.print("Jarak dari 0 (km): "); double dist = Double.parseDouble(scanner.nextLine());
                system.addStation(id, name, dist);
            } else if (opt.equals("2")) {
                System.out.print("Nomor stasiun: "); int idx = Integer.parseInt(scanner.nextLine()) - 1;
                system.deleteStation(idx);
            } else if (opt.equals("3")) {
                System.out.print("Nomor stasiun: "); int idx = Integer.parseInt(scanner.nextLine()) - 1;
                System.out.print("Nama Baru: "); String newName = scanner.nextLine();
                system.editStationName(idx, newName);
            }
        } catch (NumberFormatException e) { System.out.println("Input angka tidak valid!"); }
    }

    private static void manageTariff() {
        // Atur tarif KRL
        TariffManager tm = system.getTariffManager();
        System.out.println("\nBase Fare: " + tm.getBaseFare() + ", Per KM: " + tm.getFarePerKm());
        System.out.print("Ubah tarif? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            try {
                System.out.print("Base Fare Baru: "); double bf = Double.parseDouble(scanner.nextLine());
                System.out.print("Per KM Baru: "); double pkm = Double.parseDouble(scanner.nextLine());
                tm.setBaseFare(bf); tm.setFarePerKm(pkm);
                System.out.println("Tarif diperbarui.");
            } catch (NumberFormatException e) { System.out.println("Input salah."); }
        }
    }

    private static void viewUsers() {
        // Daftar & hapus user
        System.out.println("\n--- DAFTAR USER ---");
        for (int i = 0; i < system.getUsers().size(); i++) {
            User u = system.getUsers().get(i);
            System.out.println((i + 1) + ". " + u.getUsername() + " [" + u.getRole() + "]");
        }
        try {
            System.out.print("Nomor user untuk hapus (0 batal): "); int idx = Integer.parseInt(scanner.nextLine()) - 1;
            if (idx != -1) system.deleteUser(idx);
        } catch (Exception e) { System.out.println("Error input."); }
    }

    // ===== PASSENGER MENU =====
    private static void passengerMenu(Passenger p) {
        boolean back = false;
        while (!back) {
            p.showMenu(); System.out.print("Pilih menu: "); String choice = scanner.nextLine();

            switch (choice) {
                case "1": // Lihat Rute
                    for (Station s : system.getStations()) System.out.println(s);
                    break;
                case "2": handleRide(p); break;   // Naik KRL
                case "3": System.out.println("Saldo Anda: Rp " + p.getBalance()); break; // Cek saldo
                case "4": // Top Up
                    System.out.print("Jumlah Top Up: ");
                    try { double amt = Double.parseDouble(scanner.nextLine());
                        if (amt > 0) { p.topUp(amt); System.out.println("Saldo baru: " + p.getBalance()); }
                        else System.out.println("Jumlah tidak valid.");
                    } catch (NumberFormatException e) { System.out.println("Input harus angka."); }
                    break;
                case "5": p.viewHistory(); break; // Riwayat perjalanan
                case "0": back = true; break;
                default: System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void handleRide(Passenger p) {
        // Pilih stasiun & bayar
        for (int i = 0; i < system.getStations().size(); i++)
            System.out.println((i + 1) + ". " + system.getStations().get(i).getName());

        try {
            System.out.print("Stasiun Asal: "); int startIdx = Integer.parseInt(scanner.nextLine()) - 1;
            System.out.print("Stasiun Tujuan: "); int endIdx = Integer.parseInt(scanner.nextLine()) - 1;

            if (startIdx < 0 || endIdx < 0 || startIdx >= system.getStations().size() || endIdx >= system.getStations().size()) {
                System.out.println("Stasiun tidak valid."); return;
            }
            if (startIdx == endIdx) { System.out.println("Anda memilih stasiun yang sama."); return; }

            Station start = system.getStations().get(startIdx);
            Station end = system.getStations().get(endIdx);
            double fare = system.calculateFare(start, end);

            System.out.println("Rute: " + start.getName() + " -> " + end.getName());
            System.out.println("Tarif: Rp " + fare + ", Saldo: Rp " + p.getBalance());

            System.out.print("Konfirmasi bayar? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                if (p.deductBalance(fare)) {
                    p.addTransaction(new Transaction(start, end, fare)); // Catat transaksi
                    system.recordTransaction(fare);                      // Update pendapatan sistem
                    System.out.println("Pembayaran Berhasil!");
                } else System.out.println("Saldo tidak mencukupi.");
            } else System.out.println("Transaksi dibatalkan.");

        } catch (NumberFormatException e) { System.out.println("Input tidak valid."); }
    }
}
