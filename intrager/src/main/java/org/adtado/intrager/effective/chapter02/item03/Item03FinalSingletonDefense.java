package org.adtado.intrager.effective.chapter02.item03;

public class Item03FinalSingletonDefense {
    public static final Item03FinalSingletonDefense INSTANCE = new Item03FinalSingletonDefense();
    private Item03FinalSingletonDefense() {
        if(INSTANCE != null) {
            throw new RuntimeException("You can create only one INSTANCE of static final field");
        }
    }
}
