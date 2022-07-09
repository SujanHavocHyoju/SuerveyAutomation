package com.havoc.surveyautomation.prepare;

public enum WebDriverType {
    FIREFOX_WEBDRIVER("webdriver.gecko.driver"), CHROME_WEBDRIVER("webdriver.chrome.driver"), OPERA_WEBDRIVER(
            "webdriver.opera.driver"), EDGE_WEBDRIVER("webdriver.edge.driver");

    private String value;

    WebDriverType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}