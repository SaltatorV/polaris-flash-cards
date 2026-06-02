package com.saltatorv.polaris.flash.cards.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryTest {

    @Test
    public void testShouldCreateCategory() {
        //given

        //when
        var category = new Category("Programming");

        //then
        assertCategoryDepthIs(1, category);
    }

    @Test
    public void testShouldCreateSubCategory() {
        //given
        var category = new Category("Programming");

        //when
        var child = category.createChild("Java");

        //then
        assertCategoryDepthIs(1, category);
        assertCategoryDepthIs(2, child);
    }

    @Test
    public void testShouldCreateSubCategoriesToMaxDepth() {
        //given
        var firstGeneration = new Category("Programming");
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
        var firstGeneration = new Category("Programming");
        var secondGeneration = firstGeneration.createChild("Java");
        var thirdGeneration = secondGeneration.createChild("Collection");
        var fourthGeneration = thirdGeneration.createChild("Map");

        //when
        assertThrows(RuntimeException.class, () -> fourthGeneration.createChild("HashMap"));

        //then
    }
}
