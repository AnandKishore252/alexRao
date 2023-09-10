package happyfox.helpdesk_automation.listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import happyfox.helpdesk_automation.SystemService;

public class TestListener implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("Test Passed: " + getTestMethodName(iTestResult));
	}

	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("Test Fail: " + getTestMethodName(iTestResult));
		SystemService.FailedSS(iTestResult.getName());

	}

	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("Test Skipped: " + getTestMethodName(iTestResult));
	}
	

}