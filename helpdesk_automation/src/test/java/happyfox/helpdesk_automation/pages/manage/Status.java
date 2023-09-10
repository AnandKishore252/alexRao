package happyfox.helpdesk_automation.pages.manage;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import happyfox.helpdesk_automation.Helpdesk;
import happyfox.helpdesk_automation.SystemService;
import happyfox.helpdesk_automation.Waits;
import happyfox.helpdesk_automation.locators.manage.StatusLocators;

public class Status {

	private WebDriver driver;
	private StatusLocators statusLoc;
	private Helpdesk helpdesk;
	private Waits waits;

	public Status(WebDriver driver) {
		this.driver = driver;
		this.statusLoc = new StatusLocators();
		this.helpdesk = new Helpdesk();
		this.waits = new Waits(driver);
		PageFactory.initElements(this.driver, statusLoc);
	}

	public void openStatusTableView() {
		String statusURL = SystemService.prop.getProperty("base_url") + "/staff/manage/statuses";
		if (!this.driver.getCurrentUrl().equalsIgnoreCase(statusURL)) {
			this.driver.get(statusURL);
			this.waits.waitForElementToBeClickable(this.statusLoc.create, 13);
		}
	}

	public void createStatus(HashMap<String, String> value) {
		this.waits.waitForElementToBeClickable(this.statusLoc.create, 10);
		this.statusLoc.create.click();

		this.waits.waitForElementVisiblity(this.statusLoc.statusName, 10);
		this.statusLoc.statusName.click();
		SystemService.clearandSendkeys(this.statusLoc.statusName, value.get("name"));

		this.statusLoc.statusColor.click();
		this.setColorValue(value.get("color"));

		this.statusLoc.statusBehaviour.click();
		this.selectStatusBehaviour(value.get("behaviour"));

		this.statusLoc.desc.click();
		SystemService.clearandSendkeys(this.statusLoc.desc, value.get("description"));

		this.statusLoc.addBtn.click();
		this.waits.waitForElementVisiblity(this.statusLoc.getStatus(value.get("name")), 10);
	}

	public void setColorValue(String color) {
		String activepath = "//div[@class='sp-replacer sp-light sp-active']";
		this.statusLoc.colorValue.click();

		this.waits.waitForElementToBeClickable(this.statusLoc.sendColorValue, 5);
		this.statusLoc.sendColorValue.click();
		SystemService.clearandSendkeys(this.statusLoc.sendColorValue, color);

		this.statusLoc.statusColor = this.driver.findElement(By.xpath(activepath));
		this.waits.waitForElementToBeClickable(this.statusLoc.statusColor, 5);
		this.statusLoc.statusColor.click();
	}

	public void selectStatusBehaviour(String behaviour) {
		List<WebElement> options = this.statusLoc.behaviourDropdown();
		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(behaviour)) {
				option.click();
				break;
			}
		}
	}

	public boolean isStatusPresent(String name) {
		this.waits.waitForElementVisiblity(this.statusLoc.getStatus(name), 15);
		SystemService.scrollIntoElement(this.statusLoc.getStatus(name));
		return this.statusLoc.getStatus(name).isDisplayed();
	}

	public WebElement getStatus(String name) {
		return this.statusLoc.getStatus(name);
	}

	public WebElement getDefault() {
		return this.statusLoc.defaultTd;
	}

	public WebElement getStatusRow(String name) {
		return this.statusLoc.getExistingStatusRow(name);
	}

	public WebElement getStatustd(String name) {
		return this.statusLoc.getExistingStatustd(name);
	}

	public void makeDefault(String name) {
		this.waits.waitForElementVisiblity(this.statusLoc.getStatus(name), 10);
		SystemService.scrollIntoElement(this.statusLoc.getStatus(name));
		SystemService.mouseHover(this.statusLoc.getStatus(name));
		this.statusLoc.statusMakeDefault.click();
		this.waits.waitForElementInvisiblity(this.statusLoc.loader, 7);
	}

	public String getDefaultStatus() {
		return this.statusLoc.defaultStatusName();
	}

	public void deleteCurrentStatus(String changeDefaultStatusTo) {
		this.waits.waitForElementVisiblity(this.statusLoc.delete, 15);
		this.statusLoc.delete.click();
		this.waits.waitForElementVisiblity(this.statusLoc.confirmDelete, 13);

		try {
			if (this.statusLoc.dropdown.isDisplayed()) {
				this.statusLoc.dropdown.click();
				List<WebElement> options = this.statusLoc.statusChangeDropDown();
				for (WebElement option : options) {
					if (option.getText().equalsIgnoreCase(changeDefaultStatusTo)) {
						option.click();
						break;
					}
				}
			}
		} catch (Exception e) {

		}

		this.statusLoc.confirmDelete.click();
		
	}

	public void deleteCustomStatus(String statusName, String changeDefaultStatusTo) {
		this.waits.waitForLoading(30);
		SystemService.scrollIntoElement(this.statusLoc.getStatus(statusName));
		SystemService.mouseHover(this.statusLoc.getStatus(statusName));
		this.statusLoc.getStatus(statusName).click();
		deleteCurrentStatus(changeDefaultStatusTo);
		this.waits.waitForElementInvisiblity(this.statusLoc.getStatus(statusName), 15);
	}
	
	public String getBannerMsg() {
		String bannerMessage = this.helpdesk.getBannerMessage();
		return bannerMessage;
	}

}
