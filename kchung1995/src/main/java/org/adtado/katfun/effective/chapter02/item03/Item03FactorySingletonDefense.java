package org.adtado.katfun.effective.chapter02.item03;

public class Item03FactorySingletonDefense {
    private static final Item03FactorySingletonDefense instance = new Item03FactorySingletonDefense();

    private Item03FactorySingletonDefense() {
        if (instance != null)
            throw new RuntimeException("Cannot create second instance of Item03FinalSingletonDefense.");
    }

    public static Item03FactorySingletonDefense getInstance() {
        return instance;
    }
}
