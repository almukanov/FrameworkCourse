package ru.almukanov.page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EmailPage extends AbstractPage {
	WebDriver webDriver ;
	WebDriverWait webDriverWait;
	@FindBy(name = "username")
	private WebElement fillLogin;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement fillPass;
	@FindBy(xpath = "//button[@data-test-id='next-button']")
	WebElement btnNext;
	@FindBy(xpath = "//button[@data-test-id='submit-button']")
	WebElement btnEnter;
	@FindBy(xpath = "//div[contains(@class,'contacts')]//input[contains(@class, 'container')]")
	WebElement receiver;
	@FindBy(name = "Subject")
	WebElement subject;
	@FindBy(xpath = "//div[text()='Прикрепить файл']/following-sibling::input")
	WebElement attachFile;
	@FindBy(xpath = "//button[@data-test-id='send']")
	WebElement sendBtn;

	@Override
	public  void init (WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
	@Override
	public String openWebSite(String site) {
		driver.get(site);
		return driver.getTitle();
	}

	public EmailPage(WebDriver driver, WebDriverWait webDriverWait) {
		super(driver, webDriverWait);
		this.webDriver = webDriver;
		this.webDriverWait = webDriverWait;
	}

	public void inputLogin(String login) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fillLogin));
		fillLogin.sendKeys(login);
	}
	public void pressNxtBtnAfterLogin() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(btnNext));
		btnNext.submit();
	}
	public void inputPass(String password) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fillPass));
		fillPass.sendKeys(password);
	}
	public void pressEnterAfterPassword() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(btnEnter));
		btnEnter.submit();
	}
	public void fillReceiverAddress(String address) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(receiver));
		receiver.sendKeys(address);
	}
	public void fillSubject(String sub) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(subject));
		subject.sendKeys(sub);
	}
	public void attachFileForEmail(String filePath) {
		String downloadFilepath = System.getProperty("user.dir");
		attachFile.sendKeys(downloadFilepath +"\\"+filePath);
	}
	public void pressSendButton(){
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(sendBtn));
		sendBtn.click();
	}
}
