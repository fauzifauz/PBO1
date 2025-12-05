package pert9.overidingluasbangundata;

// Lingkaran turunan BangunDatar
public class Lingkaran extends BangunDatar {
    private double r;

    public Lingkaran(double r) {
        this.r = r;
    }

    @Override
    public double luas() {
        return 3.14 * r * r; // rumus luas lingkaran
    }
}

