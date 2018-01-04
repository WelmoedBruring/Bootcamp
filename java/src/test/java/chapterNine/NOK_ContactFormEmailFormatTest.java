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

        ContactUsPage contactUsPage = homePage.goToContactUsPage();
        contactUsPage.partiallyFillInForm(CUSTOMER_SERVICE, EMAIL_INCORRECT_FORMAT);
        Assertions.assertThat(contactUsPage.formErrorPresent());

        contactUsPage.partiallyFillInForm(CUSTOMER_SERVICE, EMAIL_CORRECT_FORMAT);
        Assertions.assertThat(!contactUsPage.formErrorPresent());
    }
}
