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
import Utils.teamContentutils2;

public class Test3 extends BaseTest {
//  @Test(dataProvider= "loginData")
  public void TestLoginPass(String UserName, String Password) throws Exception{
	  try{
		LoginUtils loginutils = new LoginUtils(driver);
		loginutils.UserLogin(UserName, Password);
		HomePageUtils home = new HomePageUtils(driver);
		String heading = home.VerifyHomePage();
		Assert.assertEquals(heading, "MES welcome");
	  }
	  catch (Exception e){
      	throw(e);
      	}
  }
	  
//   @Test(dataProvider = "reportTest")
	public void TestReport(String Report_Name, String Locator_Team_Content, String Locator_Folder_1,String Locator_Folder_2, String Locator_Folder_3, String Locator_Folder_4,String Locator_Report, String Locator_Report_Data) throws Exception{
	   try{
		teamContentutils2 teamcontentutils = new teamContentutils2(driver);
		if (Locator_Team_Content!= ""){
		teamcontentutils.ClickTeamContent(Locator_Team_Content);
		}
		if (Locator_Folder_1 != ""){
		teamcontentutils.ClickFolder1(Locator_Folder_1);
		}
		if (Locator_Folder_2 != ""){
		teamcontentutils.ClickFolder2(Locator_Folder_2);
		}
		if (Locator_Folder_3 != ""){
		teamcontentutils.ClickFolder3(Locator_Folder_3);
		}
		if (Locator_Folder_4 != ""){
			teamcontentutils.ClickFolder4(Locator_Folder_4);
			}
		if (Locator_Report != ""){
			teamcontentutils.ClickReport1(Locator_Report);
			}
		
		ExcelWriter writeExcel = new ExcelWriter(System.getProperty("user.dir")+"\\src\\test\\resources\\Datasheet_Demo.xlsx");
        writeExcel.setCellData("Menu","Report_Available",2,"PASS");
		long start = System.currentTimeMillis();
		teamcontentutils.WaitForReportLoad(Locator_Report_Data);
		long timeToLoad= (System.currentTimeMillis()-start);
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timeToLoad),
	            TimeUnit.MILLISECONDS.toMinutes(timeToLoad) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeToLoad)),
	            TimeUnit.MILLISECONDS.toSeconds(timeToLoad) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeToLoad)));
        writeExcel.setCellData("Menu","Report_Load_Time",2,hms);
	  }
        catch (Exception e){
        	ExcelWriter writeExcel = new ExcelWriter(System.getProperty("user.dir")+"\\src\\test\\resources\\Datasheet_Demo.xlsx");
        	writeExcel.setCellData("Menu","Report_Available",2,"FAIL");
        	throw(e);
        	}

			 }

  
  @DataProvider
  public String[][] loginData() throws InvalidFormatException, IOException{
		ExcelReader read = new ExcelReader();
		return read.getCellData(System.getProperty("user.dir")+"\\src\\test\\resources\\Datasheet_Demo.xlsx","Login");
		}
  
  @DataProvider
  public String[][] reportTest() throws InvalidFormatException, IOException{
		ExcelReader read = new ExcelReader();
		return read.getCellData(System.getProperty("user.dir")+"\\src\\test\\resources\\Datasheet_Demo.xlsx","Report_Locators");
		}

}
