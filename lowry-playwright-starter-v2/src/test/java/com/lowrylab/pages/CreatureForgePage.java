package com.lowrylab.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CreatureForgePage {
    private final Page page;
    private final String baseUrl;

    public CreatureForgePage(Page page, String baseUrl) {
        this.page = page;
        this.baseUrl = baseUrl;
    }

    public CreatureForgePage open() {
        page.navigate(baseUrl);
        return this;
    }

    public CreatureForgePage verifyLoaded() {
        assertThat(page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("⚔ Creature Forge ⚔")
        )).isVisible();
        return this;
    }

    public CreatureForgePage verifyArchitectureOptions() {
        assertThat(page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Direct REST"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("REST BFF"))).isVisible();
        assertThat(page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("GraphQL Facade"))).isVisible();
        return this;
    }

    public CreatureForgePage useGraphQlFacade() {
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("GraphQL Facade")).click();
        return this;
    }

    public CreatureForgePage loadBossExample() {
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Load boss example (b1)")).click();
        return this;
    }

    public CreatureForgePage verifyBossExampleReturned() {
        Locator response = page.locator("#response-view");
        assertThat(response).containsText("Vharos, Warden of the Last Gate");
        return this;
    }

    public CreatureForgePage verifyGraphQlRequestWasShown() {
        assertThat(page.locator("#request-view")).containsText("query GetCreature");
        return this;
    }
}
