public class Station {
    private String id;                 
    private String name;               
    private double distanceFromStart;  // Jarak dari stasiun awal

    // Constructor untuk inisialisasi stasiun
    public Station(String id, String name, double distanceFromStart) {
        this.id = id;
        this.name = name;
        this.distanceFromStart = distanceFromStart;
    }

    public String getId() { return id; }                     // Getter ID
    public String getName() { return name; }                 // Getter nama
    public void setName(String name) { this.name = name; }   // Setter nama
    public double getDistanceFromStart() { return distanceFromStart; } // Getter jarak
    public void setDistanceFromStart(double distanceFromStart) {       // Setter jarak
        this.distanceFromStart = distanceFromStart;
    }

    // Menampilkan info stasiun dalam format string
    @Override
    public String toString() {
        return "[" + id + "] " + name + " (" + distanceFromStart + " km)";
    }
}
