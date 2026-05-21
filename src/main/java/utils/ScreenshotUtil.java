package utils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class ScreenshotUtil {
	
	private static final Logger log =
	        LogUtils.getLogger(ScreenshotUtil.class);

	    private ScreenshotUtil() {}

	    // Saves screenshot to file — returns path
	    // Used to attach path to Extent Report
	    public static String capture(
	            WebDriver driver, String testName) {

	        String timestamp = LocalDateTime.now().format(
	            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

	        String filePath = "reports/screenshots/"
	            + testName + "_" + timestamp + ".png";

	        try {
	            byte[] screenshot = ((TakesScreenshot) driver)
	                .getScreenshotAs(OutputType.BYTES);
	            Files.createDirectories(
	                    Paths.get("reports/screenshots"));
	                Files.write(Paths.get(filePath), screenshot);

	                log.info("Screenshot saved: " + filePath);

	            } catch (IOException e) {
	                log.error("Screenshot failed: " + testName, e);
	            }

	            return filePath;
	        }

	        // Returns raw bytes — used to attach
	        // directly inside Cucumber scenario report
	        public static byte[] captureAsBytes(WebDriver driver) {
	            return ((TakesScreenshot) driver)
	                .getScreenshotAs(OutputType.BYTES);
	        }
}
