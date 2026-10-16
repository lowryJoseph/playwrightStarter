package com.lowrylab.framework;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserManager {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    public Page start() {
        String browserName = System.getProperty("browser", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));

        playwright = Playwright.create();
        browser = launchBrowser(browserName, headless);
        context = browser.newContext();
        page = context.newPage();

        return page;
    }

    private Browser launchBrowser(String browserName, boolean headless) {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless);

        return switch (browserName) {
            case "chrome" -> playwright.chromium().launch(options.setChannel("chrome"));
            case "edge" -> playwright.chromium().launch(options.setChannel("msedge"));
            case "firefox" -> playwright.firefox().launch(options);
            case "safari", "webkit" -> playwright.webkit().launch(options);
            case "chromium" -> playwright.chromium().launch(options);
            default -> throw new IllegalArgumentException(
                    "Unsupported browser '" + browserName + "'. " +
                    "Use chrome, edge, firefox, safari, webkit, or chromium."
            );
        };
    }

    public Page getPage() {
        return page;
    }

    public BrowserContext getContext() {
        return context;
    }

    public void stop() {
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
