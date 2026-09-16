package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;

import java.util.Locale;

public class DriverFactory {

    private static final ThreadLocal<WebDriver>  driver = new ThreadLocal<>();

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
        System.out.println("Initialized -> Thread: "+ Thread.currentThread().threadId()
        +" Driver : " + System.identityHashCode(getDriver()));
        getDriver().manage().window().maximize();

    }

    public static WebDriver getDriver(){

        return driver.get();
    }

    public static void quitDriver() {

        if(getDriver() != null){

            System.out.println(
                    "QUITTING -> Thread: "
                            + Thread.currentThread().threadId()
                            + " | Driver: "
                            + System.identityHashCode(getDriver())
            );
            getDriver().quit();
            driver.remove();
        }
    }
}
