package org.adtado.katfun.effective.chapter02.item02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Item02TelescopingResponseTest {
    @DisplayName("점층적 생성자 패턴으로 인스턴스를 생성한다")
    @Test
    void telescopingConstructorPatternTest() {
        // given

        // when
        Item02TelescopingResponse bochoResult = new Item02TelescopingResponse(true, false, false, false, false, false, false, false, true, false, false, true);
        Item02TelescopingResponse vaticanCodeResult = new Item02TelescopingResponse(false, false, false, false, false, false, false, true, true, false);
        Item02TelescopingResponse bruceHanResult = new Item02TelescopingResponse(false, false, false, false, true, false, false, false);

        // then
        assertThat(bochoResult.getBocho()).isTrue();
        assertThat(bochoResult.getPangyo()).isTrue();
        assertThat(bochoResult.getOffice()).isTrue();
        assertThat(vaticanCodeResult.getBachikalCode()).isTrue();
        assertThat(vaticanCodeResult.getPangyo()).isTrue();
        assertThat(vaticanCodeResult.getRemote()).isFalse();
        assertThat(bruceHanResult.getBruceHan()).isTrue();
        assertThat(bruceHanResult.getPangyo()).isFalse();
        assertThat(bruceHanResult.getOffice()).isFalse();
    }
}