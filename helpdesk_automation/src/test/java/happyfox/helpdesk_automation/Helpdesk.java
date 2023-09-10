package happyfox.helpdesk_automation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;

import happyfox.helpdesk_automation.locators.HelpdeskLocators;

public class Helpdesk {

	public Properties prop;

	private HelpdeskLocators helpdeskLoc;
	private Waits waits;
	private ArrayList<String> tabs;

	public Helpdesk() {
		this.helpdeskLoc = new HelpdeskLocators();
		this.waits = new Waits(SystemService.driver);
		this.prop = SystemService.prop;
		PageFactory.initElements(SystemService.driver, helpdeskLoc);
	}

	public void openHelpdesk() {
		SystemService.driver.get(this.prop.getProperty("base_url") + this.prop.getProperty("agent_portal"));
		this.waits.waitForLoading(30);
	}

	public void agentLogin() {
		this.helpdeskLoc.agentUsrname.sendKeys(this.prop.getProperty("user_name"));
		this.helpdeskLoc.agentPass.sendKeys(this.prop.getProperty("password"));
		this.helpdeskLoc.agentLoginbtn.click();
		this.waits.waitForLoading(20);
		this.waits.waitForElementVisiblity(this.helpdeskLoc.ticketsTable, 30);
	}

	public void gotoHomePage() {
		SystemService.driver.get(SystemService.prop.getProperty("base_url") + "/staff");
		this.waits.waitForLoading(10);
		this.waits.waitForElementVisiblity(this.helpdeskLoc.ticketsTable, 30);
	}

	public HashMap<String, String> drvierWindowHandles(WebDriver driver) {
		HashMap<String, String> windows = new HashMap<String, String>();
		windows.put("parent", driver.getWindowHandle());
		Set<String> wh = driver.getWindowHandles();
		Iterator<String> I1 = wh.iterator();

		while (I1.hasNext()) {
			windows.put("child", I1.next());
		}
		return windows;
	}

	public void selectMenu(String value) {
		SystemService.mouseHover(this.helpdeskLoc.menu);
		this.waits.waitForElementToBeClickable(this.helpdeskLoc.menu(value), 6);
		this.helpdeskLoc.menu(value).click();
	}

	public void agentLogout() {
		this.waits.waitForElementVisiblity(this.helpdeskLoc.profileBtn, 20);
		this.helpdeskLoc.profileBtn.click();
		this.helpdeskLoc.agentLogout.click();
	}

	public String logoutConfirmation() {
		this.waits.waitForElementVisiblity(this.helpdeskLoc.logoutConfirmMsg, 25);
		return this.helpdeskLoc.logoutConfirmMsg.getText();
	}

	public String getBannerMessage() {
		this.waits.waitForElementVisiblity(this.helpdeskLoc.banner, 13);
		return this.helpdeskLoc.banner.getText().toString();
	}

}
