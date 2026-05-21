package listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import retry.RetryAnalyzer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener
    implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation,
            Class testClass,
            Constructor testConstructor,
            Method testMethod) {

        // Automatically adds RetryAnalyzer
        // to EVERY @Test in ALL classes
        // You never have to add it manually
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}