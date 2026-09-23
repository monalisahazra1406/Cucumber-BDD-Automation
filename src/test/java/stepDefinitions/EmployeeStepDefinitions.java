package stepDefinitions;

import driver.DriverFactory;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.*;

public class EmployeeStepDefinitions {

    private SideMenu sideMenu;
    private PIMPage pimPage;
    private AddEmployeePage addEmployeePage;
    private String generatedEmployeeId;
    private PersonalDetailsPage personalDetailsPage;
    private EmployeeListPage employeeListPage;

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
        System.out.println("Generated Employeee id----- " + generatedEmployeeId);
        addEmployeePage.clickOnSaveBtn();
    }

    @Then("the employee should be created successfully")
    public void theEmployeeShouldBeCreatedSuccessfully() {

        personalDetailsPage = new PersonalDetailsPage(DriverFactory.getDriver());
        Assert.assertTrue(personalDetailsPage.isPersonalDetailsDisplayed(), "Personal Details page was not displayed after employee creation");
        ;
    }

    @And("the employee should be searchable using the generated employee ID")
    public void theEmployeeShouldBeSearchableUsingTheGeneratedEmployeeID() {

        Assert.assertNotNull(generatedEmployeeId, "Generated employee ID was null");
        Assert.assertFalse(generatedEmployeeId.isBlank(), "Generated employee ID was blank");

        pimPage = new PIMPage(DriverFactory.getDriver());
        pimPage.navigateToEmployeeList();

        employeeListPage = new EmployeeListPage(DriverFactory.getDriver());
        employeeListPage.searchEmployeeById(generatedEmployeeId);

        Assert.assertTrue(employeeListPage.isEmployeeDisplayed(generatedEmployeeId), "Employee with id " + generatedEmployeeId + "was not found in list");
    }
}
