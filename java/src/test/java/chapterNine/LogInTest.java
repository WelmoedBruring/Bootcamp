package chapterNine;

import chapterSix.TestShopScenario;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.HomePage;

public class LogInTest extends TestShopScenario {

    @Test
    public void logInTest() {
        HomePage homePage = new HomePage(driver);

        if(homePage.isLoggedIn()) {
            homePage.signOut();
        }

        homePage.goToAuthenticationPage().login("bootcamper@feelthepain.com","1qazxsw2");
        Assertions.assertThat(homePage.isLoggedIn());
    }
}
