package com.saltatorv.polaris.flash.cards.data.access.repository;

import com.saltatorv.polaris.flash.cards.data.access.entity.FlashcardBlueprintEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SqlFlashcardBlueprintRepository extends Repository<FlashcardBlueprintEntity, String> {
    Optional<FlashcardBlueprintEntity> findById(String id);

    FlashcardBlueprintEntity save(FlashcardBlueprintEntity flashcardBlueprintEntity);

    void deleteById(String id);

    Iterable<FlashcardBlueprintEntity> findAll();
}
