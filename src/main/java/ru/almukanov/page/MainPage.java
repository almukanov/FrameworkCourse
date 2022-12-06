package ru.almukanov.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage
{

	protected WebDriver webDriver ;

	public MainPage(WebDriver webDriver, WebDriverWait webDriverWait) {
		super(webDriver);
		this.webDriver = webDriver;
		this.webDriverWait = webDriverWait;
	}

	public WebDriverWait webDriverWait;

	@Override
	public void openWebSite(String site) {
		webDriver.get(site);
	}

	public MainPage(WebDriver webDriver) {
		super(webDriver);
		this.webDriver = webDriver;
		PageFactory.initElements(this.webDriver, this);
	}



	public WebElement getIdElement(String id){

		return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
	}
	public WebElement getNameElement(String name){
		return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
	}
	public WebElement getXpathElement(String xpath){
		return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}

}
