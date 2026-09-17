package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private WebDriverWait wait;

    public WaitUtils(WebDriver driver){

        long timeout = ConfigReader.getLongProperty("explicitWait");

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public WebElement waitForElementVisible(By locator){

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator){

        return wait.until(ExpectedConditions.elementToBeClickable(locator));

    }

    public boolean waitForElementInvisible(By locator){

        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement waitForAttributeNotEmpty(By locator, String attribute){

        WebElement element = waitForElementVisible(locator);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
        return element;
    }
}
