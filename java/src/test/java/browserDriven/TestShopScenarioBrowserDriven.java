package browserDriven;

import browser.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.HomePage;


public class TestShopScenarioBrowserDriven {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private String URL = "https://techblog.polteq.com/testshop/index.php";

    @Parameters("browser")
    @BeforeMethod
    public void setUp(BrowserFactory.Browser browser) {
        driver = BrowserFactory.getDriver(browser);

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
