package org.adtado.intrager.effective.chapter02.item03;

public class Item03FactorySingleton {
    private static final Item03FactorySingleton INSTANCE = new Item03FactorySingleton();

    private Item03FactorySingleton() {
        System.out.println("Item03FactorySingleton basic constructor");
    }

    public static Item03FactorySingleton getInstance() {
        return INSTANCE;
    }
}
