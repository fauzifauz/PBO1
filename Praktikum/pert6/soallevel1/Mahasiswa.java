package pert6.soallevel1; // Menyatakan bahwa class ini berada di package pert6.soallevel1

public class Mahasiswa {
    // Deklarasi atribut private (hanya bisa diakses di dalam class ini)
    private String nama;
    private String nim;
    private double ipk;

    // Constructor untuk menginisialisasi objek Mahasiswa
    public Mahasiswa(String nama, String nim, double ipk) {
        this.nama = nama; // Mengatur nilai atribut nama
        this.nim = nim;   // Mengatur nilai atribut NIM
        this.ipk = ipk;   // Mengatur nilai atribut IPK
    }

    // Setter method untuk mengubah nilai nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Setter method untuk mengubah nilai NIM
    public void setNim(String nim) {
        this.nim = nim;
    }

    // Setter method untuk mengubah nilai IPK
    public void setIpk(double ipk) {
        this.ipk = ipk;
    }

    // Getter method untuk mengambil nilai nama
    public String getNama() {
        return nama;
    }

    // Getter method untuk mengambil nilai NIM
    public String getNim() {
        return nim;
    }

    // Getter method untuk mengambil nilai IPK
    public double getIpk() {
        return ipk;
    }
}
