package com.saltatorv.polaris.flash.cards.domain.shared;

import com.saltatorv.polaris.flash.cards.domain.Generated;

import java.util.UUID;

@Generated
class Id {
    private final UUID id;

    Id(UUID id) {
        this.id = id;
    }

    public String getId() {
        return id.toString();
    }
}
