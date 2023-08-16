package org.adtado.verticalcode.effective.chap2.item5;

import java.util.function.Supplier;

public class BusService {
    private final Supplier<? extends Bus> busFactory;

    public BusService(Supplier<? extends Bus> busFactory) {
        this.busFactory = busFactory;
    }

    public String getBusInfo() {
        return this.busFactory.get().getBusInfo();
    }
}
