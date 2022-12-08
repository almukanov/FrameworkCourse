package ru.almukanov.model;

public class TestData {
    private final String region;
    private final String os;
    private final String countOfVm;

    public TestData(String region, String os, String countOfVm) {
        this.region = region;
        this.os = os;
        this.countOfVm = countOfVm;
    }

    public String getRegion() {
        return region;
    }
    public String getOs() {
        return os;
    }
    public String getCountOfVm() {
        return countOfVm;
    }
}
