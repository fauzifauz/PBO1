import java.util.Scanner;

// Kelas Admin merupakan turunan dari kelas User
public class Admin extends User {

    // Constructor: memanggil constructor induk (User) dengan role "Admin"
    public Admin(String username, String password) {
        super(username, password, "Admin");
    }

    // Override method showMenu untuk menampilkan menu khusus Admin
    @Override
    public void showMenu(Scanner sc, DataStore store) {
        int choice; // Variabel untuk menyimpan pilihan menu admin
        do { // LOOPING: akan terus berjalan sampai admin memilih logout
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. Kelola Stasiun");
            System.out.println("2. Kelola Rute");
            System.out.println("3. Lihat Pengguna");
            System.out.println("4. Ekspor CSV");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");

            // Membaca input integer dari user (dengan validasi dari Utils)
            choice = Utils.readInt(sc);

            // SWITCH: kontrol alur program berdasarkan pilihan menu admin
            switch (choice) {
                case 1: 
                    // Panggil method untuk mengelola stasiun
                    manageStations(sc, store); 
                    break;

                case 2: 
                    // Panggil method untuk mengelola rute
                    manageRoutes(sc, store); 
                    break;

                case 3: 
                    // Menampilkan seluruh pengguna dari DataStore
                    store.printUsers(); 
                    break;

                case 4:
                    // Try-Catch: mengantisipasi error saat ekspor data ke CSV
                    try {
                        CSVExporter.exportAll(store);
                        System.out.println("Ekspor CSV sukses.");
                    } catch (Exception e) {
                        System.out.println("Gagal ekspor CSV: " + e.getMessage());
                    }
                    break;

                case 0: 
                    // Jika 0, keluar dari menu admin
                    System.out.println("Logout Admin."); 
                    break;

                default: 
                    // Jika input tidak sesuai dengan menu di atas
                    System.out.println("Pilihan tidak valid."); 
                    break;
            }
        } while (choice != 0); // LOOP BERHENTI saat admin memilih 0
    }

    // ====================== METHOD KELOLA STASIUN ======================
    private void manageStations(Scanner sc, DataStore store) {
        int ch; // variabel pilihan menu untuk kelola stasiun
        do { // LOOPING: untuk menampilkan menu stasiun berulang kali
            System.out.println("\n-- Kelola Stasiun --");
            System.out.println("1. Lihat Stasiun");
            System.out.println("2. Tambah Stasiun");
            System.out.println("3. Edit Stasiun");
            System.out.println("4. Hapus Stasiun");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            ch = Utils.readInt(sc); // Membaca input pilihan admin

            // TRY-CATCH: menangkap error input seperti index tidak valid
            try {
                // SWITCH: menentukan aksi sesuai pilihan admin
                switch (ch) {
                    case 1: 
                        // Menampilkan semua data stasiun
                        store.printStations(); 
                        break;

                    case 2:
                        // Menambah stasiun baru
                        System.out.print("Nama stasiun baru: ");
                        String name = sc.nextLine().trim();
                        store.addStation(new Station(name));
                        System.out.println("Stasiun ditambahkan.");
                        break;

                    case 3:
                        // Mengedit nama stasiun berdasarkan index
                        store.printStations(); // tampilkan daftar dulu
                        System.out.print("Index stasiun untuk edit: ");
                        int idx = Utils.readInt(sc); // pilih index
                        System.out.print("Nama baru: ");
                        String newName = sc.nextLine().trim();
                        store.updateStation(idx, newName);
                        System.out.println("Stasiun diperbarui.");
                        break;

                    case 4:
                        // Menghapus stasiun berdasarkan index
                        store.printStations(); // tampilkan daftar stasiun
                        System.out.print("Index stasiun untuk hapus: ");
                        int del = Utils.readInt(sc);
                        store.removeStation(del);
                        System.out.println("Stasiun (dan rute terkait) dihapus.");
                        break;

                    case 0: 
                        // Kembali ke menu utama admin
                        break;

                    default: 
                        // Jika pilihan di luar menu 0-4
                        System.out.println("Pilihan tidak valid."); 
                        break;
                }
            } catch (IndexOutOfBoundsException | IllegalArgumentException ex) {
                // Jika index tidak valid atau argumen salah
                System.out.println("Error: " + ex.getMessage());
            }
        } while (ch != 0); // LOOP berhenti saat user memilih kembali (0)
    }

    // ====================== METHOD KELOLA RUTE ======================
    private void manageRoutes(Scanner sc, DataStore store) {
        int ch; // variabel pilihan menu untuk kelola rute
        do { // LOOPING: untuk menampilkan menu rute berulang kali
            System.out.println("\n-- Kelola Rute --");
            System.out.println("1. Lihat Rute");
            System.out.println("2. Tambah Rute");
            System.out.println("3. Edit Rute");
            System.out.println("4. Hapus Rute");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            ch = Utils.readInt(sc); // membaca input pilihan

            try {
                // SWITCH: mengeksekusi fitur sesuai pilihan
                switch (ch) {
                    case 1: 
                        // Menampilkan semua data rute
                        store.printRoutes(); 
                        break;

                    case 2:
                        // Menambahkan rute baru antara dua stasiun
                        store.printStations(); // tampilkan daftar stasiun
                        System.out.print("Index stasiun asal: ");
                        int ia = Utils.readInt(sc);
                        System.out.print("Index stasiun tujuan: ");
                        int ib = Utils.readInt(sc);
                        System.out.print("Harga (Rp): ");
                        double price = Utils.readDouble(sc);
                        // Menambahkan rute baru ke DataStore
                        store.addRoute(new Route(
                            store.getStation(ia).getName(), 
                            store.getStation(ib).getName(), 
                            price
                        ));
                        System.out.println("Rute ditambahkan.");
                        break;

                    case 3:
                        // Mengedit rute yang sudah ada
                        store.printRoutes(); // tampilkan semua rute
                        System.out.print("Index rute untuk edit: ");
                        int idx = Utils.readInt(sc);
                        store.printStations(); // tampilkan daftar stasiun lagi
                        System.out.print("Index stasiun asal baru: ");
                        int na = Utils.readInt(sc);
                        System.out.print("Index stasiun tujuan baru: ");
                        int nb = Utils.readInt(sc);
                        System.out.print("Harga baru (Rp): ");
                        double newPrice = Utils.readDouble(sc);
                        // Update data rute
                        store.updateRoute(idx, 
                            store.getStation(na).getName(), 
                            store.getStation(nb).getName(), 
                            newPrice
                        );
                        System.out.println("Rute diperbarui.");
                        break;

                    case 4:
                        // Menghapus rute berdasarkan index
                        store.printRoutes();
                        System.out.print("Index rute untuk hapus: ");
                        int del = Utils.readInt(sc);
                        store.removeRoute(del);
                        System.out.println("Rute dihapus.");
                        break;

                    case 0: 
                        // Kembali ke menu utama admin
                        break;

                    default: 
                        // Jika pilihan tidak dikenal
                        System.out.println("Pilihan tidak valid."); 
                        break;
                }
            } catch (IndexOutOfBoundsException | IllegalArgumentException ex) {
                // Menangkap error jika index atau input tidak sesuai
                System.out.println("Error: " + ex.getMessage());
            }
        } while (ch != 0); // LOOP berhenti saat admin memilih 0
    }
}
