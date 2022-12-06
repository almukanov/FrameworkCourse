package ru.almukanov.page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage
{
	protected WebDriver driver;

	protected abstract void openWebSite(String site);


	protected AbstractPage(WebDriver driver)
	{
		this.driver = driver;
	}
}
