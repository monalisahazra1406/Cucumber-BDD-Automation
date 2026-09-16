package stepDefinitions;

import driver.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

import java.util.List;
import java.util.Map;

public class LoginStepDefinitions {

    private LoginPage loginPage;

    @Given("the user is on the OrangeHRM login page")
    public void userIsOnOrangeHRMLoginPage() {

        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @When("the user logs in with valid credentials")
    public void userLogsInWithValidCredentials() {

        loginPage.login("Admin", "admin123");
    }

    @Then("the Dashboard should be displayed")
    public void dashboardShouldBeDisplayed() {

        DashboardPage dashboardPage = new DashboardPage(DriverFactory.getDriver());
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard is not displayed");
    }

    @When("the user logs in with username {string} and password {string}")
    public void userLogsInWithInvalidCredentials(String userName, String invalidPassword) {

        loginPage.login(userName, invalidPassword);


    }

    @Then("an invalid credentials message should be displayed")
    public void invalidCredentialsMessageShouldBeDisplayed() {
        Assert.assertTrue(loginPage.isInvalidCredentialsDisplayed(),
                "Invalid credentials message is not displayed");
    }

    @When("the user logs in with the following credentials")
    public void theUserLogsInWithTheFollowingCredentials(DataTable dataTable) {

        Map<String, String> credentials = dataTable.asMap();
        String userName = credentials.get("username");
        String password = credentials.get("password");

        loginPage.login(userName, password);
    }

    @Given("the following login credential records are available")
    public void theFollowingLoginCredentialRecordsAreAvailable(List<Map<String, String>> credentials) {

        for (Map<String, String> credential : credentials) {
            String username = credential.get("username");
            String password = credential.get("password");

            System.out.println("Username- " + username);
            System.out.println("Password- " + password);
        }
    }


/*@Given("the following login credential records are available")
public void theFollowingLoginCredentialRecordsAreAvailable(DataTable dataTable) {

List<Map<String,String>> credentials=dataTable.asMaps(String.class, String.class);
for(Map<String,String> credential:credentials){
String username = credential.get("username");
String password = credential.get("password");

System.out.println("Username- "+username);
System.out.println("Password- "+password);
}
}*/

}
