package org.adtado.verticalcode.effective.chap2.item5;

public abstract class Bus {
    String carNumber;

    public Bus(String carNumber) {
        this.carNumber = carNumber;
    }

    abstract String getBusInfo();
}
