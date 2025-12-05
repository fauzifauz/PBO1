package pert9.pegawaitetapnonkontrak;

// PegawaiKontrak adalah turunan dari Pegawai
public class PegawaiKontrak extends Pegawai {
    private double bonusPerProyek;   // bonus per proyek
    private int jumlahProyek;        // jumlah proyek diselesaikan

    public PegawaiKontrak(String nama, double gajiPokok, double bonusPerProyek, int jumlahProyek) {
        super(nama, gajiPokok);
        this.bonusPerProyek = bonusPerProyek;
        this.jumlahProyek = jumlahProyek;
    }

    // Getter & Setter
    public double getBonusPerProyek() { 
        return bonusPerProyek; 
    }

    public void setBonusPerProyek(double bonusPerProyek) { 
        this.bonusPerProyek = bonusPerProyek; 
    }

    public int getJumlahProyek() { 
        return jumlahProyek; 
    }

    public void setJumlahProyek(int jumlahProyek) { 
        this.jumlahProyek = jumlahProyek; 
    }

    // Override metode hitungGaji
    @Override
    public double hitungGaji() {
        return getGajiPokok() + (bonusPerProyek * jumlahProyek);
    }
}