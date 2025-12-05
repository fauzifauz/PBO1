package pert6.soallevel3;

public class MainProduk {
    public static void main(String[] args) {
        // Membuat objek dari kelas Produk
        Produk p1 = new Produk("Mouse Logitech", 250000, 10);

        // Membuat objek dari kelas Elektronik (turunan)
        Elektronik e1 = new Elektronik("Laptop ASUS ROG", 18500000, 5, "2 Tahun");
        Elektronik e2 = new Elektronik("Smart TV Samsung", 8500000, 3, "1 Tahun");

        // Menampilkan data produk
        System.out.println("=== DATA PRODUK UMUM ===");
        p1.tampilkanInfo();

        System.out.println("=== DATA PRODUK ELEKTRONIK ===");
        e1.tampilkanInfo();
        e2.tampilkanInfo();
    }
}
