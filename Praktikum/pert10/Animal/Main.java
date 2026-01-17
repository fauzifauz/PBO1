package pert10.Animal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Animal a1 = new Animal();
        Animal a2 = new Cat();
        Animal a3 = new Tiger();
        Animal a4 = new Dog(); 

        System.out.println();
        a1.animalSound();
        a2.animalSound();
        a3.animalSound();
        a4.animalSound();

        System.out.println();

        List<Animal> daftarHewan = new ArrayList<>();
        daftarHewan.add(a1);
        daftarHewan.add(a2);
        daftarHewan.add(a3);
        daftarHewan.add(a4);

        for (Animal h : daftarHewan) {
            h.animalSound();
        }
    }
}
