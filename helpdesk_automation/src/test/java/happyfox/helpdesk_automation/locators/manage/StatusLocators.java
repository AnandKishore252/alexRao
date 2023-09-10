package happyfox.helpdesk_automation.locators.manage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import happyfox.helpdesk_automation.SystemService;

public class StatusLocators {

	@FindBy(xpath = "//button[@data-test-id='new-status']")
	public WebElement create;

	@FindBy(xpath = "//input[@data-test-id='form-field-name']")
	public WebElement statusName;
	@FindBy(xpath = "//div[@class='sp-replacer sp-light']")
	public WebElement statusColor;

	@FindBy(xpath = "//div[@class='sp-color']")
	public WebElement colorValue;

	@FindBy(xpath = "//div[@class='sp-color']//div//following::div//div//div//following::div//input")
	public WebElement sendColorValue;

	@FindBy(xpath = "//div[@class='hf-form-field_label']//following::div")
	public WebElement statusColorPreview;

	@FindBy(xpath = "//div[@aria-label='Behavior']")
	public WebElement statusBehaviour;

	@FindBy(xpath = "//div[@class='ember-basic-dropdown']//ul//li[text()=")
	public WebElement statusBehaviourOption;

	@FindBy(xpath = "//textarea[@aria-label='Description']")
	public WebElement desc;

	@FindBy(linkText = "Make Default")
	public WebElement statusMakeDefault;

	@FindBy(xpath = "//div[@data-test-id='default-status']")
	public WebElement defaultTd;

	@FindBy(css = ".hf-ticket-action_loader")
	public WebElement loader;

	@FindBy(xpath = "//button[@data-test-id='add-status']")
	public WebElement addBtn;

	@FindBy(linkText = "Cancel")
	public WebElement cancel;

	@FindBy(linkText = "Reset")
	public WebElement reset;

	@FindBy(xpath = "//header[@data-test-id='view-status-header']//a[contains(text(),'Edit')]")
	public WebElement edit;

	@FindBy(xpath = "//header[@data-test-id='view-status-header']/a[contains(text(),'Delete')]")
	public WebElement delete;

	@FindBy(xpath = "//button[contains(text(),'Delete')]")
	public WebElement confirmDelete;

	@FindBy(xpath = "//div[@data-test-id='form-field-alternateEntity']/div/div[1]")
	public WebElement dropdown;

	@FindBy(xpath = "//li[@data-option-index='1']")
	public WebElement closedStatus;

	@FindBy(xpath = "//div[contains(text(),'Status Name')]//following::div")
	public WebElement statusNameVerify;

	@FindBy(xpath = "//div[@data-test-id='slide-in-close']")
	public WebElement close;

	@FindBy(xpath = "//*[@data-test-id='statuses-table']//tbody")
	public WebElement table;

	@FindBy(xpath = "//*[@data-test-id='statuses-table']//tbody//tr[last()]")
	public WebElement lastRow;

	public WebElement sideMenu(String name) {
		return SystemService.driver.findElement(By.linkText(name));
	}

	public WebElement getStatus(String statusName) {
		return SystemService.driver.findElement(By.xpath("//div[@title='" + statusName + "']"));
	}

	public WebElement getExistingStatusRow(String statusName) {
		return SystemService.driver.findElement(By.xpath("//div[@title='" + statusName + "']//ancestor::tr"));
	}

	public WebElement getExistingStatustd(String statusName) {
		return SystemService.driver.findElement(By.xpath("//div[@title='" + statusName + "']//ancestor::td"));
	}

	public List<WebElement> behaviourDropdown() {
		return SystemService.driver.findElements(By.xpath("//div[@class='ember-basic-dropdown']//ul//li"));
	}

	public String defaultStatusName() {
		return SystemService.driver.findElement(By.xpath("//div[@data-test-id='default-status']//ancestor::tr/td[2]"))
				.getText();
	}

	public List<WebElement> statusChangeDropDown() {
		return SystemService.driver
				.findElements(By.xpath("//div[@data-test-id='form-field-alternateEntity']//following::ul//li"));
	}

}
