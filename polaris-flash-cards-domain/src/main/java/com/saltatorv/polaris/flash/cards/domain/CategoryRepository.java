package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;
import com.saltatorv.polaris.flash.cards.domain.snapshot.CategorySnapshot;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<CategorySnapshot> findById(CategoryId id);

    Category save(CategorySnapshot category);

    void deleteById(CategoryId id);

    List<CategorySnapshot> findByParentId(CategoryId parentId);

    Optional<CategorySnapshot> findByNameAndDepth(String categoryName, int depth);

    List<CategorySnapshot> findByDepth(int depth);
}
