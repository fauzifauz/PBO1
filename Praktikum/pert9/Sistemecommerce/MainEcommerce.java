package pert9.Sistemecommerce;

import java.time.LocalDate;

public class MainEcommerce {
    public static void main(String[] args) {

        Elektronik e = new Elektronik("Laptop", 12000000, 5, 24);
        Pakaian p = new Pakaian("Jaket", 250000, 10, "L");
        Makanan m = new Makanan("Roti", 15000, 20, LocalDate.of(2023, 5, 1));

        e.infoProduk();
        System.out.println("Bisa dibeli? " + e.bisaDibeli());

        System.out.println();

        p.infoProduk();
        System.out.println("Bisa dibeli? " + p.bisaDibeli());

        System.out.println();

        m.infoProduk();
        System.out.println("Bisa dibeli? " + m.bisaDibeli() + " (kadaluarsa!)");
    }
}

