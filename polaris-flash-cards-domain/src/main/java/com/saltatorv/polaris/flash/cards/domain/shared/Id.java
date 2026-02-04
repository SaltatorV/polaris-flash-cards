package com.saltatorv.polaris.flash.cards.domain.shared;

import com.saltatorv.polaris.flash.cards.domain.Generated;

@Generated
class Id<T> {
    private final T id;

    Id(T id) {
        this.id = id;
    }

    public String getId() {
        return id.toString();
    }
}
