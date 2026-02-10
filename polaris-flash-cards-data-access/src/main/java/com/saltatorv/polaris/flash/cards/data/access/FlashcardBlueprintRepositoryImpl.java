package com.saltatorv.polaris.flash.cards.data.access;

import com.saltatorv.polaris.flash.cards.data.access.entity.FlashcardBlueprintEntity;
import com.saltatorv.polaris.flash.cards.data.access.repository.SqlFlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintRepository;
import com.saltatorv.polaris.flash.cards.domain.FlashcardBlueprintSnapshot;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class FlashcardBlueprintRepositoryImpl implements FlashcardBlueprintRepository {

    private final SqlFlashcardBlueprintRepository sqlFlashcardBlueprintRepository;

    FlashcardBlueprintRepositoryImpl(SqlFlashcardBlueprintRepository sqlFlashcardBlueprintRepository) {
        this.sqlFlashcardBlueprintRepository = sqlFlashcardBlueprintRepository;
    }

    @Override
    public Optional<FlashcardBlueprintSnapshot> findById(FlashcardBlueprintId id) {
        Optional<FlashcardBlueprintEntity> found = sqlFlashcardBlueprintRepository.findById(id.getId());

        if (found.isEmpty()) {
            return Optional.empty();
        }
        FlashcardBlueprintEntity entity = found.get();

        FlashcardBlueprintSnapshot snapshot = new FlashcardBlueprintSnapshot(
                entity.getId(),
                entity.getQuestion(),
                entity.getDefinition(),
                entity.getSource(),
                List.of(entity.getTags().split(";")),
                entity.getLanguage()
        );
        return Optional.of(snapshot);
    }

    @Override
    public FlashcardBlueprintSnapshot save(FlashcardBlueprintSnapshot flashcardBlueprintSnapshot) {

        FlashcardBlueprintEntity entity = new FlashcardBlueprintEntity(
                flashcardBlueprintSnapshot.getFlashcardBlueprintId(),
                flashcardBlueprintSnapshot.getQuestion(),
                flashcardBlueprintSnapshot.getDefinition(),
                String.join(";", flashcardBlueprintSnapshot.getTags()),
                flashcardBlueprintSnapshot.getSource(),
                flashcardBlueprintSnapshot.getLanguage(),
                "Test"
        );

        sqlFlashcardBlueprintRepository.save(entity);

        return findById(
                new FlashcardBlueprintId(
                        UUID.fromString(
                                flashcardBlueprintSnapshot.getFlashcardBlueprintId())))
                .get();
    }

    @Override
    public List<FlashcardBlueprintSnapshot> findByIds(List<FlashcardBlueprintId> ids) {
        Iterable<FlashcardBlueprintEntity> entities =
                sqlFlashcardBlueprintRepository.findByIds(
                        ids.stream()
                                .map(id -> id.getId()).toList());

        List<FlashcardBlueprintSnapshot> snapshots = new ArrayList<>();

        for (FlashcardBlueprintEntity entity : entities) {
            snapshots.add(new FlashcardBlueprintSnapshot(
                    entity.getId(),
                    entity.getQuestion(),
                    entity.getDefinition(),
                    entity.getSource(),
                    List.of(entity.getTags().split(";")),
                    entity.getLanguage()));
        }

        return snapshots;
    }
}
