package com.saltatorv.polaris.flash.cards.data.access.repository;

import com.saltatorv.polaris.flash.cards.data.access.entity.FlashcardReviewEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SqlFlashcardReviewRepository extends Repository<FlashcardReviewEntity, String> {

    Optional<FlashcardReviewEntity> findById(String id);

    FlashcardReviewEntity save(FlashcardReviewEntity flashcardReviewEntity);

    void deleteById(String id);

    Iterable<FlashcardReviewEntity> findAll();
}
