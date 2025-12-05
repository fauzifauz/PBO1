package pert8.personsebagaisuperclassfruitsebagaisuperclasslain;

public class MainPerson {
    public static void main(String[] args) {

        Student s = new Student("Fauzi");
        FruitSeller fs = new FruitSeller("Rizki", "Bananas");

        s.introduce();
        s.study();

        System.out.println();

        fs.introduce();
        fs.sell();
    }
}
