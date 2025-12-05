package pert9.overidingluasbangundata;

// Persegi turunan dari BangunDatar
public class Persegi extends BangunDatar{
    private double sisi;

    public Persegi(double sis) {
        this.sisi = sisi;
    }

    @Override
    public double luas() {
        return sisi * sisi; //rumus luas persegi
    }
}
