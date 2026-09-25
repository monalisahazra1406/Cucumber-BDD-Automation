package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class AddEmployeePage {

    private WaitUtils waitUtils;

    private By firstName = By.name("firstName");
    private By lastName = By.name("lastName");
    private By employeeId = By.xpath("//label[normalize-space()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private By saveBtn = By.xpath("//button[normalize-space()='Save']");
    private By formLoader = By.className("oxd-form-loader");

    public AddEmployeePage(WebDriver driver){
        this.waitUtils = new WaitUtils(driver);
    }

    public void enterFirstName(String firstNameValue){
        waitUtils.waitForElementVisible(firstName).sendKeys(firstNameValue);
    }

    public  void enterLastName(String lastNameValue){
        waitUtils.waitForElementVisible(lastName).sendKeys(lastNameValue);
    }

    public String getEmployeeId(){
        return waitUtils.waitForAttributeNotEmpty(employeeId,"value")
                .getDomProperty("value");
    }

    public void clickOnSaveBtn(){
        waitUtils.waitForElementInvisible(formLoader);
        waitUtils.waitForElementClickable(saveBtn).click();
    }

    public String enterUniqueEmployeeId(){

        String uniqueEmployeeId = String.valueOf(System.currentTimeMillis() % 100000000L);
        WebElement employeeField = waitUtils.waitForElementVisible(employeeId);
        waitUtils.waitForElementInvisible(formLoader);
        employeeField.click();
        employeeField.sendKeys(Keys.CONTROL,"a");
        employeeField.sendKeys(Keys.DELETE);
        employeeField.sendKeys(uniqueEmployeeId);
        return uniqueEmployeeId;
    }


}
