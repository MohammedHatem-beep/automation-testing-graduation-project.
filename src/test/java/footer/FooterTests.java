package footer;

import base.AuthenticatedBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;

public class FooterTests extends AuthenticatedBaseTest {

    @Test
    public void verifyOrangeHRMFooterBrandingLink() {

        DashboardPage dashboardPage = new DashboardPage(driver);


        dashboardPage.scrollToFooter();


        String footerText = dashboardPage.getFooterText();

        Assert.assertTrue(
                footerText.contains("OrangeHRM, Inc"),
                "Footer text does not contain OrangeHRM, Inc!"
        );

        dashboardPage.clickBrandingLink();

        String newTabUrl = dashboardPage.switchToNewTab();


        Assert.assertTrue(
                newTabUrl.contains("orangehrm.com"),
                "New tab URL does not contain orangehrm.com!"
        );
    }
}
