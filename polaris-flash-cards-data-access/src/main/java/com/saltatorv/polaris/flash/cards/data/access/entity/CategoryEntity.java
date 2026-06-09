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

    public CategoryEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public CategoryEntity getParent() {
        return parent;
    }

    public void setParent(CategoryEntity parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
