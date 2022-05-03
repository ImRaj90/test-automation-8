package testCases;


import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class ExecuteSoftHardAssertions {
	WebDriver webdriver;
	String testCaseandDataFile = "LoginTestCasesandData.xlsx";
	
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
    	webdriver.get("http://www.google.com");
    	webdriver.manage().window().maximize();
    	
}
    
    @DataProvider(name="testData")
    public Object[][] getDataFromDataprovider(Method m) throws IOException{
	 ExecuteTestMain t = new ExecuteTestMain();
	 Object[][] object1 = t.getDataFromDataproviderMain(m.getName() , testCaseandDataFile);
     System.out.println("");
     return object1;
}
    
    @Test (dataProvider = "testData")
	 public void softAssertion(String title, String password, String text, String button) throws IOException , Exception {
	   String[] variables = {title,password,text,button};
      ExecuteTestMain t = new ExecuteTestMain();
      t.ExecuteTest(webdriver, "softAssertion", variables , testCaseandDataFile);
      
   				    			   
   			
   		}
    
    @Test (dataProvider = "testData")
	 public void softAssertionAll(String title, String password, String text, String button, String allText) throws IOException , Exception {
    //public void softAssertionAll () {
    
    
	   String[] variables = {title,password,text,button, allText};
     ExecuteTestMain t = new ExecuteTestMain();
     t.ExecuteTest(webdriver, "softAssertionAll", variables , testCaseandDataFile);
     
  				    			   
  			
  		}

}
