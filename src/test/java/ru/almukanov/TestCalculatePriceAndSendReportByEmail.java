package ru.almukanov;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.almukanov.driver.Driver;
import ru.almukanov.model.TestData;
import ru.almukanov.model.User;
import ru.almukanov.page.EmailPage;
import ru.almukanov.page.MainPage;
import ru.almukanov.utils.Browser;
import ru.almukanov.utils.Property;
import ru.almukanov.utils.Screenshot;
import java.time.Duration;

/**
 * Unit test for simple App.
 */
public class TestCalculatePriceAndSendReportByEmail {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeTest
    public void beforeTest() {
         driver = Driver.getDriver(Browser.CHROME);
         webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @DataProvider
    private Object[][] pageObjects() {
        return new Object[][]{
                {new MainPage(driver, webDriverWait) ,new EmailPage(driver, webDriverWait)}
        };
    }

    @Test(dataProvider = "pageObjects")
    public void computingPriceAndSendResultByEmail(MainPage mainPage, EmailPage emailPage) {
        User user = new User(Property.loadConfig().getProperty("user.name"),
                             Property.loadConfig().getProperty("user.password"));
        TestData data = new TestData(Property.loadConfig().getProperty("data.region"),
                                     Property.loadConfig().getProperty("data.os"),
                                     Property.loadConfig().getProperty("data.vms"));
        try {
            mainPage.init(driver);
            mainPage.openWebSite(Property.loadConfig().getProperty("user.price.url"));
            mainPage.chooseVM();
            String chosenRegion = mainPage.selectRegion(mainPage.serverRegion, data.getRegion());
            Assert.assertEquals(chosenRegion, "Central US");

            String selectedOs = mainPage.selectOS(mainPage.os, data.getOs());
            Assert.assertEquals(selectedOs, "Linux");

            String countOfVM = mainPage.selectCountOfVM(data.getCountOfVm());
            Assert.assertEquals(countOfVM , "5");

            mainPage.exportCountedDataInXML();

            emailPage.init(driver);
            String site = emailPage.openWebSite(Property.loadConfig().getProperty("user.mail.url.login"));
            Assert.assertTrue(site.contains("Авторизация"));

            emailPage.inputLogin(user.getUsername());
            emailPage.pressNxtBtnAfterLogin();
            emailPage.inputPass(user.getPassword());
            emailPage.pressEnterAfterPassword();
            emailPage.openWebSite(Property.loadConfig().getProperty("user.mail.url.inbox"));
            emailPage.openWebSite(Property.loadConfig().getProperty("user.mail.url.compose"));
            emailPage.fillReceiverAddress(Property.loadConfig().getProperty("user.email.addressTo"));
            emailPage.fillSubject(Property.loadConfig().getProperty("user.email.subject"));
            emailPage.attachFileForEmail("ExportedEstimate.xlsx");
            emailPage.pressSendButton();
        }
         finally {
            Screenshot.makeScreen(driver);
            Driver.closeDriver();
        }
    }
}
