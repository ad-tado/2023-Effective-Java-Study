package org.adtado.nowns.effective.item1;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BroadcastView {
    private String title;
    private String coverImageUrl;
    private Long createdTime;
    private Long updatedTime;

    public static BroadcastView from(Broadcast broadcast) {
        return new BroadcastView(broadcast.getTitle(),
                                 broadcast.getCoverImageUrl(),
                                 broadcast.getCreatedTime().toEpochMilli(),
                                 broadcast.getUpdatedTime().toEpochMilli());
    }
}
