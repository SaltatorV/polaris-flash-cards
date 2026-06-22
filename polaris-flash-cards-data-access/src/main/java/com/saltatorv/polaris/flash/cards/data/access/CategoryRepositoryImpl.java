package com.saltatorv.polaris.flash.cards.data.access;

import com.saltatorv.polaris.flash.cards.data.access.entity.CategoryEntity;
import com.saltatorv.polaris.flash.cards.data.access.repository.SqlCategoryRepository;
import com.saltatorv.polaris.flash.cards.domain.CategoryRepository;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;
import com.saltatorv.polaris.flash.cards.domain.snapshot.CategorySnapshot;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class CategoryRepositoryImpl implements CategoryRepository {

    private final SqlCategoryRepository sqlCategoryRepository;

    public CategoryRepositoryImpl(SqlCategoryRepository sqlCategoryRepository) {
        this.sqlCategoryRepository = sqlCategoryRepository;
    }

    @Override
    public Optional<CategorySnapshot> findById(CategoryId id) {
        Optional<CategoryEntity> optional = sqlCategoryRepository.findById(id.getId());

        if (optional.isEmpty()) {
            return Optional.empty();
        }
        CategoryEntity entity = optional.get();
        String parentId = entity.getParent() == null ? null : entity.getParent().getId();
        CategorySnapshot snapshot = new CategorySnapshot(entity.getId(), entity.getDepth(),
                entity.getCategoryName(), parentId);
        return Optional.of(snapshot);
    }

    @Override
    public CategorySnapshot save(CategorySnapshot category) {
        Optional<CategoryEntity> fromDb = sqlCategoryRepository.findById(category.getId());
        CategoryEntity toSave = fromDb.orElseGet(() -> new CategoryEntity(category.getId()));

        toSave.setDepth(category.getDepth());
        toSave.setCategoryName(category.getCategoryName());

        CategoryEntity parent = null;
        if(category.getParentID() != null) {
            parent = sqlCategoryRepository.findById(category.getParentID()).orElse(null);
        }

        toSave.setParent(parent);

        sqlCategoryRepository.save(toSave);

        return mapEntityToSnapshot(toSave);
    }

    @Override
    public void deleteById(CategoryId id) {
        sqlCategoryRepository.deleteById(id.getId());
    }

    @Override
    public List<CategorySnapshot> findByParentId(CategoryId parentId) {
        List<CategoryEntity> categoryEntities = sqlCategoryRepository.findByParentId(parentId.getId());

        return categoryEntities.stream()
                .map(this::mapEntityToSnapshot)
                .toList();
    }

    @Override
    public Optional<CategorySnapshot> findByNameAndDepth(String categoryName, int depth) {
        Optional<CategoryEntity> optional = sqlCategoryRepository.findByCategoryNameAndDepth(categoryName, depth);
        return optional.map(this::mapEntityToSnapshot);
    }

    @Override
    public List<CategorySnapshot> findByDepth(int depth) {
        List<CategoryEntity> categoryEntities = sqlCategoryRepository.findByDepth(depth);
        return categoryEntities.stream()
                .map(this::mapEntityToSnapshot)
                .toList();
    }

    private CategorySnapshot mapEntityToSnapshot(CategoryEntity entity) {
        String parentId = entity.getParent() == null ? null : entity.getParent().getId();
        return new CategorySnapshot(entity.getId(), entity.getDepth(), entity.getCategoryName(), parentId);
    }
}
