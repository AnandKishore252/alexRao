package happyfox.helpdesk_automation.pages.manage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import happyfox.helpdesk_automation.SystemService;
import happyfox.helpdesk_automation.Waits;
import happyfox.helpdesk_automation.locators.TicketPageLocators;
import happyfox.helpdesk_automation.locators.TicketTableLocators;

public class Tickets {

	private Waits waits;
	private TicketPageLocators ticketPageLoc;
	private TicketTableLocators ticketTableLoc;
	public String ticketId;

	public Tickets() {
		waits = new Waits(SystemService.driver);
		ticketPageLoc = new TicketPageLocators();
		ticketTableLoc = new TicketTableLocators();
		PageFactory.initElements(SystemService.driver, ticketPageLoc);
		PageFactory.initElements(SystemService.driver, ticketTableLoc);
	}

	public void ticketsTableViewFilter(List<String> value) {
		this.waits.waitForElementVisiblity(this.ticketTableLoc.tableViewFilterBtn, 15);
		this.ticketTableLoc.tableViewFilterBtn.click();
		this.waits.waitForElementVisiblity(this.ticketTableLoc.tableFilterList, 10);
		try {
			List<WebElement> checkboxes = this.ticketTableLoc.filterListCheckBox();
			for (WebElement checkbox : checkboxes) {
				if (checkbox.isSelected()) {
					checkbox.click();
				}
			}
			for (int i = 0; i < value.size(); i++) {
				this.ticketTableLoc.filterListLabel(value.get(i)).click();
			}
		} catch (Exception e) {

		}
		this.ticketTableLoc.tableViewFilterBtn.click();
	}

	public boolean isTicketPresent(String ticketSubject) {
		return this.ticketTableLoc.ticketSubject(ticketSubject).isDisplayed();
	}

	public void getTicketID(String ticketSubject) {
		String ticketUrl = this.ticketSubject(ticketSubject).getAttribute("href");
		String id = "";
		try {
			URL parsedUrl = new URL(ticketUrl);
			id = parsedUrl.getPath().substring(14);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		this.ticketId = id;
	}

	public HashMap<String, String> getTicketDetailPageAttributes() {

		HashMap<String, String> attributes = new HashMap<String, String>();
		attributes.put("status", this.ticketPageLoc.ticketBoxStaus.getText());
		attributes.put("priority", this.ticketPageLoc.ticketBoxPriority.getText());
		attributes.put("assignee", this.ticketPageLoc.ticketBoxasignee.getText());
		attributes.put("owner", this.ticketPageLoc.ticketBoxOwner.getText());
		attributes.put("duedate", this.ticketPageLoc.ticketBoxOwner.getText());
		attributes.put("category", this.ticketPageLoc.ticketCategory.getText());
		return attributes;
	}

	public WebElement ticketSubject(String ticketSubject) {
		return this.ticketTableLoc.ticketSubject(ticketSubject);
	}

	public String getTicketStatus(String ticketSubject) {
		return this.ticketTableLoc.getStatus(ticketSubject).getText();
	}

	public String getTicketPriority(String ticketSubject) {
		return this.ticketTableLoc.getPriority(ticketSubject).getText();
	}

	public WebElement ticketRow() {
		return this.ticketTableLoc.ticketRow(ticketId);
	}

	public void openTicket(String ticketSubject) {
		SystemService.driver.get(this.ticketTableLoc.ticketSubject(ticketSubject).getAttribute("href"));
		this.waits.waitForLoading(10);
	}

	public WebElement id() {
		this.waits.waitForElementVisiblity(this.ticketPageLoc.ticketBoxId, 7);
		return this.ticketPageLoc.ticketBoxId;
	}

	public WebElement ticketBoxStatus() {
		return this.ticketPageLoc.ticketBoxStaus;
	}

	public WebElement ticketBoxPriority() {
		return this.ticketPageLoc.ticketBoxPriority;
	}

	public WebElement ticketBox() {
		return this.ticketPageLoc.ticketBox;
	}

	public List<String> getTags() {
		List<String> tags = new ArrayList<String>();
		this.ticketPageLoc.tagBox.click();
		this.waits.waitForElementVisiblity(this.ticketPageLoc.tagList, 10);
		this.ticketPageLoc.tagList.click();
		List<WebElement> options = this.ticketPageLoc.cannedAction.findElements(By.tagName("li"));
		for (WebElement option : options) {
			tags.add(option.getText());
			break;
		}
		return tags;
	}

	public void replyWithCannedAction(String value) {
		this.ticketPageLoc.reply.click();
		this.waits.waitForElementVisiblity(this.ticketPageLoc.draftSaved, 25);
		this.ticketPageLoc.cannedAction.click();
		this.waits.waitForElementVisiblity(this.ticketPageLoc.cannedActionSearch, 5);
		this.ticketPageLoc.cannedActionSearch.sendKeys(value);
		this.ticketPageLoc.cannedActionQuery(value).click();
		SystemService.mouseHover(this.ticketPageLoc.useThisBtn);
		this.ticketPageLoc.useThisBtn.click();
		this.waits.waitForElementVisiblity(this.ticketPageLoc.cannedStatus, 7);
		SystemService.mouseHover(this.ticketPageLoc.addReplyBtn);
		this.ticketPageLoc.addReplyBtn.click();
	}

	public HashMap<String, String> cannedActAttributes() {

		HashMap<String, String> cannedAttributes = new HashMap<String, String>();
		cannedAttributes.put("status", this.ticketPageLoc.cannedStatus.getText());
		cannedAttributes.put("priority", this.ticketPageLoc.cannedPriority.getText());
		cannedAttributes.put("tags", this.ticketPageLoc.tagBox.getText());
		cannedAttributes.put("assignee", this.ticketPageLoc.asignee.getText());
		cannedAttributes.put("dueDate", this.ticketPageLoc.dueDate.getText());
		cannedAttributes.put("timeSpent", this.ticketPageLoc.timeSpent.getText());

		return cannedAttributes;

	}

}
