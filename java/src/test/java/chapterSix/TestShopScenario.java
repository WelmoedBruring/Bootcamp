package chapterSix;

import browser.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;


public class TestShopScenario {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private String URL = "https://techblog.polteq.com/testshop/index.php";

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getDriver(BrowserFactory.Browser.CHROME);

        // Make new wait
        wait = new WebDriverWait(driver, 15);

        // Open the website
        driver.get(URL);

        HomePage homePage = new HomePage(driver);
        if(homePage.isLoggedIn()) {
            homePage.signOut();
        }
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
}
