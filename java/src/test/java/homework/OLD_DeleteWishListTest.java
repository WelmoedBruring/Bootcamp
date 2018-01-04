package homework;

import chapterSix.TestShopScenario;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyAccountPage;
import pages.OLD_WishListPage;

public class OLD_DeleteWishListTest extends TestShopScenario {

    @Test
    public void deleteWishListTest() {
        HomePage homePage = new HomePage(driver);
        String targetList = "Feel the pain";

        AuthenticationPage authenticationPage = homePage.goToAuthenticationPage();
        MyAccountPage myAccountPage = authenticationPage.login("welmoed@bruring-looge.com", "1qazxsw2");
        OLD_WishListPage OLDWishListPage = myAccountPage.goToOLDWishListsPage();

        OLDWishListPage.deleteWishList(targetList);
        int indexAfterDelete = OLDWishListPage.findWishList(targetList);
        Assertions.assertThat(indexAfterDelete).
                as("The returned index should be 0, indicating that the wish list does not exist").isEqualTo(0);

        OLDWishListPage.addWishList(targetList);
        int indexAfterAdd = OLDWishListPage.findWishList(targetList);
        Assertions.assertThat(indexAfterAdd).
                as("The returned index should be greater 0, indicating that the wish list exists").isGreaterThan(0);
    }
}