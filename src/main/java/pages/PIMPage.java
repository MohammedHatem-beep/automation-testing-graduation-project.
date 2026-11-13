
package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PIMPage extends BasePage {

    private final By pimMenu =
            By.xpath("//span[text()='PIM']");

    private final By employeeNameField =
            By.xpath("//label[text()='Employee Name']/../following-sibling::div//input");

    private final By searchButton =
            By.xpath("//button[@type='submit']");

    private final By employeeNameSuggestion =
            By.cssSelector(".oxd-autocomplete-option");

    private final By employeeTableRows =
            By.cssSelector(".oxd-table-body .oxd-table-card");

    private final By noRecordsMessage =
            By.xpath("//*[text()='No Records Found']");
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");

    private final By addEmployeeHeader =
            By.xpath("//h6[normalize-space()='Add Employee']");
    private final By firstNameField = By.name("firstName");

    private final By lastNameField = By.name("lastName");
    private final By saveButton =
            By.xpath("//button[@type='submit']");

    private final By firstNameRequiredError =
            By.xpath("//input[@name='firstName']/ancestor::div[contains(@class,'oxd-input-group')]//span[text()='Required']");
    private final By personalDetailsHeader =
            By.xpath("//h6[normalize-space()='Personal Details']");

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public void openPIMModule() {
        click(pimMenu);
    }

    public void enterEmployeeName(String employeeName) {
        sendKeys(employeeNameField, employeeName);
    }

    public void selectEmployeeSuggestion() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameSuggestion));
        click(employeeNameSuggestion);
    }

    public void clickSearch() {
        click(searchButton);
    }

    public boolean isEmployeeDisplayed(String employeeName) {
        return wait.until(driver ->
                driver.findElements(employeeTableRows)
                        .stream()
                        .anyMatch(row -> row.getText()
                                .replaceAll("\\s+", " ")
                                .contains(employeeName))
        );
    }

    public boolean isNoRecordsMessageDisplayed() {
        return isDisplayed(noRecordsMessage);
    }
    public void clickAddButton() {
        click(addButton);
    }

    public boolean isAddEmployeePageDisplayed() {
        return isDisplayed(addEmployeeHeader);
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isFirstNameDisplayed() {
        return isDisplayed(firstNameField);
    }

    public boolean isLastNameDisplayed() {
        return isDisplayed(lastNameField);
    }
    public void enterLastName(String lastName) {
        sendKeys(lastNameField, lastName);
    }

    public void clickSave() {
        click(saveButton);
    }

    public boolean isFirstNameRequiredErrorDisplayed() {
        return isDisplayed(firstNameRequiredError);
    }
    public void enterFirstName(String firstName) {
        sendKeys(firstNameField, firstName);
    }

    public boolean isPersonalDetailsPageDisplayed() {
        return isDisplayed(personalDetailsHeader);
    }

}
