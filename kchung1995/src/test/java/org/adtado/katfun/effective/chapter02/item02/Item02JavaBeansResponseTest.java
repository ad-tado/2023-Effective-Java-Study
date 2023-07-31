package org.adtado.katfun.effective.chapter02.item02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

class Item02JavaBeansResponseTest {
    @DisplayName("자바빈즈 패턴으로 인스턴스를 생성한다")
    @Test
    void javaBeansPatternTest() {
        // given
        Item02JavaBeansResponse bochoResult = new Item02JavaBeansResponse();
        Item02JavaBeansResponse vaticanCodeResult = new Item02JavaBeansResponse();
        Item02JavaBeansResponse bruceHanResult = new Item02JavaBeansResponse();

        // when
        bochoResult.setBocho(true);
        bochoResult.setKatfun(false);
        bochoResult.setWuki(false);
        bochoResult.setChak(false);
        bochoResult.setBruceHan(false);
        bochoResult.setHarryPatta(false);
        bochoResult.setIvai(false);
        bochoResult.setBachikalCode(false);
        bochoResult.setPangyo(true);
        bochoResult.setJeongja(false);
        bochoResult.setRemote(false);
        bochoResult.setOffice(true);

        vaticanCodeResult.setBocho(false);
        vaticanCodeResult.setKatfun(false);
        vaticanCodeResult.setWuki(false);
        vaticanCodeResult.setChak(false);
        vaticanCodeResult.setBruceHan(false);
        vaticanCodeResult.setHarryPatta(false);
        vaticanCodeResult.setIvai(false);
        vaticanCodeResult.setBachikalCode(true);
        vaticanCodeResult.setPangyo(true);
        vaticanCodeResult.setJeongja(false);

        bruceHanResult.setBocho(false);
        bruceHanResult.setKatfun(false);
        bruceHanResult.setWuki(false);
        bruceHanResult.setChak(false);
        bruceHanResult.setBruceHan(true);
        bruceHanResult.setHarryPatta(false);
        bruceHanResult.setIvai(false);
        bruceHanResult.setBachikalCode(false);

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

    @DisplayName("자바빈즈 패턴으로 인스턴스를 생성한다")
    @Test
    void javaBeansMultiThreadTest() throws InterruptedException {
        // given
        Item02JavaBeansResponse bochoResult = new Item02JavaBeansResponse();
        bochoResult.setBocho(true);
        bochoResult.setKatfun(false);
        bochoResult.setWuki(false);
        bochoResult.setChak(false);
        bochoResult.setBruceHan(false);
        bochoResult.setHarryPatta(false);
        bochoResult.setIvai(false);
        bochoResult.setBachikalCode(false);
        bochoResult.setPangyo(true);
        bochoResult.setJeongja(false);
        bochoResult.setRemote(false);
        bochoResult.setOffice(true);

        final Logger log = Logger.getGlobal();

        // when
        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            int finalI = i + 1;
            service.execute(() -> {
                bochoResult.setAccessCount(bochoResult.getAccessCount() + 1);
                log.info("thread no: " + finalI + ", access count: " + bochoResult.getAccessCount());
            });
            latch.countDown();
        }

        // then
        assertThat(numberOfThreads).isEqualTo(bochoResult.getAccessCount());
    }
}