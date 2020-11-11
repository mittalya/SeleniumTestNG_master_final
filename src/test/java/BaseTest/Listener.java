package BaseTest;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import BaseTest.BaseTest;

public class Listener extends BaseTest implements ITestListener {



    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in on TestStart method " +  getTestMethodName(iTestResult) + " start");
    }


    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in on TestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
    }


    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in on TestFailure method " +  getTestMethodName(iTestResult) + " failed");
    }


    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in on TestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }


    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in on Start method " + iTestContext.getName());
    }


    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in on Finish method " + iTestContext.getName());
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

}
