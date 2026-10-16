package com.lowrylab.framework;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    protected BrowserManager browserManager;
    protected Page page;
    protected String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = System.getProperty(
                "baseUrl",
                "https://apps.lowryjoseph.com/graphql-demo/"
        );

        browserManager = new BrowserManager();
        page = browserManager.start();
    }

    @AfterEach
    void tearDown() {
        if (browserManager != null) {
            browserManager.stop();
        }
    }
}
