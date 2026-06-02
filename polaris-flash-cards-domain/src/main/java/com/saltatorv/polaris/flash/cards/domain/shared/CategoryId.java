package com.saltatorv.polaris.flash.cards.domain.shared;

public class CategoryId extends Id {
    public CategoryId(String id) {
        super(id);
    }

    private CategoryId() {
        super();
    }

    public static CategoryId generate() {
        return new CategoryId();
    }
}
