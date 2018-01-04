package chapterNine;

import chapterSix.TestShopScenario;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

import static pages.ContactUsPage.SubjectHeading.CUSTOMER_SERVICE;

public class NOK_FillInContactFormTest extends TestShopScenario {

    HomePage homePage;

    private String EMAIL_INCORRECT_FORMAT = "nope";
    private String EMAIL_CORRECT_FORMAT = "nope@correct.com";

    @Test
    public void submitContactWithIncorrectEmailFormatTest() {
        homePage = new HomePage(driver);

        if(homePage.isLoggedIn()) {
            homePage.signOut();
        }

        ContactUsPage contactUsPage = homePage.goToContactUsPage();
        contactUsPage.submitForm(
                CUSTOMER_SERVICE,
                EMAIL_INCORRECT_FORMAT,
                "4321234",
                "Help!");
        Assertions.assertThat(contactUsPage.getErrorMessage()).contains("Invalid email address.");

        contactUsPage.submitForm(
                CUSTOMER_SERVICE,
                EMAIL_CORRECT_FORMAT,
                "4321234",
                "Help!");
        Assertions.assertThat(contactUsPage.getSuccessMessage()).contains(
                "Your message has been successfully sent to our team.");
    }
}
