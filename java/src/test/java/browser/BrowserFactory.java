package browser;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    // Moet static omdat getDriver ook static is
    static WebDriver driver;

    // Static: hoef je niet te instantiëren omdat het geen blauwdruk is
    public static WebDriver getDriver(String browser) {
        switch(browser.toUpperCase()) {
            case "FIREFOX":
                driver = getFirefoxDriver();
                break;
            case "CHROME":
                driver = getChromeDriver();
                break;
            default:
                driver = getChromeDriver();
                break;
        }
        return driver;
    }

    private static WebDriver getChromeDriver() {
        ChromeDriverManager.getInstance().setup();
        return driver = new ChromeDriver();
    }

    private static WebDriver getFirefoxDriver() {
        FirefoxDriverManager.getInstance().setup();
        return driver = new FirefoxDriver();
    }
}
