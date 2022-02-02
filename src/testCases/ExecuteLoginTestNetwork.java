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

public class ExecuteLoginTestNetwork {
	
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
    @Parameters({"condition"})
    public void openDriver(boolean condition) throws Exception {    
    	ExecuteTestMain t = new ExecuteTestMain();
    	webdriver = t.setupBrowserWithNetwork(condition);
    	t.setTimeout(webdriver);
    	t.navigateToHomePage(webdriver);    	
}
    @DataProvider(name="testData")
    public Object[][] getDataFromDataprovider(Method m) throws IOException{
	 ExecuteTestMain t = new ExecuteTestMain();
	 Object[][] object1 = t.getDataFromDataproviderMain(m.getName() , testCaseandDataFile);
     System.out.println("");
     return object1;
}
    @Test (dataProvider = "testData")
	 public void validLoginTest(String userName, String button1, String password, String button2, String list, String validateText) throws IOException , Exception {
	  String[] variables = {userName,button1,password,button2,list,validateText};
      ExecuteTestMain t = new ExecuteTestMain();
      t.ExecuteTest(webdriver, "validLoginTest", variables , testCaseandDataFile );   			
   		}
    
    @Test
    public void testRun() {
    	System.out.println("Test");
    }

}
