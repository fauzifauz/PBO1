package pert8.inheritanceemploye;

// Superclass Employee
public class Employee {
    protected String name;   // atribut nama karyawan
    protected double salary; // atribut gaji

    // Konstruktor
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Method tampil data
    public void showInfo() {
        System.out.println("Name  : " + name);
        System.out.println("Salary: " + salary);
    }
}
