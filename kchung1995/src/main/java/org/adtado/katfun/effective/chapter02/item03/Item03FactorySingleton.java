package org.adtado.katfun.effective.chapter02.item03;

public class Item03FactorySingleton {
    private static final Item03FactorySingleton instance = new Item03FactorySingleton();
    private Item03FactorySingleton() { }

    public static Item03FactorySingleton getInstance() { return instance; }
}
