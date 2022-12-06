package ru.almukanov.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.almukanov.utils.Browsers;

import java.util.HashMap;

/*
Creating singleton driver
 */
public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver(int index){
        ChromeOptions chromeOptions = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        String downloadFilepath = System.getProperty("user.dir");
        prefs.put("download.default_directory", downloadFilepath);
        chromeOptions.setExperimentalOption("prefs",prefs);
        firefoxOptions.addPreference("prefs",prefs);
        if(driver==null){
            Browsers brow = Browsers.values()[index];
            switch (index){
                case 1:driver=new ChromeDriver(chromeOptions); break;
                case 2:driver=new FirefoxDriver(firefoxOptions); break;
            }
        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }

}
