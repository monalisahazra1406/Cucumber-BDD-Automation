package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class DashboardPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    private By dashboardHeading = By.xpath("//h6[text()='Dashboard']");

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public boolean isDashboardDisplayed(){
        return waitUtils.waitForElementVisible(dashboardHeading).isDisplayed();

    }


}
