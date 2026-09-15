package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.exception.category.CategoryMaxDepthReachedDomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryTest {

    @Test
    public void testShouldCreateCategory() {
        //given

        //when
        var category = createBaseCategory();

        //then
        assertCategoryDepthIs(1, category);
    }

    @Test
    public void testShouldCreateSubCategory() {
        //given
        var category = createBaseCategory();

        //when
        var child = category.createChild("Java");

        //then
        assertCategoryDepthIs(1, category);
        assertCategoryDepthIs(2, child);
    }

    @Test
    public void testShouldCreateSubCategoriesToMaxDepth() {
        //given
        var firstGeneration = createBaseCategory();
        var secondGeneration = firstGeneration.createChild("Java");

        //when
        var thirdGeneration = secondGeneration.createChild("Collection");
        var lastGeneration = thirdGeneration.createChild("Map");

        //then
        assertCategoryDepthIs(1, firstGeneration);
        assertCategoryDepthIs(2, secondGeneration);
        assertCategoryDepthIs(3, thirdGeneration);
        assertCategoryDepthIs(4, lastGeneration);
    }

    @Test
    public void testShouldThrowExceptionWhenSubCategoryIsTooDeep() {
        //given
        var firstGeneration = createBaseCategory();
        var secondGeneration = firstGeneration.createChild("Java");
        var thirdGeneration = secondGeneration.createChild("Collection");
        var fourthGeneration = thirdGeneration.createChild("Map");

        //when
        assertThrows(CategoryMaxDepthReachedDomainException.class, () -> fourthGeneration.createChild("HashMap"));

        //then
    }
    
    private Category createBaseCategory() {
        return new Category("Programming");
    }

    private void assertCategoryDepthIs(int depth, Category category) {
        assertEquals(depth, category.getDepth());
    }
}
