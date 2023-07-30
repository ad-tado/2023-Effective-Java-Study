package org.adtado.nowns.effective.item2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Instant;

@Slf4j
class BroadcastConstructorTest {

    private BroadcastByTelescope broadcastByTelescope;
    private BroadcastByJavaBeans broadcastByJavaBeans;
    private BroadcastByBuilder broadcastByBuilder;

    private Long id = 1L;
    private String title = "title";


    private class TelescopeThread extends Thread {
        @Override
        public void run() {
            broadcastByTelescope = new BroadcastByTelescope(id, title);
        }
    }

    private class JavaBeansThread extends Thread {
        @Override
        public void run() {
            broadcastByJavaBeans = new BroadcastByJavaBeans();
            broadcastByJavaBeans.setId(id);
            broadcastByJavaBeans.setTitle(title);
            broadcastByJavaBeans.setCoverImageUrl("");
            broadcastByJavaBeans.setCreatedTime(Instant.now());
            broadcastByJavaBeans.setUpdatedTime(Instant.now());
        }
    }

    private class BuilderThread extends Thread {
        @Override
        public void run() {
            broadcastByBuilder = new BroadcastByBuilder.Builder(id, title).build();
        }
    }

    @Test
    void runThread() {
        Thread telescope = new TelescopeThread();
        Thread javaBeans = new JavaBeansThread();
        Thread builder = new BuilderThread();

        telescope.start();
        log.info("telescope: {}", broadcastByTelescope);
        log.info("telescope: {}", broadcastByTelescope);
        log.info("telescope: {}", broadcastByTelescope);
        log.info("telescope: {}", broadcastByTelescope);
        log.info("telescope: {}", broadcastByTelescope);

        javaBeans.start();
        log.info("javaBeans: {}", broadcastByJavaBeans);
        log.info("javaBeans: {}", broadcastByJavaBeans);
        log.info("javaBeans: {}", broadcastByJavaBeans);
        log.info("javaBeans: {}", broadcastByJavaBeans);
        log.info("javaBeans: {}", broadcastByJavaBeans);

        builder.start();
        log.info("builder: {}", broadcastByBuilder);
        log.info("builder: {}", broadcastByBuilder);
        log.info("builder: {}", broadcastByBuilder);
        log.info("builder: {}", broadcastByBuilder);
        log.info("builder: {}", broadcastByBuilder);

    }
}
