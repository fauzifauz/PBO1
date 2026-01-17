public class TariffManager {
    private double baseFare;   // Tarif dasar
    private double farePerKm;  // Tarif per km

    // Constructor untuk inisialisasi tarif
    public TariffManager(double baseFare, double farePerKm) {
        this.baseFare = baseFare;
        this.farePerKm = farePerKm;
    }

    // Hitung tarif antara dua stasiun
    public double calculateFare(Station start, Station end) {
        double distance = Math.abs(start.getDistanceFromStart() - end.getDistanceFromStart()); // Jarak absolut
        double calculated = baseFare + (distance * farePerKm); // Hitung tarif
        return Math.floor(calculated / 100) * 100; // Pembulatan ke ratusan
    }

    // Setter & Getter untuk admin mengubah tarif
    public void setBaseFare(double baseFare) { this.baseFare = baseFare; }
    public void setFarePerKm(double farePerKm) { this.farePerKm = farePerKm; }
    public double getBaseFare() { return baseFare; }
    public double getFarePerKm() { return farePerKm; }
}
