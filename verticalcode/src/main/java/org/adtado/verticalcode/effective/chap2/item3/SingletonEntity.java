package org.adtado.verticalcode.effective.chap2.item3;

public class SingletonEntity {
    private static final SingletonEntity INSTANCE = new SingletonEntity();

    private SingletonEntity() {}

    public static SingletonEntity getInstance() {
        return INSTANCE;
    }
}
