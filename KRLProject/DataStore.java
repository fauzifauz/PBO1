import java.util.*;

public class DataStore {
    private List<Station> stations = new ArrayList<>();
    private List<Route> routes = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    // Stations
    public void addStation(Station s) {
        for (Station st : stations) if (st.getName().equalsIgnoreCase(s.getName())) throw new IllegalArgumentException("Stasiun sudah ada");
        stations.add(s);
    }
    public Station getStation(int idx) { if (!isValidStationIndex(idx)) throw new IndexOutOfBoundsException("Index stasiun tidak valid"); return stations.get(idx); }
    public boolean isValidStationIndex(int idx) { return idx >= 0 && idx < stations.size(); }
    public void removeStation(int idx) {
        Station s = getStation(idx);
        stations.remove(idx);
        removeRoutesUsingStation(s.getName());
    }
    public void updateStation(int idx, String newName) {
        if (!isValidStationIndex(idx)) throw new IndexOutOfBoundsException("Index stasiun tidak valid");
        if (newName == null || newName.trim().isEmpty()) throw new IllegalArgumentException("Nama tidak boleh kosong");
        stations.get(idx).setName(newName.trim());
    }
    public List<Station> getStations() { return Collections.unmodifiableList(stations); }
    public void printStations() {
        System.out.println("\n-- Daftar Stasiun --");
        if (stations.isEmpty()) { System.out.println("[Belum ada stasiun]"); return; }
        for (int i = 0; i < stations.size(); i++) System.out.println(i + ". " + stations.get(i).getName());
    }

    public void addRoute(Route r) {
        routes.add(r);
    }
    public Route getRoute(int idx) { if (!isValidRouteIndex(idx)) throw new IndexOutOfBoundsException("Index rute tidak valid"); return routes.get(idx); }
    public boolean isValidRouteIndex(int idx) { return idx >= 0 && idx < routes.size(); }
    public void removeRoute(int idx) { if (!isValidRouteIndex(idx)) throw new IndexOutOfBoundsException("Index rute tidak valid"); routes.remove(idx); }
    public void updateRoute(int idx, String asal, String tujuan, double harga) {
        Route r = getRoute(idx);
        r.setAsal(asal); r.setTujuan(tujuan); r.setHarga(harga);
    }
    public void removeRoutesUsingStation(String stationName) {
        routes.removeIf(r -> r.getAsal().equalsIgnoreCase(stationName) || r.getTujuan().equalsIgnoreCase(stationName));
    }
    public List<Route> getRoutes() { return Collections.unmodifiableList(routes); }
    public void printRoutes() {
        System.out.println("\n-- Daftar Rute --");
        if (routes.isEmpty()) { System.out.println("[Belum ada rute]"); return; }
        for (int i = 0; i < routes.size(); i++) System.out.println(i + ". " + routes.get(i).toString());
    }

    // Users
    public void addUser(User u) {
        for (User us : users) if (us.getUsername().equalsIgnoreCase(u.getUsername())) throw new IllegalArgumentException("Username sudah ada");
        users.add(u);
    }
    public User authenticate(String username, String password) {
        for (User u : users) if (u.checkCredentials(username, password)) return u;
        return null;
    }
    public List<User> getUsers() { return Collections.unmodifiableList(users); }
    public void printUsers() {
        System.out.println("\n-- Pengguna --");
        if (users.isEmpty()) { System.out.println("[Belum ada user]"); return; }
        for (User u : users) System.out.println("- " + u.getUsername() + " (" + u.getRole() + ")");
    }

    public void printAll() {
        printStations();
        printRoutes();
        printUsers();
    }
}
