package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;

public class Category {
    private static final int MAX_AVAILABLE_DEPTH = 4;

    private final CategoryId id;
    private final int depth;
    private final String categoryName;
    private final CategoryId parent;

    public Category(String categoryName) {
        id = CategoryId.generate();
        depth = 1;
        parent = null;
        this.categoryName = categoryName;
    }

    private Category(int depth, String categoryName, CategoryId parent) {
        id = CategoryId.generate();
        this.depth = depth;
        this.categoryName = categoryName;
        this.parent = parent;
    }

    public Category createChild(String categoryName) {

        if (depth + 1 > MAX_AVAILABLE_DEPTH) {
            throw new RuntimeException("Max depth reached");
        }

        return new Category(depth + 1, categoryName, id);
    }

    public int getDepth() {
        return depth;
    }
}
