package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class SideMenu {

    private WaitUtils waitUtils;

    private By pimMenu = By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a/span");

    public SideMenu(WebDriver driver){

        this.waitUtils=new WaitUtils(driver);
    }

    public void clickOnPimMenu(){
        waitUtils.waitForElementClickable(pimMenu).click();
    }

}
