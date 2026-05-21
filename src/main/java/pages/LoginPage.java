package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import utils.*;


public class LoginPage {
	private WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMsg;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void open() {
        driver.get(ConfigReader.get("base.url"));
    }
    public void enterUsername(String u) {
        WaitUtils.waitForVisibility(driver, usernameField);
        usernameField.clear(); usernameField.sendKeys(u);
    }
    public void enterPassword(String p) {
        passwordField.clear(); passwordField.sendKeys(p);
    }
    public void clickLogin() { loginButton.click(); }
    public void loginWith(String u, String p) {
        enterUsername(u); enterPassword(p); clickLogin();
    }
    public String getErrorMessage() {
        WaitUtils.waitForVisibility(driver, errorMsg);
        return errorMsg.getText();
    }

}
