package pert8.inheritanceemploye;

// Developer adalah turunan lain dari Employee
public class Developer extends Employee {
    private String programmingLanguage; // bahasa pemrograman

    public Developer(String name, double salary, String lang) {
        super(name, salary);
        this.programmingLanguage = lang;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Language: " + programmingLanguage);
    }
}
