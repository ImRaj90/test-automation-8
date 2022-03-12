package testCases;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.locators.RelativeLocator.with;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExecuteRelativeLocators {
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
    public void testRelativeLocators() throws InterruptedException {
    	
    	WebElement e1 = webdriver.findElement(with(By.tagName("li")).toLeftOf(By.id("Isolation Ward")));
    	System.out.println("Clicking element left of Isolation Ward:" + e1.getAttribute("id") );
    	webdriver.findElement(with(By.tagName("li")).toLeftOf(By.id("Isolation Ward"))).click();
    	System.out.println("");
    	Thread.sleep(2000);
    	
    	WebElement e2 = webdriver.findElement(with(By.tagName("li")).toRightOf(By.id("Isolation Ward")));
    	System.out.println("Clicking element right of Isolation Ward:" + e2.getAttribute("id") );
    	e2.click();
    	System.out.println("");
    	Thread.sleep(2000);
    	
    	List<WebElement> elementList = webdriver.findElements(with(By.tagName("li")).toLeftOf(By.id("Registration Desk")));
    	System.out.println("Print items left of Registration Desk");
    	System.out.println("Number of elements:" +elementList.size());
    	for (int i=0; i<elementList.size();i++){
    	      System.out.println("Element text:" + elementList.get(i).getAttribute("id"));
    	    }
    	System.out.println("Clicking 3rd element left of Registarion Desk:" + elementList.get(2).getAttribute("id") );
    	elementList.get(2).click();  
    	System.out.println("");
    	Thread.sleep(2000);
    	
    	System.out.println("Print items near Registration Desk");
    	List<WebElement> elementList1 = webdriver.findElements(with(By.tagName("li")).near(By.id("Registration Desk")));
    	System.out.println("Number of elements:" +elementList1.size());
    	for (int i=0; i<elementList1.size();i++){
    	      System.out.println("Element text:" + elementList1.get(i).getAttribute("id"));
    	    }
    	System.out.println("Clicking 2nd element near Registarion Desk:" + elementList1.get(1).getAttribute("id") );
    	elementList1.get(1).click();
    	System.out.println("");
    	Thread.sleep(2000);
    	
    	WebElement e3 = webdriver.findElement(with(By.tagName("li")).above(By.id("loginButton")));
    	System.out.println("Clicking element above Login Button:" + e3.getAttribute("id") );
    	e3.click();
    	System.out.println("");
    	Thread.sleep(2000);
    	
    	WebElement e4 = webdriver.findElement(with(By.tagName("input")).below(By.id("Registration Desk")));
    	System.out.println("Clicking element below Registration Desk:" + e4.getAttribute("id") );
    	e4.click(); 
    	Thread.sleep(2000);
    		
    	
    }
    
    @Test
    public void getTags() throws InterruptedException {
    	webdriver.get("https://westportlibrary.libguides.com/NYTimesbestsellers");
    	List<WebElement> elementList = webdriver.findElements(with(By.tagName("img")).toLeftOf(By.id("s-lg-content-64836957")));
    	for (int i=0; i<elementList.size();i++){
    	      System.out.println(elementList.get(i).getAttribute("alt"));
    	    }
    }
}
    



