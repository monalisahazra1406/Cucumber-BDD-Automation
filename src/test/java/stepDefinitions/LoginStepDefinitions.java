package stepDefinitions;

import driver.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {

    @Given("the user is on the OrangeHRM login page")
    public void userIsOnOrangeHRMLoginPage() {

        DriverFactory.getDriver().get("https://opensource-demo.orangehrmlive.com/");
    }

    @When("the user logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        System.out.println("User logs in with valid credentials");
    }

    @Then("the Dashboard should be displayed")
    public void dashboardShouldBeDisplayed() {
        System.out.println("Dashboard should be displayed");
    }

    @When("the user logs in with invalid credentials")
    public void userLogsInWithInvalidCredentials() {
        System.out.println("User logs in with invalid credentials");
    }

    @Then("an invalid credentials message should be displayed")
    public void invalidCredentialsMessageShouldBeDisplayed() {
        System.out.println("Invalid credentials message should be displayed");
    }
}
