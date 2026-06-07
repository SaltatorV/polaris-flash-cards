package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.exception.blueprint.FlashcardContentCannotBeEmptyDomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FlashcardContentTest {
    private FlashcardContent content;

    @Test
    public void testShouldCreateFlashcardContentWithQuestionAndAnswer() {
        //given

        //when
        createFlashcardContent("Question?", "Answer");

        //then
        assertEquals("Question?", content.getQuestion());
        assertEquals("Answer", content.getAnswer());
    }

    @Test
    public void testShouldThrowExceptionWhenCreateFlashcardContentWithNullQuestion() {
        //given

        //when
        assertThrows(FlashcardContentCannotBeEmptyDomainException.class, () -> createFlashcardContent(null, "Answer"));
    }

    @Test
    public void testShouldThrowExceptionWhenCreateFlashcardContentWithEmptyQuestion() {
        //given

        //when
        assertThrows(FlashcardContentCannotBeEmptyDomainException.class, () -> createFlashcardContent("          ", "Answer"));
    }

    @Test
    public void testShouldThrowExceptionWhenCreateFlashcardContentWithNullAnswer() {
        //given

        //when
        assertThrows(FlashcardContentCannotBeEmptyDomainException.class, () -> createFlashcardContent("Question?", null));
    }

    @Test
    public void testShouldThrowExceptionWhenCreateFlashcardContentWithEmptyAnswer() {
        //given

        //when
        assertThrows(FlashcardContentCannotBeEmptyDomainException.class, () -> createFlashcardContent("Question?", "          "));
    }

    private void createFlashcardContent(String question, String answer) {
        content = new FlashcardContent(question, answer);
    }
}
