
package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class LoginPage extends BasePage {

    // Locators
    private final By usernameField = By.name("username");

    private final By passwordField = By.name("password");

    private final By loginButton =
            By.cssSelector("button[type='submit']");

    private final By errorMessage =
            By.cssSelector(".oxd-alert-content-text");

    private final By requiredMessages =
            By.cssSelector(".oxd-input-field-error-message");


    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    // Actions

    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {

        enterUsername(username);

        enterPassword(password);

        clickLogin();
    }


    // Validations / Getters

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public List<String> getRequiredMessages() {

        wait.until(
                org.openqa.selenium.support.ui.ExpectedConditions
                        .numberOfElementsToBe(requiredMessages, 2)
        );

        return driver.findElements(requiredMessages)
                .stream()
                .map(element -> element.getText())
                .toList();
    }

}
