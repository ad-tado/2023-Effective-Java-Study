package org.adtado.sinclairr.effective.item05;

import java.util.List;

public class TransferStation extends Station {
    private List<String> lineNames;
    private List<Integer> lineIndices;

    public TransferStation(int stationId, String stationName, List<String> lineNames, List<Integer> lineIndices) {
        super(stationId, stationName);
        this.lineNames = lineNames;
        this.lineIndices = lineIndices;
    }

    @Override
    public String getStationInfo() {
        String result = "이번 역은 ";

        for (String lineName : lineNames) {
            if (lineName.equals(lineNames.get(0))) {
                result += lineName;
                continue;
            }
            result += ", " + lineName;
        }

        result += " 호선이 교차하는 " + this.stationName + "역 입니다";
        return result;
    }
}
