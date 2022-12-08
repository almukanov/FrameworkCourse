package ru.almukanov.page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage extends AbstractPage
{
	WebDriver webDriver ;
	WebDriverWait webDriverWait;


	public MainPage(WebDriver webDriver, WebDriverWait webDriverWait) {
		super(webDriver, webDriverWait);
		this.webDriver = webDriver;
		this.webDriverWait = webDriverWait;

	}

	@FindBy(xpath = "//button[@title='Virtual Machines']")
	public WebElement chooseVMOption;
	@FindBy(name = "region")
	public  WebElement serverRegion;
	@FindBy(xpath = "//input[@name='count']")
	public  WebElement vm;
	@FindBy(xpath = "//input[@value='sv-three-year']")
	public  WebElement compute;
	@FindBy(xpath = "//button[@aria-label='Export Estimate']")
	public  WebElement export;
	@FindBy(name = "operatingSystem")
	public  WebElement os;

	@Override
	public  void init (WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
	@Override
	public String openWebSite(String site) {
		webDriver.get(site);
		return driver.getTitle();
	}

	public void chooseVM() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(chooseVMOption));
		chooseVMOption.click();
	}
	public String selectCountOfVM(String countOfVM) {
		vm.clear();
		vm.sendKeys(countOfVM);
		compute.click();
		return countOfVM;
	}
	public String selectRegion (WebElement regionElement, String region) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(regionElement));
		Select selectRegion = new Select(regionElement);
		selectRegion.selectByValue(region);
		return selectRegion.getFirstSelectedOption().getText();
	}
	public String selectOS (WebElement osElement, String os) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(osElement));
		Select selectOS = new Select(osElement);
		selectOS.selectByValue(os);
		return selectOS.getFirstSelectedOption().getText();
	}
	public void exportCountedDataInXML() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(export));
		export.click();
	}
}
