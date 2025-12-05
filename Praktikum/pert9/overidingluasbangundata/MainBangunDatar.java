package pert9.overidingluasbangundata;

public class MainBangunDatar {
    public static void main(String[] args) {

        BangunDatar p = new Persegi(5);
        BangunDatar l = new Lingkaran(7);
        BangunDatar s = new Segitiga(6, 4);

        System.out.println("Luas Persegi   : " + p.luas());
        System.out.println("Luas Lingkaran : " + l.luas());
        System.out.println("Luas Segitiga  : " + s.luas());
    }
}

