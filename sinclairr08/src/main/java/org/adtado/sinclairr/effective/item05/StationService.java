package org.adtado.sinclairr.effective.item05;

import java.util.function.Supplier;

public class StationService {
    private Supplier<? extends Station> stationFactory;

    public StationService(Supplier<? extends Station> stationFactory) {
        this.stationFactory = stationFactory;
    }

    public String getStationInfo() {
        return this.stationFactory.get().getStationInfo();
    }
}
