package org.adtado.verticalcode.effective.chap2.item5;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressBus extends Bus{

    private String startPoint;
    private String endPoint;

    public ExpressBus(String carNumber, String startPoint, String endPoint) {
        super(carNumber);
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    @Override
    String getBusInfo() {
        return "이 버스는 " +
                startPoint + "에서 " +
                endPoint + "로 가는 고속버스입니다.";
    }
}
