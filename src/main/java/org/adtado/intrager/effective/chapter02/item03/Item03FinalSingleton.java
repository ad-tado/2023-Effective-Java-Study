package org.adtado.intrager.effective.chapter02.item03;

public class Item03FinalSingleton {
    public static final Item03FinalSingleton INSTANCE = new Item03FinalSingleton();
    private Item03FinalSingleton() {
        System.out.println("Item03FinalSingleton basic constructor");
    }
}
