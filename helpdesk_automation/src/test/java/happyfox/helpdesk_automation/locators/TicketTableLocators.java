package happyfox.helpdesk_automation.locators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import happyfox.helpdesk_automation.SystemService;

public class TicketTableLocators {

	@FindBy(xpath = "//table//tbody[@class='lt-body']")
	public List<WebElement> ticketsTable;

	@FindBy(xpath = "//div[@data-test-id='open-table-settings']//child::*[local-name()='svg']")
	public WebElement tableViewFilterBtn;

	@FindBy(xpath = "//div[@class='hf-table-settings']//ul")
	public WebElement tableFilterList;

	@FindBy(xpath = "//label[contains(string(),'Ticket ID')]//input")
	public WebElement ticketId;

	@FindBy(xpath = "//label[contains(string(),'Ticket ID')]//input")
	public WebElement ticketPriority;

	public WebElement filterListLabel(String value) {

		return SystemService.driver.findElement(
				By.xpath("//div[@class='hf-table-settings']//ul//li//label[contains(string(),'" + value + "')]"));
	}

	public List<WebElement> filterListCheckBox() {
		return SystemService.driver
				.findElements(By.xpath("//div[@class='hf-table-settings']//following::ul//li//input[not(@disabled)]"));
	}

	public WebElement ticket(String id) {
		return SystemService.driver.findElement(By.xpath("//span[@title='" + id + "']"));
	}

	public WebElement ticketSubject(String subjectname) {
		return SystemService.driver.findElement(By.linkText(subjectname));
	}

	public WebElement ticketStatus(String defaultStatus, String ticketId) {
		return SystemService.driver.findElement(By.xpath("//tr[@data-test-id='table-row-id-" + ticketId
				+ "']//div/div[contains(string(),'" + defaultStatus + "')]//ancestor::td"));
	}

	public WebElement ticketPriority(String defaultPriority) {
		return SystemService.driver.findElement(By.xpath("//span[@title='" + defaultPriority + "']//ancestor::td"));
	}

	public WebElement ticketRow(String ticketId) {
		return SystemService.driver.findElement(By.xpath("//tr[@data-test-id='table-row-id-" + ticketId + "']"));
	}
	
	public WebElement getStatus(String ticketSubject) {
		return SystemService.driver.findElement(By.xpath("//a[contains(string(),'"+ticketSubject+"')]//preceding::td[1]//div/div"));
	}
	
	public WebElement getPriority(String ticketSubject) {
		return SystemService.driver.findElement(By.xpath("//a[contains(string(),'"+ticketSubject+"')]//following::td[2]/span"));
	}
	


}
