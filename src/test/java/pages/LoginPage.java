package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //Locators
    private By username = By.name("username");
    private By password = By.name("password");
    private By loginBtn = By.xpath("//button[@type='submit']");
    private By invalidCredentialsMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");


    //Constructor
    public LoginPage (WebDriver driver) {

        this.driver=driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    //Actions
    public void enterUsername(String userNameValue){
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        driver.findElement(username).sendKeys(userNameValue);

    }

    public void enterPassword(String passwordValue){
        wait.until(ExpectedConditions.visibilityOfElementLocated(password));
        driver.findElement(password).sendKeys(passwordValue);
    }

    public DashboardPage clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        driver.findElement(loginBtn).click();
        return new DashboardPage(driver);
    }

    public void login(String userNameValue, String passwordValue){
        enterUsername(userNameValue);
        enterPassword(passwordValue);
        clickLogin();
    }

    public boolean isInvalidCredentialsDisplayed() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(invalidCredentialsMessage))
                    .isDisplayed();
    }

}
