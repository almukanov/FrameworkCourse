package ru.almukanov;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.almukanov.driver.Driver;
import ru.almukanov.model.User;
import ru.almukanov.page.MainPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


/**
 * Unit test for simple App.
 */
public class FillGispFieldsTest
{
    String login;
    String pass;
    String price;
    String mailLogin;
    String composeMail;
    String addressTo;
    String mailSubject;

    WebDriver driver = Driver.getDriver(1);
    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    MainPage mainPage = new MainPage(driver, webDriverWait);
    @BeforeTest
    public void loadConfig() throws IOException {

        Properties property = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/dev.properties");
        property.load(fis);
        login = property.getProperty("user.name");
        pass = property.getProperty("user.password");
        price = property.getProperty("user.price.url");
        mailLogin = property.getProperty("user.mail.url.login");
        composeMail = property.getProperty("user.mail.url.compose");
        addressTo = property.getProperty("user.email.addressTo");
        mailSubject = property.getProperty("user.email.subject");
    }
        @Test

        public void shouldDoTaskThree(){

            mainPage.openWebSite(price);
            WebElement btn = mainPage.getXpathElement("//button[@title='Virtual Machines']");
            btn.click();
            WebElement reg = mainPage.getNameElement("region");
            Select region = new Select(reg);
            region.selectByValue("us-central");
            Select os = new Select(mainPage.getNameElement("operatingSystem"));
            os.selectByValue("linux");
            WebElement vm = mainPage.getXpathElement("//input[@name='count']");
            vm.clear();
            vm.sendKeys("5");
            WebElement compute = mainPage.getXpathElement("//input[@value='sv-three-year']");
            compute.click();
        /*
        total
         */
            String downloadFilepath = System.getProperty("user.dir");
            WebElement export = mainPage.getXpathElement("//button[@aria-label='Export Estimate']");
            export.click();

        /*
        Send email
         */
            User user = new User(login, pass);
            mainPage.openWebSite(mailLogin);
            WebElement login = mainPage.getNameElement("username");
            login.sendKeys(user.getUsername());
            WebElement btnNext = mainPage.getXpathElement("//button[@data-test-id='next-button']");
            btnNext.submit();
            WebElement password = mainPage.getXpathElement("//input[@name='password']");
            WebElement btnEnter = mainPage.getXpathElement("//button[@data-test-id='submit-button']");
            password.sendKeys(user.getPassword());
            btnEnter.submit();
            mainPage.openWebSite(composeMail);
            mainPage.openWebSite(composeMail);
            WebElement address = mainPage.getXpathElement("/html/body/div[1]/div/div[2]/div/div/div/div[2]/div[3]/div[2]/div/div/div[1]/div/div[2]/div/div/label/div/div/input");
            address.sendKeys(addressTo);
            WebElement subject = mainPage.getNameElement("Subject");
            subject.sendKeys(mailSubject);
            WebElement attachFile = mainPage.getXpathElement("//button[@class='container--2lPGK type_wide--2kyds color_base--hO-yz hoverable--2qtk5']//input[@type='file']");
            attachFile.sendKeys(downloadFilepath + "/ExportedEstimate.xlsx");
            WebElement sendBtn = mainPage.getXpathElement("//button[@data-test-id='send']");
            sendBtn.click();
            Driver.closeDriver();
        }



    private String getCurrentTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
        return ZonedDateTime.now().format(formatter);
    }

}
