package testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import operation.ReadProperties;
import operation.UIOperation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.lang.Integer;
import excelExportAndFileIO.ReadExcelFile;

public class ExecuteTestMain {
	ReadProperties p = new ReadProperties();
	
		
	public void ExecuteTest(WebDriver webdriver, String sheetName, String[] variables , String testCasesandDataFile ) throws IOException , Exception {
	ReadExcelFile file = new ReadExcelFile();
	    
    UIOperation operation = new UIOperation(webdriver);
    //Read keyword sheet
    Sheet keyWordFrameWorkSheet = file.readExcel(System.getProperty("user.dir")+"\\",testCasesandDataFile , sheetName);
  //Find number of rows in excel file
	int rowCount = keyWordFrameWorkSheet.getLastRowNum()-keyWordFrameWorkSheet.getFirstRowNum();
	//Create a loop over all the rows of excel file to read it
	for (int i = 1; i < rowCount+1; i++) {
		//Loop over all the rows
		Row row = keyWordFrameWorkSheet.getRow(i);
		//Check if the first cell contain a value, if yes, That means it is the new testcase name
		if(row.getCell(0).toString().length()==0){
		//Print testcase detail on console
			System.out.println(row.getCell(1).getRichStringCellValue().toString()+"----"+ row.getCell(2).getRichStringCellValue().toString()+"----"+
			row.getCell(3).getRichStringCellValue().toString()+"----"+ variables[i-2].toString());
		//Call perform function to perform operation on UI
		//Pass parameters (Action, property value of object, property type of object, parameter to use in action e.g.text value for SETETXT
			operation.perform(row.getCell(1).getRichStringCellValue().toString(), row.getCell(2).getRichStringCellValue().toString(),	row.getCell(3).getRichStringCellValue().toString(), variables[i-2].toString());	
	    }
		else{
			//Print the new  testcase name when it started
				System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
}
	}
	
	
	
	}
	
	public void ExecuteFeature(WebDriver webdriver, String sheetName, String[] variables , String testCasesandDataFile ) throws IOException , Exception {
		ReadExcelFile file = new ReadExcelFile();
		    
	    UIOperation operation = new UIOperation(webdriver);
	    //Read keyword sheet
	    Sheet keyWordFrameWorkSheet = file.readExcel(System.getProperty("user.dir")+"\\",testCasesandDataFile , sheetName);
	  //Find number of rows in excel file
		int rowCount = keyWordFrameWorkSheet.getLastRowNum()-keyWordFrameWorkSheet.getFirstRowNum();
		//Create a loop over all the rows of excel file to read it
		for (int i = 1; i < rowCount+1; i++) {
			//Loop over all the rows
			Row row = keyWordFrameWorkSheet.getRow(i);
			//Check if the first cell contain a value, if yes, That means it is the new testcase name
			if(row.getCell(0).toString().length()==0){
			//Call perform function to perform operation on UI
			//Pass parameters (Action, property value of object, property type of object, parameter to use in action e.g.text value for SETETXT
			operation.perform(row.getCell(1).getRichStringCellValue().toString(), row.getCell(2).getRichStringCellValue().toString(),	row.getCell(3).getRichStringCellValue().toString(), variables[i-2].toString());
		    }
			
		}
		
		
		}
	
	public Object[][] getDataFromDataproviderMain(String dataSheetName, String testCasesandDataFile) throws IOException{
	    Object[][] object = null;
	    ReadExcelFile file = new ReadExcelFile();
	    //Read keyword sheet
	Sheet keywordSheet = file.readExcel(System.getProperty("user.dir")+"\\",testCasesandDataFile , dataSheetName);
	//Find number of data rows in excel file	
	    int dataRowCount = (keywordSheet.getLastRowNum()-keywordSheet.getFirstRowNum()) - 1;
	  //Find number of data columns in excel file
	    int dataColumnCount = (keywordSheet.getRow(0).getPhysicalNumberOfCells()) - 4;
	    object = new Object[dataColumnCount][dataRowCount];
	    for (int i = 0; i < dataColumnCount; i++) {
	        //Loop over all the data rows
	         for (int j = 0; j < dataRowCount; j++) {
	        	Row row = keywordSheet.getRow(j+2);
	            object[i][j] = String.valueOf(row.getCell(i+4));
	        		        	
	        }
	    }
	    
	     return object;
}
	public WebDriver setupBrowser(String browser) throws Exception {
		WebDriver driver;
		//System.out.println(allProperties.getProperty("url"));
		//Check if parameter passed from TestNG is 'firefox'
				if(browser.equalsIgnoreCase("firefox")){
				//create firefox instance
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver.exe");
					driver = new FirefoxDriver();
				}
				//Check if parameter passed as 'chrome'
				else if(browser.equalsIgnoreCase("chrome")){
					//set path to chromedriver.exe
					System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
					//create chrome instance
					driver = new ChromeDriver();
				}
				//Check if parameter passed as 'Edge'
						else if(browser.equalsIgnoreCase("Edge")){
							//set path to Edge.exe
							System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\msedgedriver.exe");
							//create Edge instance
							driver = new EdgeDriver();
						}
				else{
					//If no browser passed throw exception
					throw new Exception("Browser is not correct");
				}
				//driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
				return driver;
	}
	
	public void navigateToHomePage(WebDriver driver) throws Exception {
		driver.get(p.getObjectRepository().getProperty("url"));
		driver.manage().window().maximize();
	}
	
	public void setTimeout(WebDriver driver) throws Exception {
		int implicitWait = Integer.parseInt(p.getObjectRepository().getProperty("implicitwait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
		//webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	}
	
	public void takeScreenshot(ITestResult result,WebDriver driver)
	{
	 
	// Here will compare if test is failing then only it will enter into if condition
	if(ITestResult.FAILURE==result.getStatus())
	{
	try 
	{
	// Create refernce of TakesScreenshot
	TakesScreenshot ts=(TakesScreenshot)driver;
	 
	// Call method to capture screenshot
	File source=ts.getScreenshotAs(OutputType.FILE);
	 
	// Copy method  specific location here it will save all screenshot in our project home directory and
	// result.getName() will return name of test case so that screenshot name will be same
	try{
		File destFile = new File("./Screenshots/"+result.getName()+"_"+(System.currentTimeMillis())+".png");
	FileHandler.copy(source, destFile);
	System.out.println("Screenshot taken");
	Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");;
	} finally {
		
	}
	} 
	catch (Exception e)
	{
	System.out.println("Exception while taking screenshot "+e.getMessage());
	} finally {
		
	}
}
	}
	
}

