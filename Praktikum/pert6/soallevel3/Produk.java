package pert6.soallevel3;

// Kelas induk
public class Produk {
    // Atribut private (enkapsulasi)
    private String namaProduk;
    private double harga;
    private int stok;

    // Konstruktor
    public Produk(String namaProduk, double harga, int stok) {
        this.namaProduk = namaProduk;
        this.harga = harga;
        if (stok < 0) {
            System.out.println("Stok tidak boleh negatif! Stok diatur menjadi 0.");
            this.stok = 0;
        } else {
            this.stok = stok;
        }
    }

    // Getter dan Setter
    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        if (stok < 0) {
            System.out.println("Stok tidak boleh negatif!");
        } else {
            this.stok = stok;
        }
    }

    // Method untuk menambah stok
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            stok += jumlah;
            System.out.println("Stok bertambah sebanyak " + jumlah);
        } else {
            System.out.println("Jumlah penambahan harus lebih dari 0!");
        }
    }

    // Method untuk mengurangi stok
    public void kurangiStok(int jumlah) {
        if (jumlah > 0 && stok - jumlah >= 0) {
            stok -= jumlah;
            System.out.println("Stok berkurang sebanyak " + jumlah);
        } else if (jumlah > 0 && stok - jumlah < 0) {
            System.out.println("Stok tidak mencukupi untuk dikurangi!");
        } else {
            System.out.println("Jumlah pengurangan harus lebih dari 0!");
        }
    }

    // Method menampilkan info produk
    public void tampilkanInfo() {
        System.out.println("Nama Produk : " + namaProduk);
        System.out.println("Harga       : Rp" + harga);
        System.out.println("Stok        : " + stok);
    }
}
