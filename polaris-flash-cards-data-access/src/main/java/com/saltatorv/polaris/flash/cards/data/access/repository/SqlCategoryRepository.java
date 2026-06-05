package com.saltatorv.polaris.flash.cards.data.access.repository;

import com.saltatorv.polaris.flash.cards.data.access.entity.Category;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface SqlCategoryRepository extends Repository<Category, String> {

    Optional<Category> findById(String id);

    List<Category> findByParentId(String parentId);

    List<Category> findByDepth(int depth);

    Optional<Category> findByNameAndDepth(String categoryName, int depth);

    Category save(Category category);

    void deleteById(String id);
}
