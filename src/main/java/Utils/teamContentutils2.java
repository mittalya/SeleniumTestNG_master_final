package Utils;

import org.openqa.selenium.WebDriver;
import POM.teamContent2;

public class teamContentutils2 {
	WebDriver driver;
	public teamContentutils2 (WebDriver driver){
		this.driver = driver;
	}
	public void ClickTeamContent(String Locator_Team_Content) {
		if (Locator_Team_Content != ""){
		teamContent2 team = new teamContent2(driver);
		team.ClickTeamContent(Locator_Team_Content);
	}
	}
	public void ClickFolder1(String Locator_Folder_1) {
		if (Locator_Folder_1 != ""){
		teamContent2 folder = new teamContent2(driver);
		folder.OpenFolder(Locator_Folder_1);
	}
	}
	
	public void ClickFolder2(String Locator_Folder_2) {
		if (Locator_Folder_2 != ""){
		teamContent2 folder2 = new teamContent2(driver);
		folder2.ClickFolder2(Locator_Folder_2);
	}
	}
	
	public void ClickFolder3(String Locator_Folder_3) {
		if (Locator_Folder_3 != ""){
		teamContent2 folder3 = new teamContent2(driver);
		folder3.ClickFolder3(Locator_Folder_3);
	}
	}
	
	public void ClickFolder4(String Locator_Folder_4) {
		if (Locator_Folder_4 != ""){
		teamContent2 folder4 = new teamContent2(driver);
		folder4.ClickFolder4(Locator_Folder_4);
	}
	}
	
	public void ClickReport1(String Locator_Report) {
		if (Locator_Report != ""){
		teamContent2 report = new teamContent2(driver);
		report.ClickReport(Locator_Report);
		}
	}
	
	public void WaitForReportLoad(String Locator_Report_Data) {
		if (Locator_Report_Data != ""){
		teamContent2 reportload = new teamContent2(driver);
		reportload.ReportLoad(Locator_Report_Data);
	}
	}
}
