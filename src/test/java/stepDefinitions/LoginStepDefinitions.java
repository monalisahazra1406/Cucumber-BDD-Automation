package stepDefinitions;

import driver.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginStepDefinitions {

    private LoginPage loginPage;

    @Given("the user is on the OrangeHRM login page")
    public void userIsOnOrangeHRMLoginPage() {

        DriverFactory.getDriver().get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @When("the user logs in with valid credentials")
    public void userLogsInWithValidCredentials() {

        loginPage.login("Admin","admin123");
    }

    @Then("the Dashboard should be displayed")
    public void dashboardShouldBeDisplayed() {

        DashboardPage dashboardPage = new DashboardPage(DriverFactory.getDriver());

        Assert.assertTrue(dashboardPage.isDashboardDisplayed(),"Dashboard is not displayed");
    }

    @When("the user logs in with invalid credentials")
    public void userLogsInWithInvalidCredentials() {

        loginPage.login("Admin","1234");


    }

    @Then("an invalid credentials message should be displayed")
    public void invalidCredentialsMessageShouldBeDisplayed() {
        Assert.assertTrue(loginPage.isInvalidCredentialsDisplayed(),
                "Invalid credentials message is not displayed");
    }
}
