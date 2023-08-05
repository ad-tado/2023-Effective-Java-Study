package org.adtado.katfun.effective.chapter02.item03;

public class Item03FinalSingletonDefense {
    public static final Item03FinalSingletonDefense instance = new Item03FinalSingletonDefense();

    private Item03FinalSingletonDefense() {
        if (instance != null)
            throw new RuntimeException("Cannot create second instance of Item03FinalSingletonDefense.");
    }

    public static Item03FinalSingletonDefense getInstance() {
        return instance;
    }
}
