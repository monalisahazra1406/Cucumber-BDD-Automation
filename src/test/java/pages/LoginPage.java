package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class LoginPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    //Locators
    private By username = By.name("username");
    private By password = By.name("password");
    private By loginBtn = By.xpath("//button[@type='submit']");
    private By invalidCredentialsMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");


    //Constructor
    public LoginPage (WebDriver driver) {

        this.driver=driver;
        this.waitUtils = new WaitUtils(driver);
    }

    //Actions
    public void enterUsername(String userNameValue){
        waitUtils.waitForElementVisible(username).sendKeys(userNameValue);

    }

    public void enterPassword(String passwordValue){

        waitUtils.waitForElementVisible(password).sendKeys(passwordValue);

    }

    public DashboardPage clickLogin() {
        waitUtils.waitForElementClickable(loginBtn).click();
        return new DashboardPage(driver);
    }

    public void login(String userNameValue, String passwordValue){
        enterUsername(userNameValue);
        enterPassword(passwordValue);
        clickLogin();
    }

    public boolean isInvalidCredentialsDisplayed() {

        return waitUtils.waitForElementVisible(invalidCredentialsMessage)
                    .isDisplayed();
    }

}
