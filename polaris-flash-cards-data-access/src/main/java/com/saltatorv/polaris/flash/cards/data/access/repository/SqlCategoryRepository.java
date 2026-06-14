package com.saltatorv.polaris.flash.cards.data.access.repository;

import com.saltatorv.polaris.flash.cards.data.access.entity.CategoryEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface SqlCategoryRepository extends Repository<CategoryEntity, String> {

    Optional<CategoryEntity> findById(String id);

    List<CategoryEntity> findByParentId(String parentId);

    List<CategoryEntity> findByDepth(int depth);

    Optional<CategoryEntity> findByCategoryNameAndDepth(String categoryName, int depth);

    CategoryEntity save(CategoryEntity category);

    void deleteById(String id);
}
