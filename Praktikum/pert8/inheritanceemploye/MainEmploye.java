package pert8.inheritanceemploye;

public class MainEmploye {
    public static void main(String[] args) {

        // Membuat objek Manager
        Manager mgr = new Manager("Fauzi", 8000000, 5);

        // Membuat objek Developer
        Developer dev = new Developer("Rizki", 6000000, "Java");

        System.out.println("=== Manager ===");
        mgr.showInfo();

        System.out.println("\n=== Developer ===");
        dev.showInfo();
    }
}
