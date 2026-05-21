package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class CheckoutPage {
	private WebDriver driver;

    // Step 1 — info fields
    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    // Step 2 — overview
    @FindBy(css = ".summary_total_label")
    private WebElement totalLabel;

    @FindBy(id = "finish")
    private WebElement finishButton;
 // Step 3 — confirmation
    @FindBy(css = ".complete-header")
    private WebElement confirmationHeader;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillShippingInfo(
            String fn, String ln, String zip) {
        WaitUtils.waitForVisibility(driver, firstName);
        firstName.clear(); firstName.sendKeys(fn);
        lastName.clear(); lastName.sendKeys(ln);
        postalCode.clear(); postalCode.sendKeys(zip);
    }

    public void clickContinue() {
        WaitUtils.waitForClickable(driver, continueButton);
        continueButton.click();
    }
    public String getTotal() {
        WaitUtils.waitForVisibility(driver, totalLabel);
        return totalLabel.getText();
    }

    public void clickFinish() {
        WaitUtils.waitForClickable(driver, finishButton);
        finishButton.click();
    }

    public String getConfirmationMessage() {
        WaitUtils.waitForVisibility(driver, confirmationHeader);
        return confirmationHeader.getText();
    }
}
