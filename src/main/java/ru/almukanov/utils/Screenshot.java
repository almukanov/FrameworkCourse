package ru.almukanov.utils;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Screenshot {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
    private static final String data = ZonedDateTime.now().format(formatter);
    public static void makeScreen(WebDriver driver) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File theDir = new File(System.getProperty("user.dir") + "\\screenshots\\screen" + data + ".png");
            if (!theDir.exists()) {
                theDir.getParentFile().mkdirs();
                theDir.createNewFile();
            }
            Files.copy(scrFile, theDir);
        }catch (Exception e){
        e.printStackTrace();
        }
    }
}
