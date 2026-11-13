package admin;

import base.AuthenticatedBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;

public class AdminTests extends AuthenticatedBaseTest {

    @Test
    public void verifyAddUserPageFields() {

        AdminPage adminPage = new AdminPage(driver);
        System.out.println("Current URL: " + driver.getCurrentUrl());
        adminPage.openUsersPage();
        adminPage.clickAddButton();

        Assert.assertTrue(
                adminPage.isUserRoleDisplayed(),
                "User Role field is not displayed!"
        );

        Assert.assertTrue(
                adminPage.isEmployeeNameDisplayed(),
                "Employee Name field is not displayed!"
        );

        Assert.assertTrue(
                adminPage.isUsernameDisplayed(),
                "Username field is not displayed!"
        );

        Assert.assertTrue(
                adminPage.isPasswordDisplayed(),
                "Password field is not displayed!"
        );
    }
}