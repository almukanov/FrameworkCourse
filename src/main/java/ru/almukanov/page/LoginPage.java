package ru.almukanov.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.almukanov.model.User;

public class LoginPage extends AbstractPage
{

	@FindBy(name = "login")
	private WebElement fillLogin;

	@FindBy(name = "password")
	private WebElement fillPass;

	@FindBy(name = "commit")
	private WebElement buttonSubmit;



	public LoginPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openWebSite(String site)
	{
		driver.get(site);
	}

	public MainPage login(User user)
	{
		fillLogin.sendKeys(user.getUsername());
		fillPass.sendKeys(user.getPassword());
		buttonSubmit.click();
		return new MainPage(driver);
	}



}
