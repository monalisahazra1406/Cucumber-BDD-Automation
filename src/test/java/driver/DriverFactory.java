package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

import java.util.Locale;

public class DriverFactory {

    private static final ThreadLocal<WebDriver>  driver = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);

    public static void initializeDriver(){

        String browser = ConfigReader.getProperty("browser");
        WebDriver localDriver ;

        switch (browser.toLowerCase()){

            case "chrome":
                localDriver = new ChromeDriver();
                break;

            case "firefox":
                localDriver = new FirefoxDriver();
                break;

            case "edge":
                localDriver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: "+ browser);
        }

        driver.set(localDriver);
        LOGGER.info("Initialized {} browser on thread {}",browser, Thread.currentThread().threadId());
        getDriver().manage().window().maximize();

    }

    public static WebDriver getDriver(){

        return driver.get();
    }

    public static void quitDriver() {

        if(getDriver() != null){

            LOGGER.info("Clossing browser on thread {}", Thread.currentThread().threadId());
            getDriver().quit();
            driver.remove();
        }
    }
}
