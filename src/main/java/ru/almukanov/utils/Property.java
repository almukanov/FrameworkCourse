package ru.almukanov.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
    public static Properties loadConfig()  {
        Properties property = new Properties();
        try {
            FileInputStream fis = new FileInputStream(Profile.DEV.profile);
            property.load(fis);
        }catch (Exception e){
            e.printStackTrace();
        }
        return property;
    }
}
