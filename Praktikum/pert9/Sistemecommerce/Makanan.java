package pert9.Sistemecommerce;

import java.time.LocalDate;

// Makanan turunan Produk
public class Makanan extends Produk {
    private LocalDate expired;

    public Makanan(String nama, double harga, int stok, LocalDate expired) {
        super(nama, harga, stok);
        this.expired = expired;
    }

    @Override
    public void infoProduk() {
        super.infoProduk();
        System.out.println("Kadaluarsa: " + expired);
    }

    // Validasi: makanan kadaluarsa tidak boleh dibeli
    @Override
    public boolean bisaDibeli() {
        return stok > 0 && expired.isAfter(LocalDate.now());
    }
}

