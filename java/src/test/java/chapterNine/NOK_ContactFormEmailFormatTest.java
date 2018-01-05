package chapterNine;

import chapterSix.TestShopScenario;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

import static pages.ContactUsPage.SubjectHeading.CUSTOMER_SERVICE;



public class NOK_ContactFormEmailFormatTest extends TestShopScenario {

    private HomePage homePage;
    private String EMAIL_INCORRECT_FORMAT = "nope";
    private String EMAIL_CORRECT_FORMAT = "nope@correct.com";

    @Test
    public void incorrectEmailFormatTest() {
        homePage = new HomePage(driver);

        if(homePage.isLoggedIn()) {
            homePage.signOut();
        }

        ContactUsPage contactUsPage =
                homePage.goToContactUsPage()
                        .fillInEmail(EMAIL_INCORRECT_FORMAT)
                        .selectSubject(CUSTOMER_SERVICE);

        Assertions.assertThat(contactUsPage.formErrorPresent());
    }

    @Test
    public void correctEmailFormatTest() {
        homePage = new HomePage(driver);

        if(homePage.isLoggedIn()) {
            homePage.signOut();
        }

        ContactUsPage contactUsPage =
                homePage.goToContactUsPage()
                        .clearForm()
                        .fillInEmail(EMAIL_CORRECT_FORMAT)
                        .selectSubject(CUSTOMER_SERVICE);

        Assertions.assertThat(contactUsPage.formOKPresent());
    }
}
