package pert9.Sistemecommerce;

// Pakaian turunan Produk
public class Pakaian extends Produk {
    private String ukuran;

    public Pakaian(String nama, double harga, int stok, String ukuran) {
        super(nama, harga, stok);
        this.ukuran = ukuran;
    }

    @Override
    public void infoProduk() {
        super.infoProduk();
        System.out.println("Ukuran: " + ukuran);
    }
}

