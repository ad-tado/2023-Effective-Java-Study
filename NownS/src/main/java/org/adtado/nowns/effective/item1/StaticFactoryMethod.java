package org.adtado.nowns.effective.item1;

import lombok.val;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class StaticFactoryMethod {


    private void createByStaticFactoryMethod() {
        Broadcast broadcast = buildBroadcast();
        BroadcastView view = BroadcastView.from(broadcast); // good!
    }

    private void createByConstructor() {
        Broadcast broadcast = buildBroadcast();
        BroadcastView view = new BroadcastView(broadcast.getTitle(),
                broadcast.getCoverImageUrl(),
                broadcast.getCreatedTime().toEpochMilli(),
                broadcast.getUpdatedTime().toEpochMilli()); // bad... (내부 구현 확인 필요)
    }

    private Broadcast buildBroadcast() {
        val id = 1L;
        val title = "TEST LIVE!";
        val coverImageUrl = "https://avatars.githubusercontent.com/u/32464833?s=96&v=4";
        val createdTime = Instant.now();
        val updatedTime = Instant.now();

        Broadcast broadcast = new Broadcast();
        broadcast.setId(1L);
        broadcast.setTitle("TEST LIVE!");
        broadcast.setCoverImageUrl(coverImageUrl);
        broadcast.setCreatedTime(createdTime);
        broadcast.setUpdatedTime(updatedTime);

        return broadcast;
    }

}
