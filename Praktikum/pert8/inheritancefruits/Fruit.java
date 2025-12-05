package pert8.inheritancefruits;

// Superclass Fruit
public class Fruit {
    protected String name; // nama buah

    public Fruit(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("Fruit: " + name);
    }
}
