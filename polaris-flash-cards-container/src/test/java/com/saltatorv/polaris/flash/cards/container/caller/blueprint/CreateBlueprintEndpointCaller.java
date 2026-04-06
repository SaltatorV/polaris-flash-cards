package com.saltatorv.polaris.flash.cards.container.caller.blueprint;

public interface CreateBlueprintEndpointCaller {

    CreateBlueprintEndpointCaller addDefaultBlueprintToRequestBody(int times);

    FlashcardBlueprintEndpointCaller executeCreateAPICall();
}
