package exercises;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FirstSeleniumTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Test
    private void logInSuccesful() {

        // Open website
        driver.get("https://techblog.polteq.com/testshop/index.php");

        // Find and click the login button
        driver.findElement(By.className("login")).click();

        // Check that the authentication page is opened
        String authenticationHeading = driver.findElement(By.className("page-heading")).getText();
        Assertions.assertThat(authenticationHeading).as("Check authentication page header text").isEqualToIgnoringCase("AUTHENTICATION");

        // Enter email address
        driver.findElement(By.id("email")).sendKeys("welmoed.bruring@polteq.com");

        // Enter password
        driver.findElement(By.id("passwd")).sendKeys("testw8woord");

        // Click login button
        driver.findElement(By.id("SubmitLogin")).click();

        // Check that the My Account-page is opened
        String acountPageHeading = driver.findElement(By.className("page-heading")).getText();
        Assertions.assertThat(acountPageHeading).as("My Account page is not visible").isEqualToIgnoringCase("MY ACCOUNT");

        // Kan ook op een hoger niveau zoeken: nl. het hele WebElement opslaan i.p.v. de tekst van een WebElement
        //WebElement accountPage = driver.findElement(By.className("account"));
    }

    @AfterMethod
    public void cleanUp(){
        driver.quit();
    }
}
