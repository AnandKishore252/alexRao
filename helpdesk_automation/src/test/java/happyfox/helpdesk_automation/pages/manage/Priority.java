package happyfox.helpdesk_automation.pages.manage;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import happyfox.helpdesk_automation.Helpdesk;
import happyfox.helpdesk_automation.SystemService;
import happyfox.helpdesk_automation.Waits;
import happyfox.helpdesk_automation.locators.manage.PriorityLocators;

public class Priority {
	private WebDriver driver;

	private PriorityLocators priorityLoc;
	private Helpdesk helpdesk;
	private Waits waits;

	public Priority(WebDriver driver) {
		this.driver = driver;
		this.priorityLoc = new PriorityLocators();
		this.helpdesk = new Helpdesk();
		this.waits = new Waits(driver);
		PageFactory.initElements(this.driver, priorityLoc);
	}

	public void openPriorityTableView() {
		String priorityURL = SystemService.prop.getProperty("base_url") + "/staff/manage/priorities";
		if (!this.driver.getCurrentUrl().equalsIgnoreCase(priorityURL)) {
			this.driver.get(priorityURL);
			this.waits.waitForElementInvisiblity(this.priorityLoc.loader, 10);
		}
	}

	public void createPriority(HashMap<String, String> value) {
		this.waits.waitForElementVisiblity(this.priorityLoc.createPriority, 12);
		this.priorityLoc.createPriority.click();

		this.waits.waitForElementVisiblity(this.priorityLoc.priorityName, 5);
		// this.priorityLoc.priorityName.click();
		SystemService.clearandSendkeys(this.priorityLoc.priorityName, value.get("name"));

		// this.priorityLoc.priorityDesc.click();
		SystemService.clearandSendkeys(this.priorityLoc.priorityDesc, value.get("description"));

		/*
		 * this.priorityLoc.priorityHelpText.click();
		 * SystemService.clearandSendkeys(this.priorityLoc.priorityHelpText,
		 * value.get("helpText"));
		 */

		this.priorityLoc.addPriorityBtn.click();
		this.waits.waitForElementVisiblity(this.priorityLoc.getPriority(value.get("name")), 10);

	}

	public boolean isPriorityPresent(String name) {
		this.waits.waitForElementVisiblity(this.priorityLoc.getPriority(name), 10);
		SystemService.scrollIntoElement(this.priorityLoc.getPriority(name));
		return this.priorityLoc.getPriority(name).isDisplayed();
	}

	public void makeDefault(String name) {
		this.waits.waitForElementVisiblity(this.priorityLoc.getPriority(name), 10);
		SystemService.scrollIntoElement(this.priorityLoc.getPriority(name));
		SystemService.mouseHover(this.priorityLoc.getPriority(name));
		this.priorityLoc.makeDefault(name).click();
		this.waits.waitForElementInvisiblity(this.priorityLoc.loader, 12);
	}

	public String defaultPriority() {
		return this.priorityLoc.defaultPriority.getText();
	}

	public WebElement getPriority(String name) {
		return this.priorityLoc.getPriority(name);
	}

	public WebElement getDefault() {
		return this.priorityLoc.defaultTd;
	}

	public WebElement getPriorityRow(String name) {
		return this.priorityLoc.getExistingPriorityRow(name);
	}

	public void deleteCurrentPriority(String changeDefaultPriorityTo) {
		this.waits.waitForElementVisiblity(this.priorityLoc.deletePriority, 10);
		this.priorityLoc.deletePriority.click();
		this.waits.waitForElementVisiblity(this.priorityLoc.confirmDelete, 13);

		try {
			if (this.priorityLoc.dropdown.isDisplayed()) {
				this.priorityLoc.dropdown.click();
				List<WebElement> options = this.priorityLoc.priorityChangeDropDown();
				for (WebElement option : options) {
					if (option.getText().equalsIgnoreCase(changeDefaultPriorityTo)) {
						option.click();
						break;
					}
				}
			}
		} catch (Exception e) {

		}

		this.priorityLoc.confirmDelete.click();
	}

	public void deleteCustomPriority(String priorityName, String changeDefaultPriorityTo) {
		this.waits.waitForLoading(30);
		SystemService.scrollIntoElement(this.priorityLoc.getPriority(priorityName));
		SystemService.mouseHover(this.priorityLoc.getPriority(priorityName));
		this.priorityLoc.getPriority(priorityName).click();
		deleteCurrentPriority(changeDefaultPriorityTo);
		this.waits.waitForElementInvisiblity(this.priorityLoc.getPriority(priorityName), 20);
	}

	public String getBannerMsg() {
		String bannerMessage = this.helpdesk.getBannerMessage();
		return bannerMessage;
	}

}
