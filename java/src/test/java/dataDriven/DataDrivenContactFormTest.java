package dataDriven;

import chapterSix.TestShopScenario;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

public class DataDrivenContactFormTest extends TestShopScenario{

    // VARIABLES
    private String EMAIL = "bootcamper@feelthepain.com";
    private String ORDER_REFERENCE = "4321234";
    private String MESSAGE = "Ipod defect while lifting, need new one";
    private String EXPECTED_SUCCESMESSAGE = "Your message has been successfully sent to our team.";

    // PAGES
    private HomePage homePage;
    private ContactUsPage contactUsPage;

    @Test @Parameters({"email", "reference", "message", "subject"})
    public void fillInContactForm(String email, String reference, String message, ContactUsPage.SubjectHeading subject) {

        homePage = new HomePage(driver);

        if(homePage.isLoggedIn()) {
            homePage.signOut();
        }

        ContactUsPage contactUsPage =
                homePage.goToContactUsPage()
                        .selectSubject(subject)
                        .fillInEmail(email)
                        .fillInReference(reference)
                        .fillInMessage(message)
                        .submitForm();

        Assertions.assertThat(
                contactUsPage.getSuccessMessage())
                .as("Success message should match expected success message")
                .isEqualTo(EXPECTED_SUCCESMESSAGE);
    }
}
