package commonMethods;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import scripts.Flipkart;
import atu.testng.reports.exceptions.ATUReporterException;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })

public class Testcases extends Config {
	{
		System.setProperty("atu.reporter.config", System.getProperty("user.dir") + "/atu.properties");
	}
	public String appURL;
	public String project_Name;
	public String environment;
	public String browser;
	public String browser_Version;
	public WebDriver driver;
	
	File f = new File(report_folder_create + "\\reports");

	Flipkart flipkart = new Flipkart();

	@BeforeClass
	public void getDataFromConfig() throws ATUReporterException, IOException, InterruptedException {
		
		appURL = Utils.getDataFromTestConfig("URL");
		System.out.println(appURL);
		browser = Utils.getDataFromTestConfig("AppBrowser");
		browser_Version = Utils.getDataFromTestConfig("BrowserVersion");
		project_Name = Utils.getDataFromTestConfig("Project_Name");
		environment = Utils.getDataFromTestConfig("Environment");
		driver=getWebDriver(browser);
	}
	
	@Test
	public void productSearch() throws IOException, JSONException, InterruptedException {
		flipkart.productSearch(driver, appURL);
	}
	
	@Test
	public void productSelect() throws IOException, JSONException, InterruptedException {
		flipkart.productSelect(driver);
	}
	
	@Test
	public void productShopping() throws IOException, JSONException, InterruptedException {
		flipkart.productShopping(driver);
		
	}
	
	@Test
	public void productCart() throws IOException, JSONException, InterruptedException {
		flipkart.productCart(driver);
		
	}
	
	@Test
	public void Login() throws IOException, JSONException, InterruptedException {
		flipkart.Login(driver);
		
	}
	
	@Test
	public void productDeliver() throws IOException, JSONException, InterruptedException {
		flipkart.productDeliver(driver);
		
	}
	
	@Test
	public void productRemove() throws IOException, JSONException, InterruptedException {
		flipkart.productRemove(driver);
		
	}
	
	@Test
	public void Logout() throws IOException, JSONException, InterruptedException {
		flipkart.Logout(driver);
		
	}
	

	@AfterTest
	public void teardown() throws Exception {
		driver.quit();

	}

}
