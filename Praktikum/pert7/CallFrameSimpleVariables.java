package pert7;

public class CallFrameSimpleVariables {
    // method untuk menghitung kuadrat dari nilai n
    public static double square(double n) {
        double temp; // variabel sementara
        temp = n * n; // operasi matematika kuadrat (lingkaran HC: operasi aritmatika)
        return temp; // mengembalikan hasil ke pemanggil
    }

    public static void main(String args[]) {
        double x = 4.5; // variabel input
        double y; // variabel hasil

        y = square(x); // memanggil method square dan menyimpan hasilnya di y
        System.out.println("Square of " + x + " is " + y); // tampilkan hasil
    }
}