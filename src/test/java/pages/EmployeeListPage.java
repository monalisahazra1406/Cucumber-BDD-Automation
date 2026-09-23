package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class EmployeeListPage {

    private WaitUtils waitUtils;

    public EmployeeListPage(WebDriver driver){
        this.waitUtils = new WaitUtils(driver);
    }
    private By employeeIdInput =
            By.xpath("//label[normalize-space()='Employee Id']/following::input[1]");

    private By searchButton =
            By.xpath("//button[@type='submit' and normalize-space()='Search']");

    public void searchEmployeeById(String employeeId){

        WebElement employeeIdField = waitUtils.waitForElementVisible(employeeIdInput);
        employeeIdField.clear();
        employeeIdField.sendKeys(employeeId);

        waitUtils.waitForElementClickable(searchButton).click();
    }

    public boolean isEmployeeDisplayed(String employeeId){

        By employeeIdInResults = By.xpath("//div[normalize-space()='"+employeeId+"']");
        return waitUtils.waitForElementVisible(employeeIdInResults).isDisplayed();
    }
}
