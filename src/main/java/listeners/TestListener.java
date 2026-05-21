package listeners;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentManager;
import utils.LogUtils;

public class TestListener implements ITestListener {

    private static final Logger log =
        LogUtils.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        log.info("Suite started: " + context.getName());
        ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test started: " + result.getName());
        ExtentManager.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("PASSED: " + result.getName());
        ExtentManager.getTest()
            .log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("FAILED: " + result.getName());

        // Screenshot only for UI tests
        // API tests have no browser
        try {
            String path = utils.ScreenshotUtil.capture(
                utils.DriverManager.getDriver(),
                result.getName());
            ExtentManager.getTest()
                .log(Status.FAIL,
                    result.getThrowable())
                .addScreenCaptureFromPath(path,
                    "Screenshot");
        } catch (Exception e) {
            // API test — no browser — just log failure
            ExtentManager.getTest()
                .log(Status.FAIL,
                    result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("SKIPPED: " + result.getName());
        ExtentManager.getTest()
            .log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Suite finished: " + context.getName());
        ExtentManager.flush();
    }
}