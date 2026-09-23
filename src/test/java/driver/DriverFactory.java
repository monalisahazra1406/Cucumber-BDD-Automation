package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

import java.util.Locale;


public class DriverFactory {

    private static final ThreadLocal<WebDriver>  driver = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);

    public static void initializeDriver(){

        String browser = ConfigReader.getProperty("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));
        WebDriver localDriver ;

        switch (browser.toLowerCase()){

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if(headless){
                    chromeOptions.addArguments("--headless=new", "--window-size=1920,1000");
                }
                localDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions fireFoxOptions = new FirefoxOptions();
                if(headless){
                    fireFoxOptions.addArguments("--headless=new", "--window-size=1920,1000");
                }
                localDriver = new FirefoxDriver(fireFoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if(headless){
                    edgeOptions.addArguments("--headless=new", "window-size=1920,1000");
                }
                localDriver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: "+ browser);
        }

        driver.set(localDriver);
        LOGGER.info("Initialized {} browser | Headless:{} | Thread: {}",browser,headless, Thread.currentThread().threadId());
        if(!headless) {
            getDriver().manage().window().maximize();
        }
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
