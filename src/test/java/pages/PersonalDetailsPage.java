package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class PersonalDetailsPage {

    private WaitUtils waitUtils;

    public PersonalDetailsPage(WebDriver driver){
       this.waitUtils = new WaitUtils(driver);
    }

    private By personalDetailHeading =  By.xpath("//h6[normalize-space()='Personal Details']");

    public boolean isPersonalDetailsDisplayed(){
       return waitUtils.waitForElementVisible(personalDetailHeading).isDisplayed();
    }



}
