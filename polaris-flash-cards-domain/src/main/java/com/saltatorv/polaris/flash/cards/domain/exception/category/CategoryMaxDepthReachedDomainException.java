package com.saltatorv.polaris.flash.cards.domain.exception.category;

import com.saltatorv.polaris.flash.cards.domain.exception.DomainException;
import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;

public class CategoryMaxDepthReachedDomainException extends DomainException {
    private final static String MESSAGE = "Max depth reached when create category with name: %s from parent: %s";

    public CategoryMaxDepthReachedDomainException(String categoryName, CategoryId parentId) {
        super(MESSAGE.formatted(categoryName, parentId.getId()));
    }

}
