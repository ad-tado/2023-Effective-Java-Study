package org.adtado.nowns.effective.item2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@ToString
@Getter
@AllArgsConstructor
public class BroadcastByTelescope {
    private Long id;
    private String title;
    private String coverImageUrl;
    private Instant createdTime;
    private Instant updatedTime;

    public BroadcastByTelescope(Long id, String title, String coverImageUrl) {
        this(id, title, coverImageUrl, Instant.now(), Instant.now());
    }

    public BroadcastByTelescope(Long id, String title) {
        this(id, title, "");
    }
}
