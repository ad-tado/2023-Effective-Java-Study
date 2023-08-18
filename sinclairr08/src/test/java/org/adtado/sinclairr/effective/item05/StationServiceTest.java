package org.adtado.sinclairr.effective.item05;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

class StationServiceTest {
    @DisplayName("환승역 주입 확인")
    @Test
    void inject_transfer_test() {
        TransferStation miguem = new TransferStation(
                178,
                "미금",
                List.of("수인분당", "신분당"),
                List.of(231, 13)
        );

        Supplier<? extends Station> transferStationSupplier = new Supplier<TransferStation>() {
            @Override
            public TransferStation get() {
                return miguem;
            }
        };

        StationService stationService = new StationService(transferStationSupplier);

        Assertions.assertThat(stationService.getStationInfo())
                .isEqualTo("이번 역은 수인분당, 신분당 호선이 교차하는 미금역 입니다");
    }

    @DisplayName("일반역 주입 확인")
    @Test
    void inject_normal_test() {
        NormalStation sanghyun = new NormalStation(
                289,
                "상현",
                "신분당",
                17
        );

        Supplier<? extends Station> transferStationSupplier = new Supplier<NormalStation>() {
            @Override
            public NormalStation get() {
                return sanghyun;
            }
        };

        StationService stationService = new StationService(transferStationSupplier);

        Assertions.assertThat(stationService.getStationInfo())
                .isEqualTo("이번 역은 신분당 호선의 17 번째 역인 상현역 입니다");
    }
}