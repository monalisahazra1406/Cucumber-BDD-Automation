package stepDefinitions;

import driver.DriverFactory;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddEmployeePage;
import pages.PIMPage;
import pages.SideMenu;

public class EmployeeStepDefinitions {

    private SideMenu sideMenu;
    private PIMPage pimPage;
    private AddEmployeePage addEmployeePage;
    private String generatedEmployeeId;

    @When("the user navigates to the PIM module")
    public void userNavigatesToPIMModule() {

        sideMenu = new SideMenu(DriverFactory.getDriver());
        sideMenu.clickOnPimMenu();
    }

    @And("the user adds a new employee with first name {string} and last name {string}")
    public void theUserAddsANewEmployeeWithFirstNameAndLastName(String firstName, String lastName) {

        pimPage = new PIMPage(DriverFactory.getDriver());
        pimPage.clickOnAddEmployeeLink();

        addEmployeePage = new AddEmployeePage(DriverFactory.getDriver());
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterLastName(lastName);

        generatedEmployeeId = addEmployeePage.getEmployeeId();
        addEmployeePage.clickOnSaveBtn();
    }

    @Then("the employee should be created successfully")
    public void theEmployeeShouldBeCreatedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the employee should be searchable using the generated employee ID")
    public void theEmployeeShouldBeSearchableUsingTheGeneratedEmployeeID() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
