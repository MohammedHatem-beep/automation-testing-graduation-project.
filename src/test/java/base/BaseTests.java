
package base;
import utils.LoggerUtils;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTests {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        LoggerUtils.info("========== Test Setup Started ==========");

        DriverManager.initDriver();
        driver = DriverManager.getDriver();

        driver.get(ConfigReader.getProperty("base.url"));

        LoggerUtils.info("Browser launched and application opened successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        LoggerUtils.info("Closing browser");
        DriverManager.quitDriver();
        LoggerUtils.info("Browser closed successfully");
    }
}
