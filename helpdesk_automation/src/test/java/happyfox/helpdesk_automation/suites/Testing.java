package happyfox.helpdesk_automation.suites;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import happyfox.helpdesk_automation.Helpdesk;
import happyfox.helpdesk_automation.SystemService;
import happyfox.helpdesk_automation.Waits;
import happyfox.helpdesk_automation.listener.TestListener;
import happyfox.helpdesk_automation.pages.manage.Priority;
import happyfox.helpdesk_automation.pages.manage.Status;
import happyfox.helpdesk_automation.pages.manage.Tickets;
import happyfox.helpdesk_automation.pages.support.newTicket.NewTicket;

@Listeners(TestListener.class)
public class Testing {

	public WebDriver driver;
	public WebDriver driver2;
	public Helpdesk helpdesk;
	public Status status;
	public Priority priority;
	public Waits wait;
	public Tickets tickets;
	public NewTicket newticket;

	public String timeStamp;
	public Path path = Path.of(
			"C:/Users/ckishorw/Downloads/Happy Fox Assessment/AUTOMATION/helpdesk_automation/screenshots/screenshot.png");

	public HashMap<String, String> statusValue;
	public HashMap<String, String> priorityValue;
	public HashMap<String, String> ticketValues;
	public HashMap<String, String> cannedActionValue;
	public HashMap<String, String> cannedActionAttributes;
	public HashMap<String, String> windows;
	public List<String> tableFilterlist;

	@BeforeClass
	public void setUp() {

		this.driver = SystemService.getDriver();
		this.driver.manage().window().maximize();
		
		this.helpdesk = new Helpdesk();
		this.status = new Status(SystemService.driver);
		this.priority = new Priority(SystemService.driver);
		this.wait = new Waits(SystemService.driver);
		this.tickets = new Tickets();

		this.helpdesk.openHelpdesk();
		this.helpdesk.agentLogin();

		this.timeStamp = String.valueOf(new Date().getTime());
		this.statusValue = new HashMap<String, String>() {

			{
				put("name", "Issue Created " + timeStamp);
				put("color", SystemService.colorCode());
				put("behaviour", "Pending");
				put("description", "Status when a new ticket is created in HappyFox");
				put("isdefault", "Yes");
			}
		};
		this.priorityValue = new HashMap<String, String>() {

			{
				put("name", "Assisstance Required " + timeStamp);

				put("description", "Low");
				// put("helpText", "Low Priority");
				put("isdefault", "Yes");
			}
		};
		this.ticketValues = new HashMap<String, String>() {

			{
				put("subject", "Test Ticket" + timeStamp);
				put("message", "For Testing Purpose" + timeStamp);
				put("name", "AK" + timeStamp);
				put("email", "aK" + timeStamp + "@yopmail.com");
				put("phone", "9988776655");
				put("cc", "aK" + timeStamp + "@yopmail.com");
				put("bcc", "aK" + timeStamp + "@yopmail.com");
				put("file", path.toString());

			}
		};

		this.cannedActionValue = new HashMap<String, String>() {
			{
				put("name", "Reply to customer query");

			}
		};

		this.tableFilterlist = new ArrayList<String>();
		this.tableFilterlist.add("Status");
		this.tableFilterlist.add("Priority");
		this.tableFilterlist.add("Ticket ID");

		this.windows = new HashMap<String, String>();
	}

	@AfterClass
	public void tearDown() {

		/*
		 * this.helpdesk.agentLogout();
		 * Assert.assertEquals(this.helpdesk.logoutConfirmation(),
		 * "You have logged out successfully.");
		 * 
		 * SystemService.driver.quit();
		 */
	}

	@BeforeMethod
	public void beforeMethod() {
		this.timeStamp = String.valueOf(new Date().getTime());
	}

	@AfterMethod
	public void afetrMethod() {
		this.helpdesk.gotoHomePage();
	}

	@Test(enabled = true, priority = 1, groups = "Scenario_One")
	public void verify_status_and_priority_isCreated() {

		this.helpdesk.selectMenu("Statuses");
		this.wait.waitForLoading(20);
		this.status.createStatus(this.statusValue);

		this.driver.navigate().refresh();
		this.wait.waitForLoading(20);

		Assert.assertEquals(this.status.isStatusPresent(this.statusValue.get("name")), true);

		this.helpdesk.selectMenu("Priorities");
		this.wait.waitForLoading(20);
		this.priority.createPriority(this.priorityValue);

		this.driver.navigate().refresh();
		this.wait.waitForLoading(20);

		Assert.assertEquals(this.priority.isPriorityPresent(this.priorityValue.get("name")), true);

	}

	@Test(enabled = true, priority = 2, dependsOnMethods = "verify_status_and_priority_isCreated", groups = "Scenario_Two")
	public void verifyThatCreated_status_and_priority_isDefaulted() {

		this.helpdesk.selectMenu("Statuses");
		this.wait.waitForLoading(20);
		this.status.makeDefault(this.statusValue.get("name"));

		Assert.assertTrue(this.status.getDefaultStatus().equalsIgnoreCase(this.statusValue.get("name")));

		this.driver.navigate().refresh();
		this.wait.waitForLoading(20);

		this.helpdesk.selectMenu("Priorities");
		this.wait.waitForLoading(50);
		this.priority.makeDefault(this.priorityValue.get("name"));

		this.wait.waitForLoading(30);
		Assert.assertTrue(this.priority.defaultPriority().equalsIgnoreCase(this.priorityValue.get("name")));

	}

	@Test(enabled = true, priority = 3, dependsOnMethods = "verifyThatCreated_status_and_priority_isDefaulted", groups = "Scenario_Two")
	public void createTicket_fromSupport_newTicketPage() {

		this.driver2 = SystemService.getDriver();

		this.newticket = new NewTicket(driver2);
		this.newticket.openCreateTicketPage();
		this.newticket.createTicket(this.ticketValues);
		this.wait.waitForLoading(25);
		Assert.assertTrue(this.newticket.ticketCreatedMsg().contains("Your ticket has been created"));	
		this.driver2.close();
	}

	@Test(enabled = true, priority = 4, dependsOnMethods = "createTicket_fromSupport_newTicketPage", groups = "Scenario_Two")
	public void verifyThat_ticketCreated_with_defaultStatus_and_defaultPriority() {

		Assert.assertFalse(this.tickets.isTicketPresent(this.ticketValues.get("subject")));
		this.tickets.getTicketID(this.ticketValues.get("subject"));

		Assert.assertTrue(this.tickets.getTicketStatus(this.ticketValues.get("subject")).equalsIgnoreCase("shipment"));
		Assert.assertTrue(
				this.tickets.getTicketPriority(this.ticketValues.get("subject")).equalsIgnoreCase("critical"));

		this.tickets.openTicket(this.ticketValues.get("subject"));
		Assert.assertTrue(this.tickets.ticketBoxStatus().getText().equalsIgnoreCase(this.statusValue.get("name")));
		Assert.assertTrue(this.tickets.ticketBoxStatus().getText().equalsIgnoreCase(this.priorityValue.get("name")));

	}

	@Test(enabled = true, priority = 5, dependsOnMethods = "createTicket_fromSupport_newTicketPage", groups = "Scenario_Two")
	public void replyingtheTicket_usingCannedAction_andVerifyingThePropertyChange() {

		if (!this.driver.getCurrentUrl().equalsIgnoreCase(
				SystemService.prop.getProperty("base_url") + "/staff/ticket/" + this.tickets.ticketId)) {

			this.driver.navigate()
					.to(SystemService.prop.getProperty("base_url") + "/staff/ticket/" + this.tickets.ticketId);
		} else {

			this.tickets.replyWithCannedAction(this.cannedActionValue.get("name"));
			Assert.assertEquals(this.helpdesk.getBannerMessage(), "Ticket has been updated successfully");

			this.cannedActionAttributes = this.tickets.cannedActAttributes();
			Assert.assertTrue(cannedActionAttributes.get("status").equalsIgnoreCase("shipment"));
			Assert.assertTrue(cannedActionAttributes.get("priority").equalsIgnoreCase("shipment"));
			Assert.assertTrue(cannedActionAttributes.get("tags").equalsIgnoreCase("3 tags"));

		}

	}

	@Test(enabled = true, priority = 6, dependsOnMethods = "replyingtheTicket_usingCannedAction_andVerifyingThePropertyChange", groups = "Scenario_Two")
	public void verifyThat_ticketisUpdated_withCannedActionProperties() {
		Assert.assertTrue(this.tickets.isTicketPresent(this.ticketValues.get("subject")));
		Assert.assertTrue(this.tickets.getTicketStatus(this.ticketValues.get("subject")).equalsIgnoreCase("shipment"));
		Assert.assertTrue(
				this.tickets.getTicketPriority(this.ticketValues.get("subject")).equalsIgnoreCase("critical"));

	}

	@Test(enabled = true, priority = 7, dependsOnMethods = "verify_status_and_priority_isCreated", groups = "Scenario_Three")
	public void scenarioThree() {

		this.helpdesk.selectMenu("Statuses");
		this.wait.waitForLoading(20);
		Assert.assertEquals(this.status.isStatusPresent(this.statusValue.get("name")), true);
		this.status.deleteCustomStatus(this.statusValue.get("name"), "Closed");
		Assert.assertEquals(this.status.getBannerMsg(),
				"Status \"" + this.statusValue.get("name") + "\" is deleted successfully.");

		this.helpdesk.selectMenu("Priorities");
		this.wait.waitForLoading(20);
		Assert.assertEquals(this.priority.isPriorityPresent(this.priorityValue.get("name")), true);
		this.priority.deleteCustomPriority(this.priorityValue.get("name"), "Low");
		Assert.assertEquals(this.priority.getBannerMsg(),
				"Priority \"" + this.priorityValue.get("name") + "\" is deleted successfully.");

		this.driver.navigate().refresh();
		this.wait.waitForLoading(10);

	}

}
