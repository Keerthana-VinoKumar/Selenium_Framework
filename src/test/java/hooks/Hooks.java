package hooks;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
import reports.ExtentManager;
import utils.*;
import org.apache.logging.log4j.Logger;
public class Hooks {
	private static final Logger log =
	        LogUtils.getLogger(Hooks.class);
	 @BeforeAll
	    public static void suiteSetup() {
	        ExtentManager.getInstance();
	        log.info("Suite started");
	    }
//only responsible for browser lifecycle
	    @Before
	    public void scenarioSetup(Scenario scenario) {
	    	log.info("Starting:" + scenario.getName());
	        ExtentManager.createTest(scenario.getName());
	        DriverManager.getDriver(); //open browser
	    }

	    @After
	    public void scenarioTeardown(Scenario scenario) {
	    	//Screenshot + reporting
	        if (scenario.isFailed()) {
	        	log.error("FAILED: "+ scenario.getName());
	        	//Attach to cucumber report
	            byte[] ss = ScreenshotUtil
	                .captureAsBytes(DriverManager.getDriver());
	            scenario.attach(ss, "image/png", "Failure");
	            //Attach to Extent Report
	            String path = ScreenshotUtil.capture(
	                DriverManager.getDriver(), scenario.getName());
	            ExtentManager.getTest()
	                .log(Status.FAIL, scenario.getName())
	                .addScreenCaptureFromPath(path,"ScreenShot");
	        } else {
	        	log.info("PASSED:"+ scenario.getName());
	        	ExtentManager.getTest().log(Status.PASS, "Passed");
	        }
	        DriverManager.quitDriver(); //close browser
	    }

	    @AfterAll
	    public static void suiteTeardown() {
	        ExtentManager.flush();
	        log.info("Suite finished");
	    }

}
