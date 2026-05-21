package utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
public class DriverManager {
	private static final org.apache.logging.log4j.Logger log =
	        LogUtils.getLogger(DriverManager.class);

	    private static ThreadLocal<WebDriver> driver =
	        new ThreadLocal<>();

	    private DriverManager() {}

	    public static WebDriver getDriver() {
	        if (driver.get() == null) {
	            initDriver();
	        }
	        return driver.get();
	    }

	    private static void initDriver() {
	        String browser = ConfigReader.get("browser")
	                                     .toLowerCase();
	        log.info("Launching browser: " + browser);
	        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                driver.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver.set(new FirefoxDriver(firefoxOptions));
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                driver.set(new EdgeDriver(edgeOptions));
                break;
            default:
                throw new RuntimeException(
                    "Browser not supported: " + browser);
        }
        log.info("Browser launched successfully");
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            log.info("Closing browser");
            driver.get().quit();
            driver.remove();
        }
    }
}
