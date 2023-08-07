package org.adtado.verticalcode.effective.chap2;

import org.adtado.verticalcode.effective.chap2.item5.Bus;
import org.adtado.verticalcode.effective.chap2.item5.BusService;
import org.adtado.verticalcode.effective.chap2.item5.CityBus;
import org.adtado.verticalcode.effective.chap2.item5.ExpressBus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Item5Test {

    @Test
    void 시내버스_테스트() {
        List<String> stops = new ArrayList<>();
        stops.add("잠실");
        stops.add("대치동");
        CityBus cityBus = new CityBus(340, "98소2934", "강동차고지", "강남역", stops);

        Supplier<? extends Bus> busSupplier = new Supplier<Bus>() {
            @Override
            public Bus get() {
                return cityBus;
            }
        };

        BusService busService = new BusService(busSupplier);

        Assertions.assertThat(busService.getBusInfo()).isEqualTo("이 버스는 340 번 버스입니다.\n" +
                "강동차고지에서 강남역(으)로 가는 버스입니다.");
    }

    @Test
    void 고속버스_테스트() {
        ExpressBus expressBus = new ExpressBus("98소2934", "고속터미널", "부산터미널");

        Supplier<? extends Bus> busSupplier = new Supplier<Bus>() {
            @Override
            public Bus get() {
                return expressBus;
            }
        };

        BusService busService = new BusService(busSupplier);

        Assertions.assertThat(busService.getBusInfo()).isEqualTo("이 버스는 고속터미널에서 부산터미널로 가는 고속버스입니다.");
    }
}
