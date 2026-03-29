package com.saltatorv.polaris.flash.cards.data.access;

import com.saltatorv.polaris.flash.cards.data.access.entity.FlashcardBlueprintEntity;
import com.saltatorv.polaris.flash.cards.data.access.entity.FlashcardReviewEntity;
import com.saltatorv.polaris.flash.cards.data.access.entity.FlashcardRevisionEntity;
import com.saltatorv.polaris.flash.cards.data.access.repository.SqlFlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.data.access.repository.SqlFlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardReviewSnapshot;
import com.saltatorv.polaris.flash.cards.domain.FlashcardSnapshot;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class FlashcardReviewRepositoryImpl implements FlashcardReviewRepository {

    private final SqlFlashcardReviewRepository sqlFlashcardReviewRepository;
    private final SqlFlashcardBlueprintRepository flashcardBlueprintRepository;

    FlashcardReviewRepositoryImpl(SqlFlashcardReviewRepository sqlFlashcardReviewRepository,
                                  SqlFlashcardBlueprintRepository flashcardBlueprintRepository) {
        this.sqlFlashcardReviewRepository = sqlFlashcardReviewRepository;
        this.flashcardBlueprintRepository = flashcardBlueprintRepository;
    }

    @Override
    public Optional<FlashcardReviewSnapshot> findById(FlashcardReviewId id) {
        Optional<FlashcardReviewEntity> foundReview =
                sqlFlashcardReviewRepository.findById(id.getId());

        if (foundReview.isEmpty()) {
            throw new IllegalArgumentException("Review not found");
        }

        FlashcardReviewEntity flashcardReviewEntity = foundReview.get();

        List<FlashcardSnapshot> flashcardSnapshots = new ArrayList<>();

        for (FlashcardRevisionEntity revisionEntity : flashcardReviewEntity.getFlashcardRevisions()) {
            FlashcardBlueprintEntity blueprint = revisionEntity.getFlashcardBlueprint();

            flashcardSnapshots.add(new FlashcardSnapshot(blueprint.getId(),
                    blueprint.getQuestion(), blueprint.getDefinition(),
                    revisionEntity.getStatus()));
        }

        FlashcardReviewSnapshot flashcardReviewSnapshot = new FlashcardReviewSnapshot(flashcardReviewEntity.getId(),
                flashcardReviewEntity.getStartDate(),
                flashcardReviewEntity.getFinishDate(),
                flashcardSnapshots);

        return Optional.of(flashcardReviewSnapshot);
    }

    @Override
    public FlashcardReviewSnapshot save(FlashcardReviewSnapshot flashcardReview) {

        FlashcardReviewEntity flashcardReviewEntity = new FlashcardReviewEntity(
                flashcardReview.getFlashcardReviewId(),
                flashcardReview.getStartDate(),
                flashcardReview.getFinishDate()
        );

        List<FlashcardRevisionEntity> revisionEntities = new ArrayList<>();
        for (FlashcardSnapshot flashcardSnapshot : flashcardReview.getFlashcardSnapshots()) {

            Optional<FlashcardBlueprintEntity> foundBlueprint = flashcardBlueprintRepository
                    .findById(flashcardSnapshot.getFlashcardBlueprintId());

            if (foundBlueprint.isEmpty()) {
                throw new IllegalArgumentException("Flashcard blueprint not found");
            }

            revisionEntities.add(new FlashcardRevisionEntity(flashcardReviewEntity,
                    foundBlueprint.get(),
                    flashcardSnapshot.getAnswer()));
        }

        flashcardReviewEntity.setFlashcardRevisions(revisionEntities);
        sqlFlashcardReviewRepository.save(flashcardReviewEntity);
        return findById(
                new FlashcardReviewId(UUID.fromString(
                        flashcardReview.getFlashcardReviewId())))
                .get();
    }

    @Override
    public void deleteById(FlashcardReviewId id) {
        Optional<FlashcardReviewEntity> foundReview =
                sqlFlashcardReviewRepository.findById(id.getId());

        if (foundReview.isEmpty()) {
            throw new IllegalArgumentException("Review not found");
        }

        sqlFlashcardReviewRepository.deleteById(id.getId());
    }
}
