
package listeners;

import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import utils.LoggerUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestListener implements ITestListener, IAnnotationTransformer {

    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtils.info("TEST STARTED: " + result.getName());
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtils.info("TEST PASSED: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtils.error("TEST FAILED: " + result.getName());
        LoggerUtils.error("Failure reason: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtils.info("TEST SKIPPED: " + result.getName());
    }

    @Override
    public void transform(
            ITestAnnotation annotation,
            Class testClass,
            Constructor testConstructor,
            Method testMethod
    ) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

}
