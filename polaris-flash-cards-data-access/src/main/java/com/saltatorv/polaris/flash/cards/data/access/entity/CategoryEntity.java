package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CategoryEntity {
    @Id
    @Column(length = 36)
    private String id;
    @Column(length = 36)
    private String parent;
    private int depth;
    @Column(length = 150)
    private String categoryName;

    public CategoryEntity() {
    }

    public CategoryEntity(String id, String parent, int depth, String categoryName) {
        this.id = id;
        this.parent = parent;
        this.depth = depth;
        this.categoryName = categoryName;
    }

    public String getId() {
        return id;
    }

    public String getParent() {
        return parent;
    }

    public int getDepth() {
        return depth;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
