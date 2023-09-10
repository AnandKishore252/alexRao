package happyfox.helpdesk_automation.pages.support.newTicket;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import happyfox.helpdesk_automation.Helpdesk;
import happyfox.helpdesk_automation.SystemService;
import happyfox.helpdesk_automation.Waits;
import happyfox.helpdesk_automation.support.newTicket.locators.NewTicketLocators;

public class NewTicket {
	private WebDriver driver;
	private Waits waits;
	private NewTicketLocators newTicketLoc;
	private Helpdesk helpdesk;

	public NewTicket(WebDriver driver) {
		this.driver = driver;
		this.waits = new Waits(driver);
		this.newTicketLoc = new NewTicketLocators();
		this.helpdesk = new Helpdesk();
		PageFactory.initElements(this.driver, newTicketLoc);
	}

	public void openCreateTicketPage() {
		this.driver.get(this.helpdesk.prop.getProperty("base_url") + this.helpdesk.prop.getProperty("raise_ticket"));
		this.waits.waitForLoading(20);
	}

	public void addCC(String value) {
		this.newTicketLoc.addCCBtn.click();
		SystemService.clearandSendkeys(this.newTicketLoc.ccField, value);
	}

	public void addBCC(String value) {
		this.newTicketLoc.addCCBtn.click();
		this.waits.waitForElementVisiblity(this.newTicketLoc.bccField, 10);
		SystemService.clearandSendkeys(this.newTicketLoc.bccField, value);
	}

	// uploading file by file upload window
	public void addAttachment(String fileWithPath) throws InterruptedException {
		this.waits.waitForElementVisiblity(this.newTicketLoc.attachFile, 10);
		this.newTicketLoc.attachFile.click();
		StringSelection selection = new StringSelection(fileWithPath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		try {
			Robot robo = new Robot();
			robo.keyPress(KeyEvent.VK_CONTROL);
			robo.keyPress(KeyEvent.VK_V);
			robo.keyRelease(KeyEvent.VK_V);
			robo.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(20);
			robo.keyPress(KeyEvent.VK_TAB);
			robo.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(20);
			robo.keyPress(KeyEvent.VK_ENTER);
			robo.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		this.waits.waitForElementVisiblity(this.newTicketLoc.attachedFile, 10);
	}

	public void createTicket(HashMap<String, String> values) {
		this.waits.waitForElementVisiblity(this.newTicketLoc.msgField, 30);
		SystemService.takeSS("screenshot");
		this.newTicketLoc.subjectField.click();
		SystemService.clearandSendkeys(this.newTicketLoc.subjectField, values.get("subject"));
		SystemService.jseClick(this.newTicketLoc.msgField);
		SystemService.innerHTML(this.newTicketLoc.msgField, values.get("message"));
		this.newTicketLoc.fileInput.sendKeys(values.get("file"));
		this.waits.waitForElementVisiblity(this.newTicketLoc.attachedFile, 10);
		SystemService.clearandSendkeys(this.newTicketLoc.nameField, values.get("name"));
		SystemService.clearandSendkeys(this.newTicketLoc.emailField, values.get("email"));
		SystemService.clearandSendkeys(this.newTicketLoc.phNumber, values.get("phone"));
		this.newTicketLoc.createTicketBtn.click();
	}

	public String ticketCreatedMsg() {

		try {
			SystemService.driver.findElement((By) this.newTicketLoc.customMsg);
			return this.newTicketLoc.customMsg.getText();
		} catch (NoSuchElementException e) {

		}

		return null;
	}

	public WebElement MsgElement() {
		return this.newTicketLoc.customMsg;
	}

	public boolean Msg() {
		return this.newTicketLoc.customMsg.isDisplayed();
	}

}
