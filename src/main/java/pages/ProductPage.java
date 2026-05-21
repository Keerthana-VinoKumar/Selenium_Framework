package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class ProductPage {
	 private WebDriver driver;

	    @FindBy(css = ".title")
	    private WebElement pageTitle;

	    @FindBy(css = ".shopping_cart_link")
	    private WebElement cartIcon;

	    @FindBy(css = ".select_container")
	    private WebElement sortDropdown;

	    public ProductPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	    public String getTitle() {
	        WaitUtils.waitForVisibility(driver, pageTitle);
	        return pageTitle.getText();
	    }

	    // Add product by its name
	    public void addToCartByName(String productName) {
	        String xpath = "//div[text()='" + productName
	            + "']/ancestor::div[@class='inventory_item']"
	            + "//button[text()='Add to cart']";
	        driver.findElement(By.xpath(xpath)).click();
	    }

	    public void goToCart() { cartIcon.click(); }

	    public List<String> getAllProductNames() {
	        return driver.findElements(
	            By.cssSelector(".inventory_item_name"))
	            .stream().map(WebElement::getText)
	            .collect(Collectors.toList());
	    }
	    public int getCartCount() {
	        String count = driver.findElement(
	            By.cssSelector(".shopping_cart_badge")).getText();
	        return Integer.parseInt(count);
	    }
}
