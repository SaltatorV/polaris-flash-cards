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

import java.util.*;
import java.util.stream.Collectors;

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
                    revisionEntity.getStatus(),
                    revisionEntity.getStartDate(),
                    revisionEntity.getFinishDate()));
        }

        FlashcardReviewSnapshot flashcardReviewSnapshot = new FlashcardReviewSnapshot(flashcardReviewEntity.getId(),
                flashcardReviewEntity.getStartDate(),
                flashcardReviewEntity.getFinishDate(),
                flashcardSnapshots);
        System.out.println("FIND BY ID!!!!");
        System.out.println(flashcardReviewSnapshot);
        return Optional.of(flashcardReviewSnapshot);
    }

    @Override
    public FlashcardReviewSnapshot save(FlashcardReviewSnapshot flashcardReview) {
        Optional<FlashcardReviewEntity> fromDb = sqlFlashcardReviewRepository.findById(flashcardReview.getFlashcardReviewId());
        FlashcardReviewEntity flashcardReviewEntity = fromDb.orElseGet(() -> new FlashcardReviewEntity(flashcardReview.getFlashcardReviewId()));

        flashcardReviewEntity.setStartDate(flashcardReview.getStartDate());
        flashcardReviewEntity.setFinishDate(flashcardReview.getFinishDate());

        List<FlashcardRevisionEntity> revisions = createRevisionFromFlashcardSnapshots(flashcardReview, flashcardReviewEntity);
        if (flashcardReviewEntity.getFlashcardRevisions().isEmpty()) {
            System.out.println("IS EMPTY");
            flashcardReviewEntity.getFlashcardRevisions().addAll(revisions);
        } else {
            System.out.println("IS NOT EMPTY");
            Map<String, FlashcardRevisionEntity> currentRevisions =
                    flashcardReviewEntity
                            .getFlashcardRevisions()
                            .stream()
                            .collect(Collectors.toMap(revision -> revision.getFlashcardBlueprint().getId(),
                                    revision -> revision));

            for (FlashcardRevisionEntity newOne : revisions) {
                currentRevisions.get(newOne.getFlashcardBlueprint().getId()).updateFrom(newOne);
            }


        }
        System.out.println("SAVE!!!!");
        sqlFlashcardReviewRepository.save(flashcardReviewEntity);
        for (FlashcardRevisionEntity revision : flashcardReviewEntity.getFlashcardRevisions()) {
            System.out.println(revision);
        }
        System.out.println("SAVED!!!!");
        return findById(
                new FlashcardReviewId(UUID.fromString(
                        flashcardReview.getFlashcardReviewId())))
                .get();
    }

    private List<FlashcardRevisionEntity> createRevisionFromFlashcardSnapshots(FlashcardReviewSnapshot flashcardReview, FlashcardReviewEntity flashcardReviewEntity) {
        List<FlashcardRevisionEntity> revisions = new ArrayList<>();
        for (FlashcardSnapshot flashcardSnapshot : flashcardReview.getFlashcardSnapshots()) {

            Optional<FlashcardBlueprintEntity> foundBlueprint = flashcardBlueprintRepository
                    .findById(flashcardSnapshot.getFlashcardBlueprintId());

            if (foundBlueprint.isEmpty()) {
                throw new IllegalArgumentException("Flashcard blueprint not found");
            }

            FlashcardRevisionEntity newOne = new FlashcardRevisionEntity(flashcardReviewEntity,
                    foundBlueprint.get(),
                    flashcardSnapshot.getAnswer(),
                    flashcardSnapshot.getStartDate(),
                    flashcardSnapshot.getFinishDate(),
                    flashcardReview.getFlashcardSnapshots().indexOf(flashcardSnapshot));

            revisions.add(newOne);
        }

        return revisions;
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
