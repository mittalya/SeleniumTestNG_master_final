package POM;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class teamContent2 {
		WebDriver driver;
		public teamContent2 (WebDriver driver) {
			this.driver = driver;
	//		PageFactory.initElements(driver, this);
		}
		
		public void ClickTeamContent(String Locator_Team_Content) {
			if (Locator_Team_Content != ""){
			WebElement Teamcontents = driver.findElement(By.xpath(Locator_Team_Content));
			Teamcontents.click();
			}
			}
		public void OpenFolder(String Locator_Folder_1){
			if (Locator_Folder_1 != ""){
			WebElement FirstFolder = driver.findElement(By.xpath(Locator_Folder_1));
			FirstFolder.click();
			}
		}
		
		public void ClickFolder2(String Locator_Folder_2){
			if (Locator_Folder_2 != ""){
			WebElement SecondFolder= driver.findElement(By.xpath(Locator_Folder_2));
			SecondFolder.click();
			driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		}
		}
		
		public void ClickFolder3(String Locator_Folder_3){
			if (Locator_Folder_3 != ""){
			WebElement ThirdFolder= driver.findElement(By.xpath(Locator_Folder_3));
			ThirdFolder.click();
			driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		}
		}
		
		public void ClickFolder4(String Locator_Folder_4){
			if (Locator_Folder_4 != ""){
			WebElement FourthFolder= driver.findElement(By.xpath(Locator_Folder_4));
			FourthFolder.click();
			driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		}
		}	
		
		public void ClickReport(String Locator_Report){
			if (Locator_Report != ""){
			WebElement Report= driver.findElement(By.xpath(Locator_Report));
			Report.click();
			driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		}
		}
		
		public void ReportLoad(String Locator_Report_Data){
//			WebElement ReportLocator= driver.findElement(By.xpath("Locator_Report_Data"));
		WebDriverWait Waits = new WebDriverWait(driver,20);
		Waits.until (ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator_Report_Data)));
		}
}

