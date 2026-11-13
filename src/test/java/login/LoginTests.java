
package login;

import base.BaseTests;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.TestDataProvider;

import java.util.List;

public class LoginTests extends BaseTests {


    @Test(dataProvider = "validLoginData",
            dataProviderClass = TestDataProvider.class)
    public void verifyLoginWithValidCredentials(
            String username, String password) {

        LoginPage loginPage = new LoginPage(driver);

        Allure.step("Enter valid login credentials");
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(driver);

        Allure.step("Verify dashboard URL");
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/dashboard/index"),
                "Dashboard URL is incorrect!"
        );

        Allure.step("Verify dashboard header");
        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard header is not displayed!"
        );
    }



    @Test(dataProvider = "invalidLoginData",
            dataProviderClass = TestDataProvider.class)
    public void verifyLoginWithInvalidCredentials(
            String username, String password) {

        LoginPage loginPage = new LoginPage(driver);

        Allure.step("Enter invalid login credentials");
        loginPage.login(username, password);

        Allure.step("Verify invalid credentials message");
        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Invalid credentials",
                "Invalid credentials message is incorrect!"
        );
    }



    @Test
    public void verifyLoginWithEmptyFields() {

        LoginPage loginPage = new LoginPage(driver);

        Allure.step("Click login button without entering credentials");
        loginPage.clickLogin();

        Allure.step("Verify required validation messages");

        List<String> messages = loginPage.getRequiredMessages();

        Assert.assertEquals(
                messages.size(),
                2,
                "Expected two Required validation messages!"
        );

        Assert.assertTrue(
                messages.stream().allMatch(message ->
                        message.equals("Required")),
                "Required validation messages are incorrect!"
        );
    }
}
