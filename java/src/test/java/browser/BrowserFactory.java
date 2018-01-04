package browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {

    public enum Browser {
        FIREFOX,
        CHROME;
    }

    // Static: hoef je niet te instantiëren omdat het geen blauwdruk is
    public static WebDriver getDriver(Browser browser) {
        switch(browser) {
            case FIREFOX:
                return getFirefoxDriver();
            case CHROME: default:
                return getChromeDriver();
        }
    }

    private static WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized","ignore-certificate-errors");
        ChromeDriverManager.getInstance().setup();
        return new ChromeDriver(options);

    }

    private static WebDriver getFirefoxDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        FirefoxDriverManager.getInstance().setup();
        return new FirefoxDriver(capabilities);
    }
}
