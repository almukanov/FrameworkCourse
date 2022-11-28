package ru.almukanov.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/*
Creating singleton driver
 */
public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver(int index){
        if(driver==null){
            Browsers brow = Browsers.values()[index];
            switch (index){
                case 1:driver =new ChromeDriver(); break;
                case 2:driver =new FirefoxDriver(); break;
            }
        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }

}
