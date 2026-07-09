package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.exception.category.CategoryMaxDepthReachedDomainException;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;
import com.saltatorv.polaris.flash.cards.domain.snapshot.CategorySnapshot;

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

    private Category(CategoryId categoryId, String categoryName, int depth, CategoryId parentId) {
        this.id = categoryId;
        this.parent = parentId;
        this.categoryName = categoryName;
        this.depth = depth;
    }

    public static Category restore(CategorySnapshot snapshot) {
        CategoryId parent = snapshot.getParentID() == null ? null : new CategoryId(snapshot.getParentID());

        return new Category(new CategoryId(snapshot.getId()),
                snapshot.getCategoryName(),
                snapshot.getDepth(),
                parent);
    }

    public CategorySnapshot generateSnapshot() {
        String parentId = parent == null ? null : parent.getId();
        return new CategorySnapshot(id.getId(), depth, categoryName, parentId);
    }

    public Category createChild(String categoryName) {

        if (depth + 1 > MAX_AVAILABLE_DEPTH) {
            throw new CategoryMaxDepthReachedDomainException(categoryName, id);
        }

        return new Category(depth + 1, categoryName, id);
    }

    public int getDepth() {
        return depth;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
