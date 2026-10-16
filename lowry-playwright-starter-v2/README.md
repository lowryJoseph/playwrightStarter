# Lowry Playwright Starter — v2

A deliberately small Java UI automation starter using Maven, JUnit 5, and Playwright.

This version adds one justified abstraction: `BrowserManager`, because cross-browser execution is a real requirement from day one.

## What is included

- Java 21
- Maven
- JUnit 5
- Playwright Java
- `BrowserManager` for browser lifecycle and selection
- `BaseTest` for shared test setup/teardown
- One page object: `CreatureForgePage`
- One real end-to-end UI test against the live Lowry Lab Creature Forge demo

## Structure

```text
playwright-ui-starter/
├── pom.xml
└── src/test/java/com/lowrylab/
    ├── framework/
    │   ├── BaseTest.java
    │   └── BrowserManager.java
    ├── pages/
    │   └── CreatureForgePage.java
    └── tests/
        └── CreatureForgeTest.java
```

## Browser support

| CLI value | Playwright target |
|---|---|
| `chrome` | Installed Google Chrome via Chromium channel |
| `edge` | Installed Microsoft Edge via Chromium channel |
| `firefox` | Playwright Firefox |
| `safari` | Playwright WebKit (Safari engine coverage, not installed Safari itself) |
| `webkit` | Playwright WebKit |
| `chromium` | Playwright Chromium |

The default is `chrome`.

## Requirements

- Java 21+
- Maven
- Google Chrome installed if using the default `chrome` target
- Microsoft Edge installed if using `edge`

## Install Playwright-managed browsers

```bash
mvn test -DskipTests
mvn exec:java -e \
  -Dexec.mainClass=com.microsoft.playwright.CLI \
  -Dexec.args="install chromium firefox webkit"
```

Chrome and Edge use the locally installed branded browser channels.

## Run the included test

The default URL is:

```text
https://apps.lowryjoseph.com/graphql-demo/
```

Run in Chrome:

```bash
mvn test
```

Watch it run:

```bash
mvn test -Dheadless=false
```

Run another browser:

```bash
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
mvn test -Dbrowser=safari
```

Run against a different deployment:

```bash
mvn test -DbaseUrl=https://your-environment.example/graphql-demo/
```

## What the example test proves

The test:

1. Opens Creature Forge.
2. Verifies Direct REST, REST BFF, and GraphQL Facade are available.
3. Selects GraphQL Facade.
4. Loads the existing boss example.
5. Verifies a GraphQL request is displayed.
6. Verifies the UI receives `Vharos, Warden of the Last Gate`.

The test remains intentionally small. No reporting framework, API utilities, configuration objects, workflows, dependency injection, or parallel execution have been added yet.

## Current architecture

```text
CreatureForgeTest
       ↓
CreatureForgePage
       ↓
     Page
       ↑
   BaseTest
       ↓
BrowserManager
       ↓
   Playwright
```

`BrowserManager` owns how a browser starts and stops. `BaseTest` owns the JUnit lifecycle. The page object owns knowledge of the UI. The test describes intent.
