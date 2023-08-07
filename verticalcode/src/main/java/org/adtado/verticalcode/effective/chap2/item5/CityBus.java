package org.adtado.verticalcode.effective.chap2.item5;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CityBus extends Bus {
    private int busNumber;
    private String startPoint;
    private String endPoint;
    private List<String> stops;

    public CityBus(int busNumber, String carNumber, String startPoint, String endPoint, List<String> stops) {
        super(carNumber);
        this.busNumber = busNumber;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.stops = stops;
    }

    @Override
    String getBusInfo() {
        return "이 버스는 " + busNumber + " 번 버스입니다.\n" +
                startPoint + "에서 " + endPoint + "(으)로 가는 버스입니다.";
    }
}
