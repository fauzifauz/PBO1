package pert9.inheritancehewan;

// Kucing mewarisi kelas Hewan
public class Kucing extends Hewan {

    // Konstruktor Kucing
    public Kucing(String nama, int usia) {
        super(nama, usia); // panggil konstruktor parent
    }

    // Method khusus kucing
    public void bersuara() {
        System.out.println("Meong!");
    }
}

