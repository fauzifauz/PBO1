package pert11.SistemLogindenganCustomException;

import java.util.Scanner;

public class Soal4LoginSystem {

    public static void login(String userInput, String passInput)
            throws UserNotFoundException, PasswordSalahException {

        String userDB = "admin";
        String passDB = "12345";

        // Jika username salah → lempar exception
        if (!userInput.equals(userDB)) {
            throw new UserNotFoundException("Username tidak ditemukan!");
        }

        // Jika password salah → lempar exception
        if (!passInput.equals(passDB)) {
            throw new PasswordSalahException("Password salah!");
        }

        System.out.println("Login berhasil! Selamat datang " + userDB);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        String u = sc.nextLine();

        System.out.print("Password: ");
        String p = sc.nextLine();

        try {
            login(u, p);
        }

        catch (UserNotFoundException e) {
            System.out.println("Login gagal: " + e.getMessage());
        }

        catch (PasswordSalahException e) {
            System.out.println("Login gagal: " + e.getMessage());
        }

        finally {
            sc.close();
        }
    }
}

