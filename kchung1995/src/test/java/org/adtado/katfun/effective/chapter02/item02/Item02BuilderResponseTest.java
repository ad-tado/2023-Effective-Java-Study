package org.adtado.katfun.effective.chapter02.item02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Item02BuilderResponseTest {
    @DisplayName("빌더 패턴으로 인스턴스를 생성한다")
    @Test
    void telescopingConstructorPatternTest() {
        // given

        // when
        Item02BuilderResponse bochoResult = new Item02BuilderResponse.Builder(true, false, false, false, false, false, false, false)
                .isOffice(true).isRemote(false).isPangyo(true).isJeongja(false)
                .build();
        Item02BuilderResponse vaticanCodeResult = new Item02BuilderResponse.Builder(false, false, false, false, false, false, false, true)
                .isPangyo(true).isJeongja(false)
                .build();
        Item02BuilderResponse bruceHanResult = new Item02BuilderResponse.Builder(false, false, false, false, true, false, false, false)
                .build();

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