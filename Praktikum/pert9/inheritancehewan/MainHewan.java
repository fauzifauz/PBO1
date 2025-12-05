package pert9.inheritancehewan;

public class MainHewan {
    public static void main(String[] args) {

        // Membuat objek kucing
        Kucing k = new Kucing("Oyen", 2);

        // Menampilkan data
        System.out.println("Nama: " + k.nama);
        System.out.println("Usia: " + k.usia + " tahun");

        // Panggil method
        k.bersuara();
    }
}

