package sidebar;

import base.AuthenticatedBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;

import java.util.List;

public class SidebarTests extends AuthenticatedBaseTest {

    @Test
    public void verifySidebarMenuItems() {

        DashboardPage dashboardPage = new DashboardPage(driver);

        List<String> actualMenuItems = dashboardPage.getSidebarMenuItems();

        List<String> expectedMenuItems = List.of(
                "Admin",
                "PIM",
                "Leave",
                "Time",
                "Recruitment",
                "My Info",
                "Performance",
                "Dashboard",
                "Directory"
        );

        Assert.assertTrue(
                actualMenuItems.containsAll(expectedMenuItems),
                "Sidebar menu is missing one or more expected items!"
        );
    }
}