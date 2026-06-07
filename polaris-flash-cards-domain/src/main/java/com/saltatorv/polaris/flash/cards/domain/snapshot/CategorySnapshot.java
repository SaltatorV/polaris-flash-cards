package com.saltatorv.polaris.flash.cards.domain.snapshot;

import com.saltatorv.polaris.flash.cards.domain.Generated;

import java.util.Objects;

@Generated
public class CategorySnapshot {
    private final String id;
    private final int depth;
    private final String categoryName;
    private final String parentID;

    public CategorySnapshot(String id, int depth, String categoryName, String parentID) {
        this.id = id;
        this.depth = depth;
        this.categoryName = categoryName;
        this.parentID = parentID;
    }

    public String getId() {
        return id;
    }

    public int getDepth() {
        return depth;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getParentID() {
        return parentID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CategorySnapshot that = (CategorySnapshot) o;
        return depth == that.depth && id.equals(that.id) && categoryName.equals(that.categoryName) && Objects.equals(parentID, that.parentID);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + depth;
        result = 31 * result + categoryName.hashCode();
        result = 31 * result + Objects.hashCode(parentID);
        return result;
    }
}
