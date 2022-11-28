package ru.almukanov.driver;

public enum Browsers {
    Chrome(1), Firefox(0);
    private final int browser;
    Browsers(int browser) {
        this.browser = browser;
    }
}
