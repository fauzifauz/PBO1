package pert8.inheritancefruits;

public class MainFruit {
    public static void main(String[] args) {
        Apple a = new Apple();
        Banana b = new Banana();

        a.show();
        a.taste();

        System.out.println();

        b.show();
        b.taste();
    }
}
