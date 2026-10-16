package com.lowrylab.tests;

import com.lowrylab.framework.BaseTest;
import com.lowrylab.pages.CreatureForgePage;
import org.junit.jupiter.api.Test;

class CreatureForgeTest extends BaseTest {

    @Test
    void graphqlFacadeLoadsBossCreature() {
        new CreatureForgePage(page, baseUrl)
                .open()
                .verifyLoaded()
                .verifyArchitectureOptions()
                .useGraphQlFacade()
                .loadBossExample()
                .verifyGraphQlRequestWasShown()
                .verifyBossExampleReturned();
    }
}
