package pert6.soallevel2;

public class MainRekening {
    public static void main(String[] args) {

        // Membuat objek RekeningBank dengan saldo awal positif
        RekeningBank r1 = new RekeningBank("123456789", 500000);

        // Membuat objek RekeningBank dengan saldo awal negatif (akan muncul pesan peringatan)
        RekeningBank r2 = new RekeningBank("987654321", -100000); 

        // Membuat objek RekeningBank dengan saldo awal positif besar
        RekeningBank r3 = new RekeningBank("555888999", 1500000);

        // Menampilkan semua data rekening saat pertama kali dibuat
        System.out.println("=== Data Rekening Awal ===");
        r1.tampilkanInfo();
        r2.tampilkanInfo();
        r3.tampilkanInfo();

        // Menguji apakah sistem menolak saldo negatif saat diubah lewat setter
        System.out.println("=== Uji Saldo Negatif ===");
        r1.setSaldo(-50000); // Akan menampilkan pesan "Saldo tidak boleh negatif"

        // Mengubah saldo secara valid dan menampilkan hasilnya
        System.out.println("=== Ubah Saldo Valid ===");
        r3.setSaldo(2000000); // Mengatur saldo baru
        r3.tampilkanInfo();   // Menampilkan data rekening terbaru
    }
}
