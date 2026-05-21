package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class WaitUtils {

	private static final int TIMEOUT =
	        ConfigReader.getInt("explicit.wait");

	    private WaitUtils() {}

	    public static WebElement waitForVisibility(
	            WebDriver driver, WebElement element) {
	        return new WebDriverWait(driver,
	            Duration.ofSeconds(TIMEOUT))
	            .until(ExpectedConditions.visibilityOf(element));
	    }

	    public static WebElement waitForClickable(
	            WebDriver driver, WebElement element) {
	        return new WebDriverWait(driver,
	            Duration.ofSeconds(TIMEOUT))
	            .until(ExpectedConditions
	                .elementToBeClickable(element));
	    }
	    public static WebElement waitForVisibility(
	            WebDriver driver, WebElement element,
	            int customTimeout) {
	        return new WebDriverWait(driver,
	            Duration.ofSeconds(customTimeout))
	            .until(ExpectedConditions.visibilityOf(element));
	    }

	    public static boolean waitForUrl(
	            WebDriver driver, String urlPart) {
	        return new WebDriverWait(driver,
	            Duration.ofSeconds(TIMEOUT))
	            .until(ExpectedConditions.urlContains(urlPart));
	    }

	    public static boolean waitForTitle(
	            WebDriver driver, String title) {
	        return new WebDriverWait(driver,
	            Duration.ofSeconds(TIMEOUT))
	            .until(ExpectedConditions.titleContains(title));
	    }  
}
