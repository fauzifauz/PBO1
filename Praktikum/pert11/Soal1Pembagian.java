package pert11;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Soal1Pembagian {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            // Meminta input angka dari user
            System.out.print("Masukkan angka pertama : ");
            int a = sc.nextInt();

            System.out.print("Masukkan angka kedua   : ");
            int b = sc.nextInt();

            // Operasi pembagian
            int hasil = a / b;
            System.out.println("Hasil pembagian = " + hasil);
        }

        // Menangani pembagian dengan nol
        catch (ArithmeticException e) {
            System.out.println("Error: Tidak bisa membagi dengan nol!");
        }

        // Menangani input yang bukan angka
        catch (InputMismatchException e) {
            System.out.println("Error: Input harus berupa angka!");
        }

        finally {
            sc.close();
        }
    }
}
