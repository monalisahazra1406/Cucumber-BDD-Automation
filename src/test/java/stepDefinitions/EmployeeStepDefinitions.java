package stepDefinitions;

import driver.DriverFactory;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import pages.*;

public class EmployeeStepDefinitions {

    private SideMenu sideMenu;
    private PIMPage pimPage;
    private AddEmployeePage addEmployeePage;
    private String generatedEmployeeId;
    private PersonalDetailsPage personalDetailsPage;
    private EmployeeListPage employeeListPage;

    private static final Logger logger  = LoggerFactory.getLogger(EmployeeStepDefinitions.class);


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

        generatedEmployeeId = addEmployeePage.enterUniqueEmployeeId();
        logger.info("Generated Employee ID: {}", generatedEmployeeId);
        addEmployeePage.clickOnSaveBtn();
        logger.info(
                "URL after clicking Save: {}",
                DriverFactory.getDriver().getCurrentUrl()
        );

        logger.info(
                "Page title after clicking Save: {}",
                DriverFactory.getDriver().getTitle()
        );
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
