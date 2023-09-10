package happyfox.helpdesk_automation.locators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import happyfox.helpdesk_automation.SystemService;

public class TicketPageLocators {

	@FindBy(xpath = "//header[@data-test-id='left-nav-queues-header']//following::ul//li//a")
	public List<WebElement> queuesMenu;

	@FindBy(xpath = "//div[@data-test-id='ticket-preview-close']")
	public WebElement closePreview;

	@FindBy(xpath = "//aside[@data-test-id='ticket-preview']//a[1]")
	public WebElement ticketHeader;

	@FindBy(xpath = "//a[@data-test-id='reply-link']")
	public WebElement reply;

	@FindBy(xpath = "//button[@data-test-id='add-update-reply-button']")
	public WebElement addReplyBtn;

	@FindBy(xpath = "//article[@data-test-id='ticket-box']")
	public WebElement ticketBox;

	@FindBy(xpath = "//span[@data-test-id='ticket-subject']")
	public WebElement ticketPageSubject;

	@FindBy(css = ".hf-ticket-box_display-id'")
	public WebElement ticketBoxId;

	@FindBy(css = ".hf-ticket-status_name")
	public WebElement ticketBoxStaus;

	@FindBy(xpath = "(//div[@data-test-id='ticket-box_priority'])[1]")
	public WebElement ticketBoxPriority;

	@FindBy(xpath = "//div[@id='cke_1_contents']//following::div[@data-test-id='ticket-box_status']")
	public WebElement cannedStatus;

	@FindBy(xpath = "//div[@id='cke_1_contents']//following::div[@data-test-id='ticket-box_priority']")
	public WebElement cannedPriority;

	@FindBy(xpath = "//div[@data-test-id='editor-add-tags-trigger']/div")
	public WebElement tagBox;

	@FindBy(xpath = "//div[@id='cke_1_contents']//following::div[@data-test-id='ticket-box_assignee']")
	public WebElement asignee;

	@FindBy(xpath = "//div[@id='cke_1_contents']//following::div[@data-test-id='due-date-value']")
	public WebElement dueDate;

	@FindBy(xpath = "//div[@data-test-id='floating-editor-time-spent']/div/div[2]")
	public WebElement timeSpent;

	@FindBy(xpath = "//div[@data-test-id='ticket-box_assignee']")
	public WebElement ticketBoxasignee;

	@FindBy(xpath = "//div[@data-test-id='ticket-box_raised-by']")
	public WebElement ticketBoxdueDate;

	@FindBy(xpath = "//div[@data-test-id='ticket-box_category']")
	public WebElement ticketCategory;

	@FindBy(xpath = "//div[@data-test-id='floating-editor-time-spent']/div/div[2]")
	public WebElement ticketBoxOwner;

	@FindBy(xpath = "//div[@data-test-id='editor-add-tags-trigger']//following::ul//li")
	public WebElement tagList;

	@FindBy(xpath = "//div[@data-test-id='add-update-editor']")
	public WebElement editor;

	@FindBy(xpath = "//div[@data-test-id='ticket-box_more-actions']")
	public WebElement ticketBoxmoreActions;

	@FindBy(linkText = "More Actions")
	public WebElement moreActions;

	@FindBy(css = ".ticket-more-actions_action-input hf-js-test-ticket-more-actions-status-select ember-view")
	public WebElement ticketActionChangeStatus;

	@FindBy(xpath = "//div[@class='hf-mod-dropdown ember-view']//ul//li")
	public List<WebElement> ticketActionStatusDropdown;

	@FindBy(css = ".ticket-more-actions_action-input hf-js-test-ticket-more-actions-priority-select ember-view")
	public WebElement ticketActionChangePriority;

	@FindBy(xpath = "//div[@class='ticket-more-actions_action-input hf-js-test-ticket-more-actions-priority-select ember-view']//ul//li")
	public List<WebElement> ticketActionPriorityDropdown;

	@FindBy(css = ".ticket-more-actions_action-input hf-js-test-ticket-more-actions-assignee-select ember-view")
	public WebElement ticketActionChangeAsignee;

	@FindBy(xpath = "//div[@class='ticket-more-actions_action-input hf-js-test-ticket-more-actions-assignee-select ember-view']//ul//li")
	public List<WebElement> ticketActionAsigneeDropdown;

	@FindBy(xpath = "//div[@data-test-id='pop-over-close']")
	public WebElement popupClose;

	@FindBy(xpath = "//span[@data-test-id='canned-action-trigger']")
	public WebElement cannedAction;

	@FindBy(css = ".hf-canned-action-pane")
	public WebElement cannedActionMenu;

	@FindBy(xpath = "//button[@data-test-id='hf-add-canned-action']")
	public WebElement useThisBtn;

	@FindBy(xpath = "//div[@data-test-id='add-update-editor']")
	public WebElement cannedActionBox;

	@FindBy(xpath = "//div[contains(text(),'Sample Canned Action Content')]")
	public WebElement cannedActionContent;

	@FindBy(xpath = "//input[@type='search']")
	public WebElement cannedActionSearch;

	@FindBy(xpath = "//a[@data-test-id='canned-action-cancel']")
	public WebElement cannedActioncancel;

	@FindBy(xpath = "//div[@data-test-id='pop-over-close']")
	public WebElement cannedActionMenuClose;

	@FindBy(xpath = "//div[@class='hf-update-box_body']/div/div/div")
	public WebElement updateBoxBody;

	@FindBy(css = ".hf-canned-action-pane")
	public WebElement cannedActionTable;

	@FindBy(xpath = "//dic[@data-test-id='empty-tags-message']")
	public WebElement asidetagsBox;

	@FindBy(xpath = "//span[contains(text(),'Draft Saved')]")
	public WebElement draftSaved;

	@FindBy(css = ".hf-canned-action-list ")
	public WebElement cannedActionList;

	public WebElement cannedActionQuery(String value) {
		return SystemService.driver.findElement(By.xpath("//li[@class='ember-power-select-option']"));
	}

	public WebElement getTicketListAttributes(String subjectName) {
		return SystemService.driver.findElement(By.xpath("//a[@title='" + subjectName + "']//ancestor::td"));
	}

	public WebElement getTables(String subjectName, byte i) {
		return SystemService.driver.findElement(
				By.xpath("//a[@title='" + subjectName + "']//ancestor::td//preceding-sibling::td[" + i + "]"));
	}

	public List<WebElement> allSelectedFilter() {
		return SystemService.driver.findElements(By.cssSelector("input:checked[type='checkbox']"));
	}

}
