package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.LogUtils;
import org.apache.logging.log4j.Logger;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger log =
        LogUtils.getLogger(RetryAnalyzer.class);

    private static final int MAX_RETRY = 2;

    // ThreadLocal — each parallel thread
    // gets its own retry counter
    private ThreadLocal<Integer> count =
        ThreadLocal.withInitial(() -> 0);

    @Override
    public boolean retry(ITestResult result) {
        if (count.get() < MAX_RETRY) {
            log.warn("Retrying test: ["
                + result.getName()
                + "] attempt " + (count.get() + 1)
                + " of " + MAX_RETRY);
            count.set(count.get() + 1);
            return true;   // run again
        }
        count.remove();    // clean up ThreadLocal
        return false;      // mark as FAILED
    }
}