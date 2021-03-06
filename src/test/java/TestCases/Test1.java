package TestCases;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import DataReader.ExcelReader;
import DataReader.ExcelWriter;
import Utils.HomePageUtils;
import Utils.LoginUtils;
import Utils.TeamContentUtils;

import com.relevantcodes.extentreports.LogStatus;

public class Test1 extends BaseTest {
  @Test(dataProvider= "loginData")
  public void TestLoginPass(String UserName, String Password) throws Exception{
	  try{
//		  static Logger log = Logger.getLogger(BaseTest.class.getName());
		LoginUtils loginutils = new LoginUtils(driver);
//		log.info("Clicked on Login");
		loginutils.UserLogin(UserName, Password);
		HomePageUtils home = new HomePageUtils(driver);
		String heading = home.VerifyHomePage();
		Assert.assertEquals(heading, "MES welcome");
		TeamContentUtils teamcontentutils = new TeamContentUtils(driver);
		teamcontentutils.ClickTeamContent();
		teamcontentutils.ClickFolder1();
		teamcontentutils.ClickFolder2();
		teamcontentutils.ClickReport1();
		ExcelWriter writeExcel = new ExcelWriter(System.getProperty("user.dir")+"\\src\\test\\resources\\Datasheet_Demo.xlsx");
        writeExcel.setCellData("Menu","Report_Available",2,"PASS");
        test.log(LogStatus.PASS, "Test1 Pass");
		long start = System.currentTimeMillis();
		teamcontentutils.WaitForReportLoad();
		long timeToLoad= (System.currentTimeMillis()-start);
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timeToLoad),
	            TimeUnit.MILLISECONDS.toMinutes(timeToLoad) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeToLoad)),
	            TimeUnit.MILLISECONDS.toSeconds(timeToLoad) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeToLoad)));
	//	System.out.println(timeToLoad);
	//	DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
	//	String loadTime = dateFormat.format(timeToLoad);
	//	String loadTime = Long.toString(timeToLoad);
        writeExcel.setCellData("Menu","Report_Load_Time",2,hms);
	  }
        catch (Exception e){
   //     	ExcelWriter writeExcel = new ExcelWriter("C:\\Users\\yasmittal\\Documents\\Selenium package\\SeleniumTestNG-master_final\\src\\test\\resources\\Datasheet_Demo.xlsx");
        	ExcelWriter writeExcel = new ExcelWriter(System.getProperty("user.dir")+"\\src\\test\\resources\\Datasheet_Demo.xlsx");
        
        	writeExcel.setCellData("Menu","Report_Available",2,"FAIL");
        	test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test1 Failed");
        	throw(e);
        	}
	//	System.out.println(timeToLoad);

			 }
/*	  @DataProvider (name="LoginData1")
	  public Object [][] getDataFromDataProvider1(Method a){
		  if (a.getName().equalsIgnoreCase("UserLogin")) {
	  return new Object [][]
			  {
		  {"yasmittal", "yasmittal"}
			  };}
		  else {
		return new Object [][]
				  {
			  {"yasmittal", "yasmittal"}
				  };
		  }
	}*/
  
  @DataProvider
  public String[][] loginData() throws InvalidFormatException, IOException{
		ExcelReader read = new ExcelReader();
		return read.getCellData(System.getProperty("user.dir")+"\\src\\test\\resources\\Datasheet_Demo.xlsx","Login");
		}

}

