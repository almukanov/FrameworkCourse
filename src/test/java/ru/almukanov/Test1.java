package ru.almukanov;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.almukanov.driver.Driver;
import ru.almukanov.model.User;
import ru.almukanov.page.LoginPage;
import ru.almukanov.page.MainPage;
import ru.almukanov.utils.Constans;

/**
 * Unit test for simple App.
 */
public class Test1
{

    @Test
    public void createNewRecord()
    {
        User user1 = new User(Constans.GIT_LOGIN, Constans.GIT_PASS);
        WebDriver driver = Driver.getDriver(1);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(user1);
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.fillFields();
        driver.close();

    }
}
