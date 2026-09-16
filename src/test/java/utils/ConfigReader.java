package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    static {
         try (FileInputStream file = new FileInputStream("src/test/resources/config.properties"))
         {

             properties.load(file);

         }  catch (IOException e) {
             throw new RuntimeException(
                     "Unable to load config.properties file",
                     e
             );
         }

     }

     public static String getProperty(String key){

        String systemValue = System.getProperty(key);
        if(systemValue != null && !systemValue.isBlank()){
            return systemValue;
        }
         return properties.getProperty(key);

     }

     public static long getLongProperty(String key){
        return Long.parseLong(getProperty(key));
     }
}
