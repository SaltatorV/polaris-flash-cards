package com.saltatorv.polaris.flash.cards.data.access;

import com.saltatorv.polaris.flash.cards.data.access.entity.FlashcardReviewEntity;
import com.saltatorv.polaris.flash.cards.data.access.entity.FlashcardRevisionEntity;
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

    FlashcardReviewRepositoryImpl(SqlFlashcardReviewRepository sqlFlashcardReviewRepository) {
        this.sqlFlashcardReviewRepository = sqlFlashcardReviewRepository;
    }

    @Override
    public Optional<FlashcardReviewSnapshot> findById(FlashcardReviewId id) {

        return null;
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
            revisionEntities.add(new FlashcardRevisionEntity(flashcardReviewEntity,
                    flashcardSnapshot.getFlashcardBlueprintId(),
                    flashcardSnapshot.getAnswer()));
        }

        flashcardReviewEntity.setFlashcardRevisions(revisionEntities);
        sqlFlashcardReviewRepository.save(flashcardReviewEntity);
        return findById(
                new FlashcardReviewId(UUID.fromString(
                        flashcardReview.getFlashcardReviewId())))
                .get();
    }
}
