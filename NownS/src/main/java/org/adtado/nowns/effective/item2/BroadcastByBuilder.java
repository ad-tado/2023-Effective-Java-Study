package org.adtado.nowns.effective.item2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@ToString
public class BroadcastByBuilder {
    private final Long id;
    private String title;
    private String coverImageUrl;
    private Instant createdTime;
    private Instant updatedTime;

    public static class Builder {
        private final Long id;
        private final String title;

        private String coverImageUrl = "";
        private Instant createdTime = Instant.now();
        private Instant updatedTime = Instant.now();

        public Builder(Long id, String title) {
            this.id = id;
            this.title = title;
        }

        public Builder coverImageUrl(String coverImageUrl) {
            this.coverImageUrl = coverImageUrl;
            return this;
        }

        public Builder createdTime(Instant createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public Builder updatedTime(Instant updatedTime) {
            this.updatedTime = updatedTime;
            return this;
        }

        public BroadcastByBuilder build() {
            return new BroadcastByBuilder(this);
        }
    }

    private BroadcastByBuilder(Builder builder) {
        id = builder.id;
        title = builder.title;
        coverImageUrl = builder.coverImageUrl;
        createdTime = builder.createdTime;
        updatedTime = builder.updatedTime;
    }
}
