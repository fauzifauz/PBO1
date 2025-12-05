package pert6.soallevel1; // Menunjukkan bahwa class ini berada di package pert6.soallevel1

public class MainMahasiswa {
    public static void main(String[] args) {

        // Membuat objek Mahasiswa baru bernama mhs1 dengan nilai awal
        Mahasiswa mhs1 = new Mahasiswa("Fauzi Rizki", "123456789", 3.85);

        // Menampilkan data awal mahasiswa
        System.out.println("=== Data Mahasiswa ===");
        System.out.println("Nama : " + mhs1.getNama()); // Menampilkan nama mahasiswa
        System.out.println("NIM  : " + mhs1.getNim());  // Menampilkan NIM mahasiswa
        System.out.println("IPK  : " + mhs1.getIpk());  // Menampilkan IPK mahasiswa

        // Mengubah data mahasiswa menggunakan setter
        mhs1.setNama("Rizki Maulana");  // Mengubah nama mahasiswa
        mhs1.setNim("987654321");       // Mengubah NIM mahasiswa
        mhs1.setIpk(3.95);              // Mengubah IPK mahasiswa

        // Menampilkan data mahasiswa setelah diubah
        System.out.println("=== Data Mahasiswa Setelah Diubah ===");
        System.out.println("Nama : " + mhs1.getNama()); // Menampilkan nama baru
        System.out.println("NIM  : " + mhs1.getNim());  // Menampilkan NIM baru
        System.out.println("IPK  : " + mhs1.getIpk());  // Menampilkan IPK baru
    }
}
