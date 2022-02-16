package testCases;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExecuteOpenTab {
	
	WebDriver webdriver;
	String testCaseandDataFile = "LoginTestCasesandData.xlsx";
	
	//To close browser at the end of each test method execution   
    @AfterMethod
     public void closeDriver (ITestResult result) throws Exception {
    	ExecuteTestMain t = new ExecuteTestMain();
    	t.takeScreenshot(result,webdriver);
      webdriver.quit();

}
    @BeforeMethod
    @Parameters({"browser"})
    public void openDriver(String browser) throws Exception {
    	ExecuteTestMain t = new ExecuteTestMain();
    	webdriver = t.setupBrowser(browser);
    	t.setTimeout(webdriver);
    	t.navigateToHomePage(webdriver); 
    	
}
    
    @Test
    public void openTab() throws InterruptedException {
    	webdriver.switchTo().newWindow(WindowType.TAB);
    	webdriver.navigate().to("https://www.google.com");
    	Thread.sleep(2000);
    	ArrayList<String> newTab = new ArrayList<String>(webdriver.getWindowHandles());
    	webdriver.switchTo().window(newTab.get(0));
    	Thread.sleep(2000);
    	webdriver.switchTo().window(newTab.get(1));
    	Thread.sleep(2000);
    	
    }
    
    @Test
    public void openWindow() throws InterruptedException {
    	webdriver.switchTo().newWindow(WindowType.WINDOW);
    	webdriver.navigate().to("https://www.google.com");
    	Thread.sleep(2000);
    	ArrayList<String> newTab = new ArrayList<String>(webdriver.getWindowHandles());
    	webdriver.switchTo().window(newTab.get(0));
    	Thread.sleep(2000);
    	webdriver.switchTo().window(newTab.get(1));
    	Thread.sleep(2000);
    	
    }

}
