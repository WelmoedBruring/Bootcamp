package homework;

import chapterSix.TestShopScenario;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;

public class DeleteWishListTest extends TestShopScenario {

    @Test
    public void deleteWishListTest() throws Exception {
        // Login and go to wish lists
        login("welmoed@bruring-looge.com","1qazxsw2");
        driver.findElement(By.cssSelector(".icon-heart")).click();

        String listToDelete = "Feel the pain";
        int indexBeforeDelete = findWishList(listToDelete);
        System.out.println("Index before deletion = " + indexBeforeDelete + "\nDeleting wish list...");
        deleteWishList(listToDelete);

        int indexAfterDelete = findWishList(listToDelete);
        System.out.println("Wish list is deleted. \nIndex after deletion = " + indexAfterDelete);
        System.out.println(getRows());


        Assertions.assertThat(indexAfterDelete).
                as("The returned index should be 0, indicating that the wish list does not exist").isEqualTo(0);

        addWishList(listToDelete);

        int indexAfterAdd = findWishList(listToDelete);
        System.out.println("Index after adding = " + indexAfterAdd);
        System.out.println(getRows());

        Assertions.assertThat(indexAfterAdd).
                as("The returned index should be greater 0, indicating that the wish list exists").isGreaterThan(0);
    }

    private void addWishList(String listToDelete) {
        WebElement textbox = driver.findElement(By.cssSelector("#name"));
        textbox.sendKeys(listToDelete);
        driver.findElement(By.cssSelector("#submitWishlist")).click();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table-bordered"))));
    }

    private void deleteWishList(String listToDelete) {
        int index = findWishList(listToDelete);
        if(index > 0) {
//            System.out.println("Index: "+ index);
            WebElement deleteButton = driver.findElement(By.xpath(
                    "(//tbody/tr)[" + (index) + "]/td[@class='wishlist_delete']/a"));
            deleteButton.click();
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
            driver.navigate().refresh();
        } else {
            throw new NoSuchElementException("The wishlist you want to delete does not exist!");
        }
    }

    private int findWishList(String wishList) {
        int index = 0;  // Index is initialized at 0; if returned with value 0 it means that the wish list was not found
        // For each row i in the table body, check if it contains the name of the target wish list
        int numberOfRows = getRows();

        for(int i = 0; i<numberOfRows; i++) {
            WebElement row = driver.findElement(By.xpath("(//tbody/tr)["+(i+1)+"]"));
            if(row.getText().contains(wishList)) {
                index = i + 1; // +1 Because xpath is not 0-based
                break;
            }
        }

        return index;
    }

    // Returns the number of rows in the table
    private int getRows() {
        // Get number of rows
        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
        System.out.println("There are "+ rows.size() + " rows");
        return rows.size();
    }

    private void login(String email, String password) {
        WebElement loginButton = driver.findElement(By.className("login"));
        loginButton.click();

        // Enter credentials
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);

        // Submit login
        driver.findElement(By.id("SubmitLogin")).click();
    }
}