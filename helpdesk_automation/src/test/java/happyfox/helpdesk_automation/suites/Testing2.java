package happyfox.helpdesk_automation.suites;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import happyfox.helpdesk_automation.Helpdesk;
import happyfox.helpdesk_automation.SystemService;
import happyfox.helpdesk_automation.Waits;
import happyfox.helpdesk_automation.locators.TicketPageLocators;
import happyfox.helpdesk_automation.pages.manage.Priority;
import happyfox.helpdesk_automation.pages.manage.Status;
import happyfox.helpdesk_automation.pages.manage.Tickets;
import happyfox.helpdesk_automation.pages.support.newTicket.NewTicket;

public class Testing2 {

	public static WebDriver driver;
	public static WebDriver driver2;

	public static Helpdesk helpdesk;
	public static Status status;
	public static Priority priority;
	public static NewTicket submitTicket;
	public static Waits wait;
	public static Tickets tickets;
	public static NewTicket newTicket;
	public static TicketPageLocators ticloc;

	public static String timeStamp;

	public static HashMap<String, String> statusValue;
	public static HashMap<String, String> priorityValue;
	public static HashMap<String, String> ticketValue;
	public static HashMap<String, String> cannedAction;
	public static HashMap<String, String> ticketValues;
	public static HashMap<String, String> cannedActionValue;
	public static Set<String> tabs;

	public void init() {
		driver = SystemService.getDriver();
		helpdesk = new Helpdesk();
		status = new Status(SystemService.driver);
		priority = new Priority(SystemService.driver);
		submitTicket = new NewTicket(SystemService.driver);
		wait = new Waits(SystemService.driver);
		tickets = new Tickets();
		
		ticloc = new TicketPageLocators();

		ArrayList<String> list = new ArrayList<String>();

		list.add("Status");
		list.add("Priority");
		list.add("Ticket ID");

		ticketValues = new HashMap<String, String>() {

			{
				put("subject", "Test Ticket");
				put("message", "For Testing Purpose");
				put("name", "Anand Kishore");
				put("email", "ak@yopmail.com");
				put("phone", "9988776655");
				put("cc", "aK@yopmail.com");
				put("bcc", "aK@yopmail.com");
				put("file",
						"C:\\Users\\ckishorw\\Downloads\\Happy Fox Assessment\\AUTOMATION\\helpdesk_automation\\src\\test\\resources\\capture.jpg");

			}
		};

		this.cannedActionValue = new HashMap<String, String>() {

			{
				put("name", "Reply to customer query");

			}
		};

	}

	public static void main(String[] args) throws InterruptedException, MalformedURLException {

		Testing2 t2 = new Testing2();

		t2.init();

		helpdesk.openHelpdesk();
		helpdesk.agentLogin();
	
		String parent = driver.getWindowHandle();	
		System.out.println("Parent : "+parent);
		
		driver2 = SystemService.getDriver();
		String child = driver2.getWindowHandle();
		System.out.println("Child : "+child);
		
		//driver.switchTo().window(child);
		newTicket = new NewTicket(driver2);
		newTicket.openCreateTicketPage();	
		
		
		driver2.close();
		
		//driver2.switchTo().window(parent);
		
		driver.navigate().refresh();
		
		helpdesk.agentLogout();
		
		
		//driver.quit();


	}

}
