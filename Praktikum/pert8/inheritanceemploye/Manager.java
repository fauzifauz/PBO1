package pert8.inheritanceemploye;

// Manager adalah turunan dari Employee
public class Manager extends Employee {
    private int teamSize; // jumlah anggota tim

    public Manager(String name, double salary, int teamSize) {
        super(name, salary); // panggil konstruktor parent
        this.teamSize = teamSize;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Team Size: " + teamSize);
    }
}

