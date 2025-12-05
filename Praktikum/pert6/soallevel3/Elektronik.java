package pert6.soallevel3;

// Kelas turunan Elektronik mewarisi Produk
public class Elektronik extends Produk {
    // Atribut tambahan dengan enkapsulasi
    private String garansi;

    // Konstruktor Elektronik memanggil konstruktor Produk (super)
    public Elektronik(String namaProduk, double harga, int stok, String garansi) {
        super(namaProduk, harga, stok); // memanggil konstruktor dari kelas induk
        this.garansi = garansi;
    }

    // Getter dan Setter untuk garansi
    public String getGaransi() {
        return garansi;
    }

    public void setGaransi(String garansi) {
        this.garansi = garansi;
    }

    // Override method tampilkanInfo() untuk menampilkan data lengkap
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo(); // tampilkan info dari kelas induk
        System.out.println("Garansi     : " + garansi);
        System.out.println("----------------------------------");
    }
}