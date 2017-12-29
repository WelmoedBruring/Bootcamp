package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class EmptyCartTest extends TestShopScenario {

    @Test
    public void emptyCartTest() {

        WebElement empty = driver.findElement(By.className("ajax_cart_no_product"));

        if(empty.isDisplayed()) {
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
        } else {
            System.out.println("Cart is not empty!");
        }

        // Empty shopping cart
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".shopping_cart>a")));
        driver.navigate().refresh();
        driver.findElement(By.cssSelector(".shopping_cart>a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("icon-trash")));
        driver.findElement(By.className("icon-trash")).click();

//        Code van Robert
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart")));
//        System.out.println("Producten in cart :"+driver.findElement(By.className("ajax_cart_quantity")).getText());
//        driver.navigate().refresh();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='View my shopping cart']")));
//        driver.findElement(By.xpath("//a[@title='View my shopping cart']")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("icon-trash")));
//        driver.findElement(By.className("icon-trash")).click();

        // Check that the cart is empty
        empty = driver.findElement(By.className("ajax_cart_no_product"));
        Assertions.assertThat(empty.isDisplayed());
    }
}
