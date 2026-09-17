package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class PIMPage {

    private WaitUtils waitUtils;

    private By addEmployeeLink = By.linkText("Add Employee");

    public PIMPage(WebDriver driver){
        this.waitUtils = new WaitUtils(driver);

    }

    public void clickOnAddEmployeeLink(){
        waitUtils.waitForElementClickable(addEmployeeLink).click();
    }

}
