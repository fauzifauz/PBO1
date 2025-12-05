package pert8.personsebagaisuperclassfruitsebagaisuperclasslain;

// FruitSeller turunan dari Person, bukan dari Fruit
public class FruitSeller extends Person {
    private String fruitSold; // buah yang dijual

    public FruitSeller(String name, String fruitSold) {
        super(name);
        this.fruitSold = fruitSold;
    }

    public void sell() {
        System.out.println(name + " sells " + fruitSold);
    }
}
