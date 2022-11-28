package ru.almukanov;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import ru.almukanov.driver.Driver;
import ru.almukanov.model.User;
import ru.almukanov.page.LoginPage;
import ru.almukanov.page.MainPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Properties;


/**
 * Unit test for simple App.
 */
public class FillGispFieldsTest
{
    String login;
    String pass;

    WebDriver driver = Driver.getDriver(1);
    @Before
    public void loadConfig() throws IOException {

        Properties property = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/dev.properties");
        property.load(fis);
        login = property.getProperty("user.name");
        pass = property.getProperty("user.password");

    }

    @Test

    public void createNewRecord() throws IOException {
        User user1 = new User(login, pass);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(user1);
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.fillFields();
        driver.close();

    }

    private String getCurrentTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
        return ZonedDateTime.now().format(formatter);
    }

}
