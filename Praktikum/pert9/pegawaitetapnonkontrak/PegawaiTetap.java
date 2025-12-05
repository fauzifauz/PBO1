package pert9.pegawaitetapnonkontrak;

// PegawaiTetap adalah turunan dari Pegawai
public class PegawaiTetap extends Pegawai {
    private double tunjangan;  // atribut khusus pegawai tetap

    public PegawaiTetap(String nama, double gajiPokok, double tunjangan) {
        super(nama, gajiPokok);  // panggil konstruktor parent
        this.tunjangan = tunjangan;
    }

    // Getter & Setter
    public double getTunjangan() { 
        return tunjangan; 
    }

    public void setTunjangan(double tunjangan) { 
        this.tunjangan = tunjangan; 
    }

    // Override metode hitungGaji
    @Override
    public double hitungGaji() {
        return getGajiPokok() + tunjangan; 
    }
}


