package com.saltatorv.polaris.flash.cards.data.access.entity;

import jakarta.persistence.*;

@Entity
public class CategoryEntity {
    @Id
    @Column(length = 36)
    private String id;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntity parent;
    private int depth;
    @Column(length = 150)
    private String categoryName;

    public CategoryEntity() {
    }

    public CategoryEntity(String id, CategoryEntity parent, int depth, String categoryName) {
        this.id = id;
        this.parent = parent;
        this.depth = depth;
        this.categoryName = categoryName;
    }

    public CategoryEntity(String id, int depth, String categoryName) {
        this.id = id;
        this.depth = depth;
        this.categoryName = categoryName;
    }

    public String getId() {
        return id;
    }

    public CategoryEntity getParent() {
        return parent;
    }

    public int getDepth() {
        return depth;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
