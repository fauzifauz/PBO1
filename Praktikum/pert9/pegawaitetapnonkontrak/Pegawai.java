package pert9.pegawaitetapnonkontrak;

// Superclass Pegawai
public class Pegawai {
    private String nama;        // atribut private
    private double gajiPokok;   // atribut private

    // Constructor
    public Pegawai(String nama, double gajiPokok) {
        this.nama = nama;
        this.gajiPokok = gajiPokok;
    }

    // Getter & Setter
    public String getNama() { 
        return nama; 
    }

    public void setNama(String nama) { 
        this.nama = nama; 
    }

    public double getGajiPokok() { 
        return gajiPokok; 
    }

    public void setGajiPokok(double gajiPokok) { 
        this.gajiPokok = gajiPokok; 
    }

    // Method menghitung gaji (dapat dioverride)
    public double hitungGaji() {
        return gajiPokok; // default: gaji pokok saja
    }
}

