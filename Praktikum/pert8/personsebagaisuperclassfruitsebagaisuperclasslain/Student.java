package pert8.personsebagaisuperclassfruitsebagaisuperclasslain;

// Student turunan dari Person
public class Student extends Person {

    public Student(String name) {
        super(name); // panggil konstruktor parent
    }

    public void study() {
        System.out.println(name + " is studying.");
    }
}
