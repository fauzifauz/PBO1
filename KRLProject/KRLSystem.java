import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KRLSystem {
    private List<Station> stations;        
    private List<User> users;             
    private TariffManager tariffManager;    

    // Constructor inisialisasi sistem
    public KRLSystem() {
        this.stations = new ArrayList<>();
        this.users = new ArrayList<>();
        this.tariffManager = new TariffManager(3000, 1000); // Tarif default
        seedData(); // Tambah data awal
    }

    // Inisialisasi data default
    private void seedData() {
        users.add(new Admin("admin", "admin123")); // Admin default
        Passenger p1 = new Passenger("budi", "budi123");
        p1.topUp(50000); 
        users.add(p1); // User default

        // Stasiun default
        stations.add(new Station("ST01", "Jakarta Kota", 0.0));
        stations.add(new Station("ST02", "Manggarai", 9.8));
        stations.add(new Station("ST03", "Tebet", 12.4));
        stations.add(new Station("ST04", "Pasar Minggu", 17.0));
        stations.add(new Station("ST05", "Universitas Indonesia", 27.0));
        stations.add(new Station("ST06", "Bogor", 45.0));
    }

    // === AUTHENTICATION ===
    public User login(String username, String password) {
        for (User u : users) {                  // Loop cek credential
            if (u.checkCredentials(username, password)) {
                return u;                        // Login berhasil
            }
        }
        return null;                             // Login gagal
    }

    public boolean registerPassenger(String username, String password) {
        for (User u : users) {                   // Cek duplicate username
            if (u.getUsername().equalsIgnoreCase(username)) {
                return false;                    // Username sudah ada
            }
        }
        users.add(new Passenger(username, password)); // Tambah user baru
        return true;
    }

    // === STATION MANAGEMENT (Admin) ===
    public void addStation(String id, String name, double distance) {
        stations.add(new Station(id, name, distance)); 
        sortStations();                           // Urutkan stasiun
        System.out.println("Stasiun berhasil ditambahkan!");
    }

    public void deleteStation(int index) {
        if (index >= 0 && index < stations.size()) {
            Station s = stations.remove(index);   // Hapus stasiun
            System.out.println("Stasiun " + s.getName() + " dihapus.");
        } else {
            System.out.println("Index tidak valid.");
        }
    }

    public void editStationName(int index, String newName) {
        if (index >= 0 && index < stations.size()) {
            stations.get(index).setName(newName); // Edit nama stasiun
            System.out.println("Nama stasiun berhasil diubah.");
        }
    }

    public List<Station> getStations() { return stations; }

    private void sortStations() {                // Urutkan stasiun berdasarkan jarak
        stations.sort(Comparator.comparingDouble(Station::getDistanceFromStart));
    }

    // === TARIFF MANAGEMENT (Admin) ===
    public TariffManager getTariffManager() { return tariffManager; }

    // === USER MANAGEMENT (Admin) ===
    public List<User> getUsers() { return users; }

    public void deleteUser(int index) {
        if (index >= 0 && index < users.size()) {
            User u = users.get(index);
            if (u instanceof Admin) {           // Cek admin
                System.out.println("Tidak dapat menghapus Admin utama.");
                return;
            }
            users.remove(index);                 // Hapus user
            System.out.println("User " + u.getUsername() + " dihapus.");
        }
    }

    // === REPORTING (Admin) ===
    public void printAllTransactions() {
        System.out.println("\n=== LAPORAN TRANSAKSI SYSTEM ===");
    }

    private double totalRevenueSystem = 0;       // Total pemasukan sistem

    public void recordTransaction(double amount) {
        this.totalRevenueSystem += amount;       // Tambah revenue
    }

    public double getTotalRevenue() { return totalRevenueSystem; }

    // === PASSENGER ACTIONS ===
    public double calculateFare(Station start, Station end) {
        return tariffManager.calculateFare(start, end); // Hitung tarif perjalanan
    }
}
