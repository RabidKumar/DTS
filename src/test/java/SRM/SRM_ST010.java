package SRM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.os.WindowsUtils;

	public class SRM_ST010 {

		public static void main(String[] args) throws IOException, InterruptedException {
			// TODO Auto-generated method stub

			try {
				  WindowsUtils.killByName("firefox.exe");
				  System.out.println("Browser Closed. \n Opening New Browser for: \nPositive Adjustment...");
				  }catch(org.openqa.selenium.os.WindowsRegistryException ignored){
				   System.out.println("No Browser Opened. \n Opening New Browser for: \nPositive Adjustment...");
				  }
			WebDriver srm = new FirefoxDriver();
			srm.manage().window().maximize();
			srm.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			System.out.println("\nOpening...http://58.68.10.76:8200/SRM/jsp/main.jsp");
			srm.get("http://58.68.10.76:8200/SRM/jsp/main.jsp");
			File srmexcel = new File("D:\\DTS_Workspace\\DTS\\src\\test\\resources\\SRM.xlsx");
			FileInputStream Fis = new FileInputStream(srmexcel);
			XSSFWorkbook wb = new XSSFWorkbook(Fis);
			XSSFSheet sheet1 = wb.getSheet("Login");
			int lastRow = sheet1.getLastRowNum();
			String username1="";
			for (int i=1;i<=lastRow;) 
			{
			XSSFRow row = sheet1.getRow(i);
			XSSFCell userCell = row.getCell(0);
			username1= userCell.getStringCellValue();
			srm.findElement(By.id("username")).sendKeys(username1);
			XSSFCell passCell = row.getCell(1);
			String password = passCell.getStringCellValue();
			srm.findElement(By.id("password")).sendKeys(password);
			srm.findElement(By.cssSelector(".imageButton")).click();
			break;
			}
			
			System.out.println("\nLogged In to "+username1+" user.");
			
			int DTSAccountID=124831;
			System.out.println("Initiating Positive Adjustment for account id: "+DTSAccountID);
			srm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(4) > div:nth-child(1) > span:nth-child(1)")).click();
			Thread.sleep(2000);
			srm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(4) > div:nth-child(2) > div:nth-child(1)")).click();
			//srm.findElement(By.cssSelector("")).click();

			Thread.sleep(2000);
			srm.findElement(By.id("account.id")).sendKeys(DTSAccountID+"");
			Thread.sleep(2000);
		
			srm.findElement(By.id("searchButton")).click();
			Thread.sleep(2000);
			srm.findElement(By.cssSelector(".odd > td:nth-child(3) > a:nth-child(1)")).click();
			Thread.sleep(3000);

			srm.findElement(By.id("addDetail")).click();
			System.out.println("Adding New Positive Adjustment");

			
			//----------

			Thread.sleep(2000);
			srm.findElement(By.xpath("//div[@id='spanadjustmentCode.id']/span[3]")).click();
			Thread.sleep(5000);
			WebElement PositiveAdjustmentCode = srm.findElement(By.xpath("//iframe[contains(@id,'window')]"));
			srm.switchTo().frame(PositiveAdjustmentCode);
			Thread.sleep(1000);
			srm.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
			srm.switchTo().parentFrame();
			System.out.println("Positive Adjustment Code Selected.");
			Thread.sleep(1000);

			
			
			Thread.sleep(2000);
			srm.findElement(By.xpath("//div[@id='spanadjustmentReasonCode.id']/span[3]")).click();
			Thread.sleep(5000);
			WebElement PositiveAdjustmentReasonCode = srm.findElement(By.xpath("//iframe[contains(@id,'window')]"));
			srm.switchTo().frame(PositiveAdjustmentReasonCode);
			Thread.sleep(1000);
			srm.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
			srm.switchTo().parentFrame();
			System.out.println("Positive Adjustment Reason Code Selected.");
			Thread.sleep(1000);			
			
		
			Thread.sleep(2000);
			srm.findElement(By.xpath("//div[@id='spanfeature.id']/span[3]")).click();
			Thread.sleep(5000);
			WebElement Feature = srm.findElement(By.xpath("//iframe[contains(@id,'window')]"));
			srm.switchTo().frame(Feature);
			Thread.sleep(1000);
			srm.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
			srm.switchTo().parentFrame();
			System.out.println("Invoice Number Selected.");
			Thread.sleep(1000);
			
			
			srm.findElement(By.id("txtaccountBalanceDetail.id")).click();

			Thread.sleep(1000);
			srm.findElement(By.id("saveAddNewPositiveAdjustment")).click();
			Thread.sleep(2000);
			  
			srm.findElement(By.id("InfoBoxButton")).click();
			
			Thread.sleep(5000);

			System.out.println("\nPositive Adjustment initiated Sucessfully, Need to send for approval.");

			//srm.findElement(By.cssSelector("")).click();
		    srm.findElement(By.cssSelector("#PositiveAdjustmentComponentDiv > fieldset:nth-child(2) > div:nth-child(2) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(10) > img:nth-child(1)")).click();


		    String ApprovalText = srm.findElement(By.id("liElement")).getText();
		    String[] split = ApprovalText.split(":");
		    String InstanceId = split[1];
		    
			System.out.println(ApprovalText);
			System.out.println("\nPositive Adjustment send for approval having instance id:"+InstanceId+", Plz approve it from PTE.");
			Thread.sleep(5000);
		    
		    //--------------------------**********************************----------------------

			System.out.println("To go to PTE for approval, we need to logout & login to different user.");
			
			System.out.println("\nLogging Out from "+username1+" user.");

			srm.findElement(By.cssSelector(".account > b:nth-child(2)")).click();
			Thread.sleep(1000);
			srm.findElement(By.cssSelector(".loginDropdown > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1) > span:nth-child(2)")).click();
			Thread.sleep(3000);
			srm.findElement(By.cssSelector("button.btn:nth-child(1)")).click();
			
			System.out.println("\nLogged Out from "+username1+" user");
			Thread.sleep(2000);

			System.out.println("\nOpening PTE for approval of Positive Adjustment from different User.");

			srm.get("http://58.68.10.76:8098/PTE/jsp/main.jsp");
			Thread.sleep(5000);

			srm.findElement(By.id("loginAsAnother")).click();
			Thread.sleep(3000);
			String username2="";
			for (int i=2;i<=lastRow;) 
			{
			XSSFRow row = sheet1.getRow(i);
			XSSFCell userCell = row.getCell(0);
			username2 = userCell.getStringCellValue();
			srm.findElement(By.id("username")).sendKeys(username2);
			XSSFCell passCell = row.getCell(1);
			String password = passCell.getStringCellValue();
			srm.findElement(By.id("password")).sendKeys(password);
			srm.findElement(By.cssSelector(".imageButton")).click();
			break;
			}
			System.out.println("\nLogging in to "+username2+" user");

			srm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(1)")).click();
			Thread.sleep(2000);
			srm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(3)")).click();
			Thread.sleep(2000);
			System.out.println("Searching for Workflow instance ID");

			srm.findElement(By.id("workflowInstanceSearch")).sendKeys(InstanceId);
			Thread.sleep(2000);
			srm.findElement(By.cssSelector("#workflowInstanceForm > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > input:nth-child(3)")).click();
			Thread.sleep(2000);
			System.out.println("Approving Positive Adjustment task");

			srm.findElement(By.linkText("Update")).click();
			WebElement ApproveTask = srm.findElement(By.xpath("//iframe[contains(@id,'window')]"));
			srm.switchTo().frame(ApproveTask);
			Thread.sleep(3000);
			srm.findElement(By.id("comment")).sendKeys("Approved");
			Thread.sleep(1000);
			srm.findElement(By.id("submitButton")).click();
			Thread.sleep(1000);
			srm.findElement(By.id("InfoBoxButton")).click();
			srm.switchTo().parentFrame();
			Thread.sleep(2000);
			System.out.println("Approval for Positive Adjustment is done");
			Thread.sleep(5000);


			System.out.println("Back to SRM to view the PositiveAdjustment");
			Thread.sleep(10000);
			
			srm.get("http://58.68.10.76:8200/SRM/jsp/main.jsp");
			Thread.sleep(5000);

			srm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(4) > div:nth-child(1) > span:nth-child(1)")).click();
			Thread.sleep(2000);
			srm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(4) > div:nth-child(2) > div:nth-child(1)")).click();

			Thread.sleep(2000);
			srm.findElement(By.id("account.id")).sendKeys(DTSAccountID+"");
			Thread.sleep(2000);
		
			srm.findElement(By.id("searchButton")).click();
			Thread.sleep(2000);
			srm.findElement(By.cssSelector(".odd > td:nth-child(3) > a:nth-child(1)")).click();
			Thread.sleep(3000);System.out.println("View Positive Adjustment which is in approved status");
			
		}
		
	}
