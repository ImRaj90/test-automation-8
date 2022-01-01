package testCases;

import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.DataProvider;

public class ExecuteHomePage {
	WebDriver webdriver;
	String testCaseandDataFile = "HomePageTestCasesandData.xlsx";
	
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
    	String variable[] = {"Admin","","Admin123","Inpatient Ward","","Inpatient Ward"};
    	t.ExecuteFeature(webdriver, "validLoginTest", variable, "LoginTestCasesandData.xlsx");
}
	
	 @DataProvider(name="testData")
	    public Object[][] getDataFromDataprovider(Method m) throws IOException{
		 ExecuteTestMain t = new ExecuteTestMain();
		 Object[][] object1 = t.getDataFromDataproviderMain(m.getName() , testCaseandDataFile);
	     System.out.println("");
	     return object1;
	}
	 
	 @Test (dataProvider = "testData")
	      public void selectActionTest(String findButton, String findElement, String backButton, String registerButton, String registerElement) throws IOException , Exception {
	      String[] variables = {findButton,findElement,backButton,registerButton,registerElement};
	      ExecuteTestMain t = new ExecuteTestMain();
          t.ExecuteTest(webdriver, "selectActionTest", variables , testCaseandDataFile );
           
    			
    		}

}
