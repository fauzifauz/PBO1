package pert9.overidingluasbangundata;

// Segitiga turunan BangunDatar
public class Segitiga extends BangunDatar {
    private double alas, tinggi;

    public Segitiga(double a, double t) {
        this.alas = a;
        this.tinggi = t;
    }

    @Override
    public double luas() {
        return 0.5 * alas * tinggi; // rumus luas segitiga
    }
}

