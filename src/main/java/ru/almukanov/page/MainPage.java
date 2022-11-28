package ru.almukanov.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.almukanov.utils.Constans;

import java.time.Duration;

public class MainPage extends AbstractPage
{

	@FindBy(xpath = "//*[@cm-text]")
	private  WebElement textArea;

	@FindBy(xpath = "//input[@class = 'form-control js-gist-filename js-blob-filename']")
	private WebElement title;

	@FindBy(xpath = "//select[@class='form-select select-sm js-code-indent-mode']")
	private WebElement select;

	@FindBy(xpath = "//div[@class='BtnGroup']//button")
	private WebElement submit;

	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}


	@Override
	public MainPage openPage()
	{
		driver.navigate().to(Constans.GIST_GIT);
		return this;
	}

	public MainPage fillFields(){
		textArea.sendKeys(Constans.TEST_TEXT_TASK_2);
		title.sendKeys(Constans.TITLE);
		Select select1 = new Select(select);
		select1.selectByVisibleText(Constans.TABS);
		submit.click();
		return this;
	}

}
