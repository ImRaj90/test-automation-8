package library;

import java.io.File;
//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.TestListenerAdapter;
//import org.testng.Reporter;
import org.openqa.selenium.WebDriver;

public class Utility extends TestListenerAdapter {
	public static void captureScreenshot(WebDriver driver,String screenshotName)
	{
	 
	try 
	{
	TakesScreenshot ts=(TakesScreenshot)driver;
	 
	File source=ts.getScreenshotAs(OutputType.FILE);
	 
	FileHandler.copy(source, new File("./Screenshots/"+screenshotName+".png"));
	 
	System.out.println("Screenshot taken");

	} 
	catch (Exception e)
	{
	 
	System.out.println("Exception while taking screenshot "+e.getMessage());
	} 
	}

}
