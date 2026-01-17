import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String transactionId;       // ID transaksi unik
    private Station origin;             // Stasiun asal
    private Station destination;        // Stasiun tujuan
    private double amount;              // Tarif perjalanan
    private LocalDateTime timestamp;    // Waktu transaksi

    // Constructor inisialisasi transaksi
    public Transaction(Station origin, Station destination, double amount) {
        this.transactionId = "TRX-" + System.currentTimeMillis(); 
        this.origin = origin;                                    
        this.destination = destination;                         
        this.amount = amount;                                     
        this.timestamp = LocalDateTime.now();                    
    }

    // Menampilkan detail transaksi dalam format string
    public String getDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Format waktu
        return String.format("ID: %s | Time: %s | Route: %s -> %s | Fare: Rp %.0f",
                transactionId,
                timestamp.format(formatter),
                origin.getName(),
                destination.getName(),
                amount);
    }

    public double getAmount() { return amount; } 
}
