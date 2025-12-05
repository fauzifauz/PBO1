package pert9.Sistemecommerce;

// Superclass Produk
public class Produk {
    protected String nama;
    protected double harga;
    protected int stok;

    public Produk(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Akan dioverride
    public void infoProduk() {
        System.out.println("Nama: " + nama);
        System.out.println("Harga: " + harga);
        System.out.println("Stok: " + stok);
    }

    // Validasi default
    public boolean bisaDibeli() {
        return stok > 0;
    }
}

