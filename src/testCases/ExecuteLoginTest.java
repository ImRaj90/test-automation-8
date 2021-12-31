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

/**
 * THIS IS THE EXAMPLE OF KEYWORD DRIVEN TEST CASE
 *
 */
public class ExecuteLoginTest {
	WebDriver webdriver;
	String testCaseandDataFile = "LoginTestCasesandData.xlsx";
	
	//To close browser at the end of each test method execution   
    @AfterMethod
     public void closeDriver (ITestResult result) throws Exception {
    	ExecuteTestMain t = new ExecuteTestMain();
    	t.takeScreenshot(result,webdriver);
      webdriver.quit();

}
    //To Open browser, set implicit wait time before each test method run and navigate to home page
    @BeforeMethod
    @Parameters({"browser"})
    public void openDriver(String browser) throws Exception {
    	ExecuteTestMain t = new ExecuteTestMain();
    	webdriver = t.setupBrowser(browser);
    	t.setTimeout(webdriver);
    	t.navigateToHomePage(webdriver);    	
}
    
    //This will call he main data provider under Execute Test Main
    //The method of each @Test run will be passed to the dataprovider and that method name will be used to select the sheet to fetch data.
	 @DataProvider(name="testData")
	    public Object[][] getDataFromDataprovider(Method m) throws IOException{
		 ExecuteTestMain t = new ExecuteTestMain();
		 Object[][] object1 = t.getDataFromDataproviderMain(m.getName() , testCaseandDataFile);
	     System.out.println("");
	     return object1;
	}
	 
		 
	 //Test Method name should match the data sheet in Excel file of the test
	 //Number of variables passed into test method will be equal to the number of action key rows in the excel sheet
	 //In the keyword sheet, the Object column gives the object property value variable name
	 //In the keyword sheet, the Object Type column gives the property type of object
	 //All the keywords are defined in UIOPeration.java and if an operation involves a value like set text where we need to enter the value of text then 
	 //that value must be defined in data sheet. If no value is needed for operation then the value can be left blank
	 //Data for each iteration can be given in subsequent columns to ObjectType column and a column header must be given (e.g.Iteration1)
	 @Test (dataProvider = "testData")
	 public void validLoginTest(String userName, String button1, String password, String button2, String list, String validateText) throws IOException , Exception {
	  String[] variables = {userName,button1,password,button2,list,validateText};
       ExecuteTestMain t = new ExecuteTestMain();
       t.ExecuteTest(webdriver, "validLoginTest", variables , testCaseandDataFile );
           
    			
    		}
	 
	 @Test (dataProvider = "testData")
	 public void invalidLoginTest(String userName, String button1, String password, String button2, String list , String validateText) throws IOException , Exception {
	   String[] variables = {userName,button1,password,button2,list,validateText};
       ExecuteTestMain t = new ExecuteTestMain();
       t.ExecuteTest(webdriver, "invalidLoginTest", variables , testCaseandDataFile);
       
    				    			   
    			
    		}
	}
    
    
    
    
