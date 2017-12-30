package homework;

import chapterSix.TestShopScenario;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyAccountPage;
import pages.WishListPage;

public class DeleteWishListTest extends TestShopScenario {

    @Test
    public void deleteWishListTest() {
        HomePage homePage = new HomePage(driver);
        String targetList = "Feel the pain";

        AuthenticationPage authenticationPage = homePage.goToAuthenticationPage();
        MyAccountPage myAccountPage = authenticationPage.login("welmoed@bruring-looge.com", "1qazxsw2");
        WishListPage wishListPage = myAccountPage.goToWishLists();

        wishListPage.deleteWishList(targetList);
        int indexAfterDelete = wishListPage.findWishList(targetList);
        Assertions.assertThat(indexAfterDelete).
                as("The returned index should be 0, indicating that the wish list does not exist").isEqualTo(0);

        wishListPage.addWishList(targetList);
        int indexAfterAdd = wishListPage.findWishList(targetList);
        Assertions.assertThat(indexAfterAdd).
                as("The returned index should be greater 0, indicating that the wish list exists").isGreaterThan(0);
    }
}