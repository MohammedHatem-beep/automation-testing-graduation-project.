
package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public static void initDriver() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver webDriver = new ChromeDriver(options);

        driver.set(webDriver);
    }

    public static WebDriver getDriver() {

        if (driver.get() == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialized!"
            );
        }

        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();

            driver.remove();
        }
    }
}
