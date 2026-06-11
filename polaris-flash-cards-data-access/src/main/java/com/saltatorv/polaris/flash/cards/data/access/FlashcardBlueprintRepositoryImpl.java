package com.saltatorv.polaris.flash.cards.data.access;

import com.saltatorv.polaris.flash.cards.data.access.entity.CategoryEntity;
import com.saltatorv.polaris.flash.cards.data.access.entity.FlashcardBlueprintEntity;
import com.saltatorv.polaris.flash.cards.data.access.entity.FlashcardLocalizationEntity;
import com.saltatorv.polaris.flash.cards.data.access.repository.SqlCategoryRepository;
import com.saltatorv.polaris.flash.cards.data.access.repository.SqlFlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardContent;
import com.saltatorv.polaris.flash.cards.domain.FlashcardLocalization;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import com.saltatorv.polaris.flash.cards.domain.snapshot.FlashcardBlueprintSnapshot;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
class FlashcardBlueprintRepositoryImpl implements FlashcardBlueprintRepository {

    private final SqlFlashcardBlueprintRepository sqlFlashcardBlueprintRepository;
    private final SqlCategoryRepository sqlCategoryRepository;

    FlashcardBlueprintRepositoryImpl(SqlFlashcardBlueprintRepository sqlFlashcardBlueprintRepository, SqlCategoryRepository sqlCategoryRepository) {
        this.sqlFlashcardBlueprintRepository = sqlFlashcardBlueprintRepository;
        this.sqlCategoryRepository = sqlCategoryRepository;
    }

    @Override
    public Optional<FlashcardBlueprintSnapshot> findById(FlashcardBlueprintId id) {
        Optional<FlashcardBlueprintEntity> found = sqlFlashcardBlueprintRepository.findById(id.getId());

        if (found.isEmpty()) {
            return Optional.empty();
        }
        FlashcardBlueprintEntity entity = found.get();
        List<FlashcardLocalization> localizationEntities = entity.getFlashcardLocalizations()
                .stream()
                .map(flashcardLocalizationEntity -> new FlashcardLocalization(
                        Locale.of(flashcardLocalizationEntity.getLanguage()),
                        new FlashcardContent(flashcardLocalizationEntity.getQuestion(), flashcardLocalizationEntity.getDefinition())
                ))
                .toList();

        FlashcardBlueprintSnapshot snapshot = new FlashcardBlueprintSnapshot(
                entity.getId(),
                entity.getCategory().getId(),
                localizationEntities,
                entity.getSource(),
                Set.of(entity.getTags().split(";"))
        );
        return Optional.of(snapshot);
    }

    @Override
    public FlashcardBlueprintSnapshot save(FlashcardBlueprintSnapshot flashcardBlueprintSnapshot) {
        CategoryEntity category = sqlCategoryRepository.findById(flashcardBlueprintSnapshot.getCategoryId()).get();

        FlashcardBlueprintEntity entity = new FlashcardBlueprintEntity(
                category,
                "test",
                flashcardBlueprintSnapshot.getSource(),
                String.join(";", flashcardBlueprintSnapshot.getTags()),
                flashcardBlueprintSnapshot.getFlashcardBlueprintId()
        );

        List<FlashcardLocalizationEntity> localizations = flashcardBlueprintSnapshot.getLocalizations()
                .stream()
                .map(localization -> new FlashcardLocalizationEntity())
                .toList();

        entity.setFlashcardLocalizations(localizations);

        sqlFlashcardBlueprintRepository.save(entity);

        return findById(
                new FlashcardBlueprintId(
                        flashcardBlueprintSnapshot.getFlashcardBlueprintId()))
                .get();
    }

    @Override
    public List<FlashcardBlueprintSnapshot> findByIds(List<FlashcardBlueprintId> ids) {
        Iterable<FlashcardBlueprintEntity> entities =
                sqlFlashcardBlueprintRepository.findByIdIn(
                        ids.stream()
                                .map(id -> id.getId()).toList());

        List<FlashcardBlueprintSnapshot> snapshots = new ArrayList<>();

        for (FlashcardBlueprintEntity entity : entities) {
            List<FlashcardLocalization> localizationEntities = entity.getFlashcardLocalizations()
                    .stream()
                    .map(flashcardLocalizationEntity -> new FlashcardLocalization(
                            Locale.of(flashcardLocalizationEntity.getLanguage()),
                            new FlashcardContent(flashcardLocalizationEntity.getQuestion(), flashcardLocalizationEntity.getDefinition())
                    ))
                    .toList();

            snapshots.add(new FlashcardBlueprintSnapshot(
                    entity.getId(),
                    entity.getCategory().getId(),
                    localizationEntities,
                    entity.getSource(),
                    Set.of(entity.getTags().split(";"))));
        }

        return snapshots;
    }

    @Override
    public List<FlashcardBlueprintSnapshot> findAll() {
        Iterable<FlashcardBlueprintEntity> entities = sqlFlashcardBlueprintRepository.findAll();

        List<FlashcardBlueprintSnapshot> snapshots = new ArrayList<>();

        for (FlashcardBlueprintEntity entity : entities) {

            List<FlashcardLocalization> localizationEntities = entity.getFlashcardLocalizations()
                    .stream()
                    .map(flashcardLocalizationEntity -> new FlashcardLocalization(
                            Locale.of(flashcardLocalizationEntity.getLanguage()),
                            new FlashcardContent(flashcardLocalizationEntity.getQuestion(), flashcardLocalizationEntity.getDefinition())
                    ))
                    .toList();

            snapshots.add(new FlashcardBlueprintSnapshot(
                    entity.getId(),
                    entity.getCategory().getId(),
                    localizationEntities,
                    entity.getSource(),
                    Set.of(entity.getTags().split(";"))));
        }

        return snapshots;
    }

    @Override
    public List<FlashcardBlueprintSnapshot> findAllByCategoryId(CategoryId categoryId) {
        return List.of();
    }
}
