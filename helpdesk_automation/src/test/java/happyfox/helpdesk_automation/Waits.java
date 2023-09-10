package happyfox.helpdesk_automation;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

	public WebDriver driver;
	public WebDriverWait wait;

	public Waits(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForLoading(int timeout) {
		// wait until the page to load completely
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}

	public void waitForElementToBeClickable(WebElement element, int timeout) {
		//wait until the element to be loaded to perform click operation
		new WebDriverWait(this.driver, Duration.ofSeconds(timeout))
			.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementVisiblity(WebElement element, int timeout) {
		//wait until element to be visible on the page
		new WebDriverWait(this.driver, Duration.ofSeconds(timeout))
			.ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementInvisiblity(WebElement element, int timeout) {
		//wait until element to get disappear on the page
		new WebDriverWait(this.driver, Duration.ofSeconds(timeout))
			.ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForElementVisiblityWithPolling(WebElement element, int timeout,int poll) {
		//wait until element to be visible on the page with time interval
		new FluentWait<WebDriver>(this.driver)
			.withTimeout(Duration.ofSeconds(timeout))
			.pollingEvery(Duration.ofSeconds(timeout / poll))
			.ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.visibilityOf(element));
	}

}
