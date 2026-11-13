
package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DashboardPage extends BasePage {
    private final By sidebarMenuItems =
            By.cssSelector(".oxd-main-menu-item span");
    private final By dashboardHeader =
            By.xpath("//h6[text()='Dashboard']");
    private final By footerText =
            By.xpath("//a[normalize-space()='OrangeHRM, Inc']");

    private final By brandingLink =
            By.xpath("//a[normalize-space()='OrangeHRM, Inc']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    public boolean isDashboardDisplayed() {
        return isDisplayed(dashboardHeader);
    }
    public String getDashboardHeader() {
        return getText(dashboardHeader);
    }
    public String getFooterText() {
        return getText(footerText);
    }

    public void clickBrandingLink() {
        click(brandingLink);
    }

    public String switchToNewTab() {
        String originalWindow = driver.getWindowHandle();

        wait.until(driver -> driver.getWindowHandles().size() == 2);

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        return driver.getCurrentUrl();
    }
    public void scrollToFooter() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                waitForElementVisible(footerText)
        );
    }
    public List<String> getSidebarMenuItems() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(sidebarMenuItems));

        return driver.findElements(sidebarMenuItems)
                .stream()
                .map(element -> element.getText().trim())
                .toList();
    }
}
