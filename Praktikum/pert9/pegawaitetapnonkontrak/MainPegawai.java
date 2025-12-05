package pert9.pegawaitetapnonkontrak;

public class MainPegawai {
    public static void main(String[] args) {

        // Membuat objek Pegawai Tetap
        PegawaiTetap pt = new PegawaiTetap("Fauzi", 5000000, 1500000);

        // Membuat objek Pegawai Kontrak
        PegawaiKontrak pk = new PegawaiKontrak("Rizki", 4000000, 500000, 3);

        // Output
        System.out.println("=== Pegawai Tetap ===");
        System.out.println("Nama        : " + pt.getNama());
        System.out.println("Gaji Akhir  : " + pt.hitungGaji());

        System.out.println("\n=== Pegawai Kontrak ===");
        System.out.println("Nama        : " + pk.getNama());
        System.out.println("Gaji Akhir  : " + pk.hitungGaji());
    }
}


