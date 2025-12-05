package pert6.soallevel2; // Mendeklarasikan package tempat kelas ini berada

public class RekeningBank {
    // ====== Deklarasi atribut (bersifat private agar menerapkan enkapsulasi) ======
    private String nomorRekening; // Menyimpan nomor rekening
    private double saldo;         // Menyimpan saldo rekening

    // ====== Konstruktor ======
    // Konstruktor digunakan untuk memberikan nilai awal saat objek dibuat
    public RekeningBank(String nomorRekening, double saldo) {
        this.nomorRekening = nomorRekening; // Menginisialisasi nomor rekening

        // Mengecek apakah saldo yang dimasukkan bernilai negatif
        if (saldo < 0) {
            System.out.println("Saldo tidak boleh negatif. Saldo diatur menjadi 0.");
            this.saldo = 0; // Jika negatif, saldo otomatis diatur ke 0
        } else {
            this.saldo = saldo; // Jika valid, saldo diset sesuai nilai input
        }
    }

    // ====== Getter dan Setter untuk nomor rekening ======
    // Getter digunakan untuk mengambil nilai atribut
    public String getNomorRekening() {
        return nomorRekening; // Mengembalikan nilai nomor rekening
    }

    // Setter digunakan untuk mengubah nilai atribut
    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening; // Mengatur nomor rekening baru
    }

    // ====== Getter dan Setter untuk saldo ======
    // Getter untuk melihat saldo saat ini
    public double getSaldo() {
        return saldo; // Mengembalikan nilai saldo
    }

    // Setter untuk mengubah saldo rekening
    public void setSaldo(double saldo) {
        // Jika saldo yang dimasukkan negatif, tampilkan pesan peringatan
        if (saldo < 0) {
            System.out.println("Saldo tidak boleh negatif!");
        } else {
            this.saldo = saldo; // Jika valid, saldo diset sesuai nilai baru
        }
    }

    // ====== Method tampilkanInfo ======
    // Method ini digunakan untuk menampilkan data rekening ke layar
    public void tampilkanInfo() {
        System.out.println("Nomor Rekening : " + nomorRekening); // Menampilkan nomor rekening
        System.out.println("Saldo          : Rp" + saldo);        // Menampilkan saldo rekening
        System.out.println("------------------------------");      // Pemisah agar output lebih rapi
    }
}
