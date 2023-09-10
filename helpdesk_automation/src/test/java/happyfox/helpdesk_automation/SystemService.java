package happyfox.helpdesk_automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SystemService {

	public static WebDriver driver;
	public static Properties prop;
	public static Actions actions;
	public static Action action;
	public static Random obj;
	public static ArrayList<String> tabs;

	public static WebDriver getDriver() {
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream("src\\test\\resources\\config.properties");
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (prop.getProperty("browser").equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\ckishorw\\Downloads\\Driver\\chromedriver-win64\\chromedriver.exe");
			ChromeOptions chromeopt = new ChromeOptions();
			chromeopt.setBinary("C:\\Users\\ckishorw\\Downloads\\Driver\\chrome-win64\\chrome.exe");
			driver = new ChromeDriver(chromeopt);

		} else if (prop.getProperty("browser").equalsIgnoreCase("Firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (prop.getProperty("browser").equalsIgnoreCase("Edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		return driver;
	}

	public static void mouseHover(WebElement element) {
		actions = new Actions(driver);
		action = actions.moveToElement(element).build();
		action.perform();
	}

	public static void scrollIntoElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void jseClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public static void innerHTML(WebElement element, String value) {
		((JavascriptExecutor) driver).executeScript("var ele=arguments[0]; ele.innerHTML = '" + value + "';", element);
	}

	public static void clearandSendkeys(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	public static void sendValuesByJSE(WebElement element, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value','" + value + "');", element);
	}

	public static String colorCode() {
		obj = new Random();
		int rand_num = obj.nextInt(0xffffff + 1);
		return String.format("#%06x", rand_num);
	}

	public static Map<String, String> getWidowsID() {
		Map<String, String> windows = new HashMap<String, String>();
		windows.put("parent", driver.getWindowHandle());
		Set<String> w = driver.getWindowHandles();
		Iterator<String> it = w.iterator();
		while (it.hasNext()) {
			windows.put("child", it.next());
		}
		return windows;
	}

	public static void takeSS(String filename) {
		TakesScreenshot tss = ((TakesScreenshot) driver);
		File srcfile = tss.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcfile, new File("./screenshots/" + filename + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void FailedSS(String methodName) {
		TakesScreenshot tss = ((TakesScreenshot) driver);
		File srcfile = tss.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcfile, new File("./screenshots/" + methodName + "_Failed" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void elementSS(WebElement element, String fileName) {
		File srcfile = element.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcfile, new File("./screenshots/" + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void elementHighlighter(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'border: 2px solid green;');",
				element);
	}

	public static String jseGetText(WebElement element) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText;", element);

	}

}
