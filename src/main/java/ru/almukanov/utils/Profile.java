package ru.almukanov.utils;

public enum Profile {
    DEV("src/main/resources/dev.properties"),
    QA("src/main/resources/qa.properties"),
    IFT("src/main/resources/ift.properties");
    final String profile;

    Profile(String profile) {
        this.profile = profile;
    }
}
