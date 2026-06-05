package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById(CategoryId id);

    Category save(Category category);

    void deleteById(CategoryId id);

    List<Category> findByParentId(CategoryId parentId);

    Optional<Category> findByNameAndDepth(String categoryName, int depth);

    List<Category> findByDepth(int depth);
}
