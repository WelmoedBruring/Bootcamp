package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class FillCartTest extends TestShopScenario {

    private String authenticationHeading;

    @Test
    public void fillCartTest() {

        // Check that the cart is empty
        WebElement empty = driver.findElement(By.className("ajax_cart_no_product"));
        Assertions.assertThat(empty.isDisplayed());

        // Search for iPod
        driver.findElement(By.cssSelector("[title='More about ipod']")).click();

        // Click on iPod Shuffle
        driver.findElement(By.xpath("//div[@class='product-container']/div/h5/a[contains(text(), 'iPod shuffle')]")).click();

        // Add iPod Shuffle to cart
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_to_cart")));
        driver.findElement(By.id("add_to_cart")).click();

        // Continue shopping
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(., 'Continue shopping')]")));
        driver.findElement(By.xpath("//span[contains(., 'Continue shopping')]")).click();

        // Validate number of items in shopping cart
        String cartQuantity =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ajax_cart_quantity")))
                        .getText();

        String expectedQuantity = "1";
        Assertions.assertThat(cartQuantity.equals(expectedQuantity));
    }
}
