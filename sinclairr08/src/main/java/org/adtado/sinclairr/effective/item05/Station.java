package org.adtado.sinclairr.effective.item05;

import java.util.List;

public abstract class Station {
    int stationId;
    String stationName;
    public Station(int stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }
    abstract String getStationInfo();
}
