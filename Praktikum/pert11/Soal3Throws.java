package pert11;

import java.util.Scanner;

public class Soal3Throws {

    // Method yang melempar NumberFormatException
    public static int bacaAngka() throws NumberFormatException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan angka: ");

        String input = sc.nextLine();   // Membaca input berupa string
        return Integer.parseInt(input); // Bisa melempar NumberFormatException
    }

    public static void main(String[] args) {

        try {
            int angka = bacaAngka(); // memanggil method yang punya throws
            System.out.println("Angka yang dimasukkan: " + angka);
        }

        catch (NumberFormatException e) {
            System.out.println("Error: Input bukan angka yang valid!");
        }
    }
}

