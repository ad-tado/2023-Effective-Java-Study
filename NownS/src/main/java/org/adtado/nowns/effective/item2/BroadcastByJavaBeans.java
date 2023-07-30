package org.adtado.nowns.effective.item2;

import lombok.*;

import java.time.Instant;

@ToString
@Setter
@NoArgsConstructor
public class BroadcastByJavaBeans {
    private Long id;
    private String title;
    private String coverImageUrl;
    private Instant createdTime;
    private Instant updatedTime;
}
