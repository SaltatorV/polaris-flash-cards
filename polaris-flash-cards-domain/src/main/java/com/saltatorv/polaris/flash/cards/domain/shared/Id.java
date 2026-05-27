package com.saltatorv.polaris.flash.cards.domain.shared;

import com.saltatorv.polaris.flash.cards.domain.Generated;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Generated
class Id {
    private final UUID id;

    Id() {
        this.id = generateUUIDv7();
    }

    public Id(String id) {
        this.id = UUID.fromString(id);
    }

    private UUID generateUUIDv7() {
        long timestamp = System.currentTimeMillis();
        long mostSigBits = (timestamp << 16) | 0x7000;
        long leastSigBits = ThreadLocalRandom.current().nextLong();
        return new UUID(mostSigBits, leastSigBits);
    }

    public String getId() {
        return id.toString();
    }
}
