package pert11;

import java.util.Scanner;

public class Soal2ArrayIndex {
    public static void main(String[] args) {

        int[] data = {10, 20, 30, 40, 50};
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Masukkan index array (0-4): ");
            int index = sc.nextInt();

            // Mencetak nilai pada index yang dipilih
            System.out.println("Isi array pada index " + index + " = " + data[index]);
        }

        // Jika index tidak valid
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Index yang dimasukkan tidak valid!");
        }

        finally {
            sc.close();
        }
    }
}

