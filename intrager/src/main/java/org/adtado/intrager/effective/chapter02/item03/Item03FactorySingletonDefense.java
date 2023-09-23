package org.adtado.intrager.effective.chapter02.item03;

public class Item03FactorySingletonDefense {
    private static final Item03FactorySingletonDefense INSTANCE = new Item03FactorySingletonDefense();

    private Item03FactorySingletonDefense() {
        if(INSTANCE != null)
            throw new RuntimeException("You can create only one INSTANCE of static factory method");
    }

    public static Item03FactorySingletonDefense getInstance() {
        return INSTANCE;
    }
}
