package chapterNine;

import chapterSix.TestShopScenario;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.HomePage;

public class NOK_LoginTest extends TestShopScenario {

    private HomePage homePage;
    private String CORRECT_EMAIL = "welmoed@bruring-looge.com";
    private String INCORRECT_EMAIL_FORMAT = "nope";
    private String UNKNOWN_EMAIL = "hoi@doei.com";
    private String PASSWORD = "1qazxsw2";
    private String INCORRECT_PASSWORD = "bladiebla";
    private String INVALID_PASSWORD = "a";

    @Test
    public void incorrectEmailFormatTest() {
        homePage = new HomePage(driver);

        AuthenticationPage authenticationPage =
                homePage.goToAuthenticationPage()
                        .fillInEmail(INCORRECT_EMAIL_FORMAT)
                        .fillInPassword(PASSWORD)
                        .submitIncorrectLogin();

        Assertions.assertThat(authenticationPage.formErrorPresent())
                .as("Error message of incorrect email format should be displayed");
    }

    @Test
    public void incorrectPasswordTest() {
        homePage = new HomePage(driver);

        AuthenticationPage authenticationPage =
                homePage.goToAuthenticationPage()
                        .fillInEmail(CORRECT_EMAIL)
                        .fillInPassword(INCORRECT_PASSWORD)
                        .submitIncorrectLogin();

        Assertions.assertThat(authenticationPage.getErrorMessage())
                .as("Error message of failed authentications should be displayed")
                .contains("Authentication failed.");
    }

    @Test
    public void unknownUserTest() {
        homePage = new HomePage(driver);

        AuthenticationPage authenticationPage =
                homePage.goToAuthenticationPage()
                        .fillInEmail(UNKNOWN_EMAIL)
                        .fillInPassword(PASSWORD)
                        .submitIncorrectLogin();

        Assertions.assertThat(authenticationPage.getErrorMessage())
                .as("Error message of failed authentications should be displayed")
                .contains("Authentication failed.");
    }

    @Test
    public void emptyEmailFieldTest() {
        homePage = new HomePage(driver);

        AuthenticationPage authenticationPage =
                homePage.goToAuthenticationPage()
                        .fillInPassword(PASSWORD)
                        .submitIncorrectLogin();

        Assertions.assertThat(authenticationPage.getErrorMessage())
                .as("Error message should be displayed because email address can't be empty")
                .contains("An email address required.");
    }

    @Test
    public void invalidEmailAddressTest() {
        homePage = new HomePage(driver);

        AuthenticationPage authenticationPage =
                homePage.goToAuthenticationPage()
                        .fillInEmail(CORRECT_EMAIL)
                        .fillInPassword(INVALID_PASSWORD)
                        .submitIncorrectLogin();

        Assertions.assertThat(authenticationPage.getErrorMessage())
                .as("Error message of invalid password should be displayed")
                .contains("Invalid password.");
    }
}
