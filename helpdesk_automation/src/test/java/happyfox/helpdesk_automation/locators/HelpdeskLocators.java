package happyfox.helpdesk_automation.locators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import happyfox.helpdesk_automation.SystemService;

public class HelpdeskLocators {

	@FindBy(name = "username")
	public WebElement agentUsrname;

	@FindBy(name = "password")
	public WebElement agentPass;

	@FindBy(id = "id_remember_me")
	public WebElement rememberMe;

	@FindBy(id = "btn-submit")
	public WebElement agentLoginbtn;

	@FindBy(xpath = "//div[@data-test-id='tickets-table']")////tbody//tr[last()]
	public WebElement ticketsTable;

	@FindBy(xpath = "//div[@data-test-id='module-switcher_trigger']")
	public WebElement menu;

	@FindBy(xpath = "//div[@data-test-id='module-switcher_manage-title']//following::ul//li//a")
	public List<WebElement> manageList;

	@FindBy(xpath = "//*[@data-test-id='statuses-table']//tbody//tr[last()]")
	public WebElement lastRowStatus;

	@FindBy(xpath = "//*[@data-test-id='priorities-table']//tbody//tr[last()]")
	public WebElement lastRowPriority;

	@FindBy(xpath = "//img[@data-test-id='staff-profile-image']")
	public WebElement profileBtn;

	@FindBy(xpath = "//li[@data-test-id='staff-menu_logout']")
	public WebElement agentLogout;

	@FindBy(css = ".hf-toast-message")
	public WebElement banner;

	@FindBy(css = ".confirmation")
	public WebElement logoutConfirmMsg;

	public List<WebElement> manageList() {
		return SystemService.driver
				.findElements(By.xpath("//div[@data-test-id='module-switcher_manage-title']//following::ul//li//a"));
	}

	public WebElement menu(String menu) {
		By by = By.xpath("//li//a[contains(text(),'"+menu+"')]");
		return SystemService.driver.findElement(by);
	}

}
