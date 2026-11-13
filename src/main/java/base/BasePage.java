
package base;
import utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {

        this.driver = driver;

        int waitTime = Integer.parseInt(
                ConfigReader.getProperty("explicit.wait")
        );

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(waitTime)
        );
    }


    protected WebElement waitForElementVisible(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }


    protected WebElement waitForElementClickable(By locator) {

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }


    protected void click(By locator) {
        LoggerUtils.info("Clicking on element: " + locator);
        waitForElementClickable(locator).click();
    }


    protected void sendKeys(By locator, String text) {
        LoggerUtils.info("Entering text into element: " + locator);
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }


    protected String getText(By locator) {
        LoggerUtils.info("Getting text from element: " + locator);
        return waitForElementVisible(locator).getText();
    }


    protected boolean isDisplayed(By locator) {

        return waitForElementVisible(locator).isDisplayed();
    }

}
