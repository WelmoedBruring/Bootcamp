package homework;

import chapterSix.TestShopScenario;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.WishListPage;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteWishListTest extends TestShopScenario {
    private HomePage homePage;
    private String EMAIL = "welmoed@bruring-looge.com";
    private String PASSWORD = "1qazxsw2";
    private String TARGET_WISHLIST = "Feel the pain";

    @Test
    public void deleteWishListTest() {
        homePage = new HomePage(driver);

        WishListPage wishListPage =
                homePage.goToAuthenticationPage()
                        .login(EMAIL, PASSWORD)
                        .goToWishListPage();

        if(!wishListPage.hasWishList(TARGET_WISHLIST)) {
            wishListPage.createWishList(TARGET_WISHLIST);
        }

        wishListPage.deleteWishList(TARGET_WISHLIST);
        assertThat(!wishListPage.hasWishList(TARGET_WISHLIST))
                .as("Target wish list should be deleted but is still found!");

        wishListPage.createWishList(TARGET_WISHLIST);
        assertThat(wishListPage.hasWishList(TARGET_WISHLIST))
                .as("Target wish list should be created but is not found!");
    }
}
