package ru.almukanov.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	protected WebDriver driver;
	protected WebDriverWait webDriverWait;

	public abstract String openWebSite(String site);
	public abstract void init (WebDriver webDriver);

	protected AbstractPage(WebDriver driver, WebDriverWait webDriverWait) {
		this.driver = driver;
		this.webDriverWait = webDriverWait;
	}
}
