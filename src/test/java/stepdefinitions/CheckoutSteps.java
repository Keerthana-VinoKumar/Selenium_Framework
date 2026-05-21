package stepdefinitions;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.*;
import utils.DriverManager;

public class CheckoutSteps {
	private LoginPage loginPage;
    private ProductPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public CheckoutSteps() {
        loginPage = new LoginPage(DriverManager.getDriver());
        productsPage = new ProductPage(DriverManager.getDriver());
        cartPage = new CartPage(DriverManager.getDriver());
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
    }

    @Given("user is on the SauceDemo login page")
    public void openLoginPage() {
        loginPage.open();
    }

    @When("user logs in with {string} and {string}")
    public void loginWith(String user, String pass) {
        loginPage.loginWith(user, pass);
    }
    @Then("products page should be displayed")
    public void verifyProductsPage() {
        Assert.assertTrue(productsPage.getTitle()
            .contains("Products"));
    }

    @When("user adds {string} to cart")
    public void addToCart(String product) {
        productsPage.addToCartByName(product);
    }

    @Then("cart should have {int} items")
    public void verifyCartCount(int count) {
        Assert.assertEquals(productsPage.getCartCount(), count);
    }

    @When("user proceeds to cart")
    public void goToCart() { productsPage.goToCart(); }

    @When("user proceeds to checkout")
    public void goToCheckout() { cartPage.proceedToCheckout(); }
    @When("user fills shipping info {string} {string} {string}")
    public void fillShipping(String fn, String ln, String zip) {
        checkoutPage.fillShippingInfo(fn, ln, zip);
    }

    @When("user clicks continue")
    public void clickContinue() { checkoutPage.clickContinue(); }

    @Then("order total should be displayed")
    public void verifyTotal() {
        Assert.assertNotNull(checkoutPage.getTotal());
    }

    @When("user clicks finish")
    public void clickFinish() { checkoutPage.clickFinish(); }

    @Then("order confirmation should show {string}")
    public void verifyConfirmation(String expected) {
        Assert.assertTrue(checkoutPage
            .getConfirmationMessage().contains(expected));
    }
    @Then("error message {string} should appear")
    public void verifyError(String expected) {
        Assert.assertTrue(loginPage
            .getErrorMessage().contains(expected));
    }

}
