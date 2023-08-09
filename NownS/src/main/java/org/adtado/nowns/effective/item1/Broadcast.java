package org.adtado.nowns.effective.item1;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Broadcast {
    private Long id;
    private String title;
    private String coverImageUrl;
    private Instant createdTime;
    private Instant updatedTime;
}
