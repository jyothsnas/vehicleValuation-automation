package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

//This Utils class is to read the properties files to accommodate the Test execution based
//on the Test environments(we can pass dynamic values)
public class ConfigUtils {
    public static HashMap<String, String> properties;
    //public static String ENV=System.getProperty("env");
    public static String ENV="test";
    public static void setProperties() throws IOException{
        String globalFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "config" +File.separator + "global.properties";
        String envFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "config" +File.separator + ENV +".properties";
        properties=new HashMap<>();
        Properties properties1 = new Properties();
        properties1.load(new FileInputStream(globalFilePath));
        properties1.load(new FileInputStream(envFilePath));
        for (String key: properties1.stringPropertyNames()){
            properties.put(key, properties1.getProperty(key));
        }
    }
}
