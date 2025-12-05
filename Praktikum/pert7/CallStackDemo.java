package pert7;

public class CallStackDemo {
    public static void m2() {
        System.out.println("Starting m2"); // mulai eksekusi method m2
        System.out.println("m2 calling m3"); // memanggil method m3
        m3(); // memanggil m3
        m4(); // memanggil m4
        System.out.println("Leaving m2"); // selesai dari m2
        return;
    }

    public static void m3() {
        System.out.println("Starting m3"); // mulai eksekusi method m3
        System.out.println("Leaving m3");  // selesai dari m3
        return;
    }

    public static void m4() {
        System.out.println("Starting m4"); // mulai eksekusi method m4
        System.out.println("Leaving m4");  // selesai dari m4
        return;
    }

    public static void main(String args[]) {
        System.out.println("Starting main"); // awal program utama
        System.out.println("main calling m2"); // memanggil method m2
        m2(); // jalankan m2
        System.out.println("Leaving main"); // program utama selesai
    }
}
