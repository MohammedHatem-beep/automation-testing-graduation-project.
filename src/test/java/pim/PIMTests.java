package pim;
import base.AuthenticatedBaseTest;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PIMPage;

public class PIMTests extends AuthenticatedBaseTest {

    @Test
    public void verifySearchForExistingEmployee() {



        PIMPage pimPage = new PIMPage(driver);

        Allure.step("Open PIM module");
        pimPage.openPIMModule();

        Allure.step("Enter existing employee name");
        pimPage.enterEmployeeName("A8DCo 4Ys 010Z");

        Allure.step("Select employee from autocomplete suggestions");
        pimPage.selectEmployeeSuggestion();

        Allure.step("Click Search button");
        pimPage.clickSearch();

        Allure.step("Verify employee appears in search results");
        Assert.assertTrue(
                pimPage.isEmployeeDisplayed("A8DCo 4Ys 010Z"),
                "Employee was not found in search results!"
        );
    }

    @Test
    public void verifySearchForNonExistingEmployee() {


        PIMPage pimPage = new PIMPage(driver);

        Allure.step("Open PIM module");
        pimPage.openPIMModule();

        Allure.step("Enter non-existing employee name");
        pimPage.enterEmployeeName("ZZZ NonExisting Employee 999");

        Allure.step("Click Search button");
        pimPage.clickSearch();

        Allure.step("Verify No Records Found message");
        Assert.assertTrue(
                pimPage.isNoRecordsMessageDisplayed(),
                "No Records Found message was not displayed!"
        );
    }
    @Test
    public void verifyOpenAddEmployeePage() {

        PIMPage pimPage = new PIMPage(driver);

        Allure.step("Open PIM module");
        pimPage.openPIMModule();

        Allure.step("Click Add button");
        pimPage.clickAddButton();

        Allure.step("Verify URL contains /pim/addEmployee");
        Assert.assertTrue(
                pimPage.getCurrentUrl().contains("/pim/addEmployee"),
                "URL does not contain /pim/addEmployee"
        );

        Allure.step("Verify First Name field is displayed");
        Assert.assertTrue(
                pimPage.isFirstNameDisplayed(),
                "First Name field is not displayed!"
        );

        Allure.step("Verify Last Name field is displayed");
        Assert.assertTrue(
                pimPage.isLastNameDisplayed(),
                "Last Name field is not displayed!"
        );
    }
    @Test
    public void verifyFirstNameRequiredValidation() {

        PIMPage pimPage = new PIMPage(driver);

        Allure.step("Open PIM module");
        pimPage.openPIMModule();

        Allure.step("Open Add Employee page");
        pimPage.clickAddButton();

        Allure.step("Enter Last Name only");
        pimPage.enterLastName("Hatem");

        Allure.step("Click Save button");
        pimPage.clickSave();

        Allure.step("Verify Required validation under First Name");
        Assert.assertTrue(
                pimPage.isFirstNameRequiredErrorDisplayed(),
                "First Name Required validation message was not displayed!"
        );
    }
    @Test
    public void verifySuccessfullyAddEmployee() {

        PIMPage pimPage = new PIMPage(driver);

        // Generate unique employee data
        String firstName = "Auto" + System.currentTimeMillis();
        String lastName = "Test";
        String fullName = firstName + " " + lastName;

        Allure.step("Open PIM module");
        pimPage.openPIMModule();

        Allure.step("Navigate to Add Employee page");
        pimPage.clickAddButton();

        Allure.step("Enter First Name and Last Name");
        pimPage.enterFirstName(firstName);
        pimPage.enterLastName(lastName);

        Allure.step("Click Save button");
        pimPage.clickSave();

        Allure.step("Verify Personal Details page is displayed");
        Assert.assertTrue(
                pimPage.isPersonalDetailsPageDisplayed(),
                "Personal Details page was not displayed!"
        );

        Allure.step("Navigate back to Employee List");
        pimPage.openPIMModule();

        Allure.step("Search for the newly created employee");
        pimPage.enterEmployeeName(fullName);
        pimPage.selectEmployeeSuggestion();
        pimPage.clickSearch();

        Allure.step("Verify the new employee appears in search results");
        Assert.assertTrue(
                pimPage.isEmployeeDisplayed(fullName),
                "Newly created employee was not found in Employee List!"
        );
    }
}
