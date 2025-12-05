package pert9.Sistemecommerce;

// Elektronik turunan Produk
public class Elektronik extends Produk {
    private int garansi; // dalam bulan

    public Elektronik(String nama, double harga, int stok, int garansi) {
        super(nama, harga, stok);
        this.garansi = garansi;
    }

    @Override
    public void infoProduk() {
        super.infoProduk();
        System.out.println("Garansi: " + garansi + " bulan");
    }
}

