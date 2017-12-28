package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class SignOutTest extends TestShopScenario {

    private String authenticationHeading;

    @Test
    private void signOutTest() {

        // Open URL
        driver.get("https://techblog.polteq.com/testshop/index.php");

        // Find and click the login button
        driver.findElement(By.className("login")).click();

        // Check that the authentication page is opened
        authenticationHeading = driver.findElement(By.className("page-heading"))
                .getText();
        Assertions.assertThat(authenticationHeading)
                .as("Authentication page should be displayed")
                .isEqualToIgnoringCase("AUTHENTICATION");

        // Enter email address
        driver.findElement(By.id("email"))
                .sendKeys("welmoed.bruring@polteq.com");

        // Enter password
        driver.findElement(By.id("passwd"))
                .sendKeys("testw8woord");

        // Click login button
        driver.findElement(By.id("SubmitLogin")).click();

        // Check that the My Account-page is opened
        String signOutButtonText = driver.findElement(By.className("logout"))
                .getText();
        Assertions.assertThat(signOutButtonText)
                .as("Sign out button should be displayed")
                .isEqualToIgnoringCase("SIGN OUT");

        // Find and click the logout button
        driver.findElement(By.className("logout")).click();

        // Check that the logout is succesful and the authentication page is opened
        String signInButtonText = driver.findElement(By.className("login"))
                .getText();
        Assertions.assertThat(signInButtonText)
                .as("Sign in button should be displayed")
                .isEqualToIgnoringCase("SIGN IN");
    }
}
