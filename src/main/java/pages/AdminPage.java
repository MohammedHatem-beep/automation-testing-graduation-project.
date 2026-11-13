
package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPage extends BasePage {

    private final By adminMenu =
            By.xpath("//span[text()='Admin']");

    private final By userManagementMenu =
            By.xpath("//span[text()='User Management ']");

    private final By usersMenu =
            By.xpath("//a[text()='Users']");

    private final By addButton = By.xpath("//button[normalize-space()='Add']");

    private final By userRoleField =
            By.xpath("//label[text()='User Role']/../following-sibling::div");

    private final By employeeNameField =
            By.xpath("//input[@placeholder='Type for hints...']");

    private final By usernameField =
            By.xpath("//label[text()='Username']/../following-sibling::div//input");

    private final By passwordField =
            By.xpath("//label[text()='Password']/../following-sibling::div//input");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void openUsersPage() {
        click(adminMenu);
        click(userManagementMenu);
        click(usersMenu);
    }

    public void clickAddButton() {
        WebElement add = wait.until(
                ExpectedConditions.visibilityOfElementLocated(addButton)
        );

        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public boolean isUserRoleDisplayed() {
        return isDisplayed(userRoleField);
    }

    public boolean isEmployeeNameDisplayed() {
        return isDisplayed(employeeNameField);
    }

    public boolean isUsernameDisplayed() {
        return isDisplayed(usernameField);
    }

    public boolean isPasswordDisplayed() {
        return isDisplayed(passwordField);
    }
}

