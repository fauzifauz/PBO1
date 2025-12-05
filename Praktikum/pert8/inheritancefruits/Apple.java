package pert8.inheritancefruits;

// Apple adalah turunan Fruit
public class Apple extends Fruit {

    public Apple() {
        super("Apple"); // panggil konstruktor parent
    }

    public void taste() {
        System.out.println("Taste: Sweet and crispy");
    }
}
