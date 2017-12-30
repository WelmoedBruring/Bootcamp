package chapterSix;

import browser.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestShopScenario {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private String URL = "https://techblog.polteq.com/testshop/index.php";

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getDriver("Chrome");

        // Maximize browser window
        driver.manage().window().maximize();

        // Make new wait
        wait = new WebDriverWait(driver, 15);

        // Open the website
        driver.get(URL);
    }

    @AfterMethod
    public void cleanUp() {
        //driver.quit();
    }
}
