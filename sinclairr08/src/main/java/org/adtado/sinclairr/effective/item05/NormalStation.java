package org.adtado.sinclairr.effective.item05;

public class NormalStation extends Station{
    private String lineName;
    private int lineIndex;

    public NormalStation(int stationId, String stationName, String lineName, int lineIndex) {
        super(stationId, stationName);
        this.lineName = lineName;
        this.lineIndex = lineIndex;
    }

    @Override
    String getStationInfo() {
        String result = String.format("이번 역은 %s 호선의 %s 번째 역인 %s역 입니다",
                this.lineName,
                this.lineIndex,
                this.stationName
        );
        return result;
    }
}
