package happyfox.helpdesk_automation.support.newTicket.locators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import happyfox.helpdesk_automation.SystemService;

public class NewTicketLocators {

	@FindBy(xpath = "//div[@class='hf-welcome-banner_heading hf-welcome-banner_heading_37']")
	public WebElement Welcomebanner;

	@FindBy(id = "id_subject")
	public WebElement subjectField;

	@FindBy(xpath = "//div[@id='cke_1_contents']//p")
	public WebElement msgField;

	@FindBy(id = "add-cc")
	public WebElement addCCBtn;

	@FindBy(id = "add-bcc")
	public WebElement addBCCBtn;

	@FindBy(id = "id_cc")
	public WebElement ccField;

	@FindBy(id = "id_bcc")
	public WebElement bccField;

	@FindBy(id = "attach-file-input")
	public WebElement fileInput;

	@FindBy(linkText = "Browse for a file")
	public WebElement attachFile;

	@FindBy(className = "hf-attach-file_content")
	public WebElement attachedFile;

	@FindBy(id = "id_name")
	public WebElement nameField;

	@FindBy(id = "id_email")
	public WebElement emailField;

	@FindBy(id = "id_phone")
	public WebElement phNumber;

	@FindBy(id = "submit")
	public WebElement createTicketBtn;

	@FindBy(linkText = "Cancel")
	public WebElement cancel;

	@FindBy(xpath = "//div[@class='hf-custom-message-after-ticket-creation_content hf-editor-reset_list']")
	public WebElement customMsg;

	public List<WebElement> attachedFileList(String index) {
		return SystemService.driver
				.findElements(By.xpath("//div[@data-file-id='" + index + "']/span[@class='hf-attach-file_name']"));
	}

	public List<WebElement> deleteAttachedFile(String index) {
		return SystemService.driver
				.findElements(By.xpath("//div[@data-file-id='" + index + "']//span[@class='hf-attach-file_close']"));
	}

}
