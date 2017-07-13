package PTE;
//In PTE all approval are same;

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

	public class PTE_ALL_APPROVAL {

		public static void main(String[] args) throws IOException, InterruptedException {
			// TODO Auto-generated method stub

			try {
				  WindowsUtils.killByName("firefox.exe");
				  System.out.println("Browser Closed. \n Opening New Browser for: \nAdjustment...");
				  }catch(org.openqa.selenium.os.WindowsRegistryException ignored){
				   System.out.println("No Browser Opened. \n Opening New Browser for: \nAdjustment...");
				  }
			System.out.println("\nIn PTE all approval having same steps, \nSo taking the example of SRM Adjustment having 2 level Approval.");

			WebDriver pte = new FirefoxDriver();
			pte.manage().window().maximize();
			pte.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			System.out.println("\nOpening...http://58.68.10.76:8200/SRM/jsp/main.jsp");
			pte.get("http://58.68.10.76:8200/SRM/jsp/main.jsp");
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
			pte.findElement(By.id("username")).sendKeys(username1);
			XSSFCell passCell = row.getCell(1);
			String password = passCell.getStringCellValue();
			pte.findElement(By.id("password")).sendKeys(password);
			pte.findElement(By.cssSelector(".imageButton")).click();
			break;
			}
			System.out.println("\nLogged In to "+username1+" user.");

			int DTSAccountID=128236;
			System.out.println("Initiating Adjustment for account id: "+DTSAccountID);

			pte.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(3) > div:nth-child(1) > span:nth-child(1)")).click();
			Thread.sleep(2000);
			pte.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1)")).click();
			//pte.findElement(By.cssSelector("")).click();

			Thread.sleep(2000);
			pte.findElement(By.id("account.id")).sendKeys(DTSAccountID+"");
			Thread.sleep(2000);
		
			pte.findElement(By.id("searchButton")).click();
			Thread.sleep(2000);
			pte.findElement(By.cssSelector(".odd > td:nth-child(3) > a:nth-child(1)")).click();
			Thread.sleep(3000);

			pte.findElement(By.id("addDetail")).click();
			System.out.println("Adding New Adjustment");

			
			//----------

			Thread.sleep(2000);
			pte.findElement(By.xpath("//div[@id='spanadjustmentCode.id']/span[3]")).click();
			Thread.sleep(5000);
			WebElement AdjustmentCode = pte.findElement(By.xpath("//iframe[contains(@id,'window')]"));
			pte.switchTo().frame(AdjustmentCode);
			Thread.sleep(1000);
			pte.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
			pte.switchTo().parentFrame();
			System.out.println("Adjustment Code Selected.");
			Thread.sleep(1000);

			
			
			Thread.sleep(2000);
			pte.findElement(By.xpath("//div[@id='spanadjustmentReasonCode.id']/span[3]")).click();
			Thread.sleep(5000);
			WebElement AdjustmentReasonCode = pte.findElement(By.xpath("//iframe[contains(@id,'window')]"));
			pte.switchTo().frame(AdjustmentReasonCode);
			Thread.sleep(1000);
			pte.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
			pte.switchTo().parentFrame();
			System.out.println("Adjustment Reason Code Selected.");
			Thread.sleep(1000);			
			
			//pte.findElement(By.id("annotation")).sendKeys("Adjustment Done");
			
			Thread.sleep(2000);
			pte.findElement(By.xpath("//div[@id='spaninvoiceNumber.id']/span[3]")).click();
			Thread.sleep(5000);
			WebElement InvoiceNumber = pte.findElement(By.xpath("//iframe[contains(@id,'window')]"));
			pte.switchTo().frame(InvoiceNumber);
			Thread.sleep(1000);
			pte.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
			pte.switchTo().parentFrame();
			System.out.println("Invoice Number Selected.");
			Thread.sleep(1000);

			pte.findElement(By.id("calculationType")).sendKeys("Fixed");
			Thread.sleep(1000);
			//pte.findElement(By.id("calculationType")).sendKeys("Percentage");
			pte.findElement(By.id("amount")).sendKeys("1");
			System.out.println("\nNew Adjustment details given.");

			Thread.sleep(1000);
			pte.findElement(By.id("saveAddNewAdjustment")).click();
			Thread.sleep(2000);
			  
			pte.findElement(By.id("InfoBoxButton")).click();
			
			Thread.sleep(5000);

			System.out.println("\nAdjustment initiated Sucessfully, Need to send for approval.");

			//pte.findElement(By.cssSelector("")).click();
		    pte.findElement(By.cssSelector("#AdjustmentSummaryComponentDiv > fieldset:nth-child(2) > div:nth-child(2) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(10) > img:nth-child(1)")).click();


		    String ApprovalText = pte.findElement(By.id("liElement")).getText();
		    String[] split = ApprovalText.split(":");
		    String InstanceId = split[1];
		    
			System.out.println(ApprovalText);
			System.out.println("\nAdjustment send for approval having instance id:"+InstanceId+", Plz approve it from PTE.");
			Thread.sleep(5000);
		    
		    //--------------------------**********************************----------------------
			System.out.println("\nAdjustment is having 2 level of approval, So need to approve the Adjustment from 2 different user.");

			System.out.println("To go to PTE for 1st level approval, we need to logout & login to different user.");
			
			System.out.println("\nLogging Out from "+username1+" user.");

			pte.findElement(By.cssSelector(".account > b:nth-child(2)")).click();
			Thread.sleep(1000);
			pte.findElement(By.cssSelector(".loginDropdown > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1) > span:nth-child(2)")).click();
			Thread.sleep(3000);
			pte.findElement(By.cssSelector("button.btn:nth-child(1)")).click();
			
			System.out.println("\nLogged Out from "+username1+" user");
			Thread.sleep(2000);

			System.out.println("\nOpening PTE for 2nd level approval of Adjustment from different User.");

			pte.get("http://58.68.10.76:8098/PTE/jsp/main.jsp");
			Thread.sleep(5000);

			pte.findElement(By.id("loginAsAnother")).click();
			Thread.sleep(3000);
			String username2="";
			for (int i=2;i<=lastRow;) 
			{
			XSSFRow row = sheet1.getRow(i);
			XSSFCell userCell = row.getCell(0);
			username2 = userCell.getStringCellValue();
			pte.findElement(By.id("username")).sendKeys(username2);
			XSSFCell passCell = row.getCell(1);
			String password = passCell.getStringCellValue();
			pte.findElement(By.id("password")).sendKeys(password);
			pte.findElement(By.cssSelector(".imageButton")).click();
			break;
			}
			System.out.println("\nLogging in to "+username2+" user");

			pte.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(1)")).click();
			Thread.sleep(2000);
			pte.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(3)")).click();
			Thread.sleep(2000);
			System.out.println("Searching for Workflow instance ID");

			pte.findElement(By.id("workflowInstanceSearch")).sendKeys(InstanceId);
			Thread.sleep(2000);
			pte.findElement(By.cssSelector("#workflowInstanceForm > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > input:nth-child(3)")).click();
			Thread.sleep(2000);
			System.out.println("Approving 1st level Adjustment task");

			pte.findElement(By.linkText("Update")).click();
			WebElement ApproveTask = pte.findElement(By.xpath("//iframe[contains(@id,'window')]"));
			pte.switchTo().frame(ApproveTask);
			Thread.sleep(3000);
			pte.findElement(By.id("comment")).sendKeys("Approved");
			Thread.sleep(1000);
			pte.findElement(By.id("submitButton")).click();
			Thread.sleep(1000);
			pte.findElement(By.id("InfoBoxButton")).click();
			pte.switchTo().parentFrame();
			Thread.sleep(2000);
			System.out.println("1st level approval for Adjustment is done");
			Thread.sleep(5000);


		    //--------------------------**********************************----------------------
			System.out.println("\nAdjustment is having 2 level of approval, 1st approval done from "+username2+ "user");

			System.out.println("\nTo go to PTE for 2st level approval, we need to logout & login to different user.");
			
			System.out.println("\nLogging Out from "+username2+" user.");

			pte.findElement(By.cssSelector(".account > b:nth-child(2)")).click();
			Thread.sleep(1000);
			pte.findElement(By.cssSelector(".loginDropdown > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1) > span:nth-child(2)")).click();
			Thread.sleep(3000);
			pte.findElement(By.cssSelector("button.btn:nth-child(1)")).click();
			
			System.out.println("\nLogged Out from "+username2+" user");
			Thread.sleep(1000);

			System.out.println("\nOpening PTE for Adjustment 2nd level approval, from different User.");

			pte.get("http://58.68.10.76:8098/PTE/jsp/main.jsp");
			Thread.sleep(20000);

			pte.findElement(By.id("loginAsAnother")).click();
			Thread.sleep(3000);
			String username3="";
			for (int i=3;i<=lastRow;) 
			{
			XSSFRow row = sheet1.getRow(i);
			XSSFCell userCell = row.getCell(0);
			username3 = userCell.getStringCellValue();
			pte.findElement(By.id("username")).sendKeys(username3);
			XSSFCell passCell = row.getCell(1);
			String password = passCell.getStringCellValue();
			pte.findElement(By.id("password")).sendKeys(password);
			pte.findElement(By.cssSelector(".imageButton")).click();
			break;
			}
			System.out.println("\nLogging in to "+username3+" user");
			Thread.sleep(20000);

			pte.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(1)")).click();
			Thread.sleep(3000);
			pte.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(3)")).click();
			Thread.sleep(3000);
			System.out.println("Searching for Workflow instance ID");

			pte.findElement(By.id("workflowInstanceSearch")).sendKeys(InstanceId);
			Thread.sleep(3000);
			pte.findElement(By.cssSelector("#workflowInstanceForm > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > input:nth-child(3)")).click();
			Thread.sleep(2000);
			System.out.println("Approving 2nd level Adjustment task.");

			pte.findElement(By.linkText("Update")).click();
			WebElement ApproveTask2 = pte.findElement(By.xpath("//iframe[contains(@id,'window')]"));
			pte.switchTo().frame(ApproveTask2);
			Thread.sleep(3000);
			pte.findElement(By.id("comment")).sendKeys("Approved");
			Thread.sleep(1000);
			pte.findElement(By.id("submitButton")).click();
			Thread.sleep(1000);
			pte.findElement(By.id("InfoBoxButton")).click();
			pte.switchTo().parentFrame();
			Thread.sleep(2000);
			System.out.println("2nd level is also done, So Adjustment Approved.");

		    //--------------------------**********************************----------------------

			//--- Approving once again for PTE Issue...


		    //--------------------------**********************************----------------------
			System.out.println("\nAdjustment is having 2nd level 2nd times of approval, 2nd approval done from "+username3+ "user");

			System.out.println("\nTo go to PTE for 2nd level 2nd times approval, we need to logout & login to different user.");
			
			System.out.println("\nLogging Out from "+username3+" user.");

			pte.findElement(By.cssSelector(".account > b:nth-child(2)")).click();
			Thread.sleep(1000);
			pte.findElement(By.cssSelector(".loginDropdown > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1) > span:nth-child(2)")).click();
			Thread.sleep(3000);
			pte.findElement(By.cssSelector("button.btn:nth-child(1)")).click();
			
			System.out.println("\nLogged Out from "+username3+" user");
			Thread.sleep(1000);

			System.out.println("\nOpening PTE for Adjustment 2nd level 2nd times approval, from different User.");

			pte.get("http://58.68.10.76:8098/PTE/jsp/main.jsp");
			Thread.sleep(20000);

			pte.findElement(By.id("loginAsAnother")).click();
			Thread.sleep(3000);
			String username4="";
			for (int i=4;i<=lastRow;) 
			{
			XSSFRow row = sheet1.getRow(i);
			XSSFCell userCell = row.getCell(0);
			username4 = userCell.getStringCellValue();
			pte.findElement(By.id("username")).sendKeys(username4);
			XSSFCell passCell = row.getCell(1);
			String password = passCell.getStringCellValue();
			pte.findElement(By.id("password")).sendKeys(password);
			pte.findElement(By.cssSelector(".imageButton")).click();
			break;
			}
			System.out.println("\nLogging in to "+username4+" user");

			Thread.sleep(20000);

			pte.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(1)")).click();
			Thread.sleep(3000);
			pte.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(3)")).click();
			Thread.sleep(3000);
			System.out.println("Searching for Workflow instance ID");

			pte.findElement(By.id("workflowInstanceSearch")).sendKeys(InstanceId);
			Thread.sleep(3000);
			pte.findElement(By.cssSelector("#workflowInstanceForm > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > input:nth-child(3)")).click();
			Thread.sleep(2000);
			System.out.println("Approving 2nd level 2nd times Adjustment task.");

			pte.findElement(By.linkText("Update")).click();
			WebElement ApproveTask3 = pte.findElement(By.xpath("//iframe[contains(@id,'window')]"));
			pte.switchTo().frame(ApproveTask3);
			Thread.sleep(3000);
			pte.findElement(By.id("comment")).sendKeys("Approved");
			Thread.sleep(1000);
			pte.findElement(By.id("submitButton")).click();
			Thread.sleep(1000);
			pte.findElement(By.id("InfoBoxButton")).click();
			pte.switchTo().parentFrame();
			Thread.sleep(2000);
			System.out.println("2nd level 2nd times is also done, So Adjustment Approved.");

		    //--------------------------**********************************----------------------

			
			System.out.println("Back to SRM to view the Adjustment");
			Thread.sleep(10000);
			
			pte.get("http://58.68.10.76:8200/SRM/jsp/main.jsp");
			Thread.sleep(5000);

			pte.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(3) > div:nth-child(1) > span:nth-child(1)")).click();
			Thread.sleep(2000);
			pte.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1)")).click();
			//pte.findElement(By.cssSelector("")).click();

			Thread.sleep(2000);
			pte.findElement(By.id("account.id")).sendKeys(DTSAccountID+"");
			Thread.sleep(2000);
		
			pte.findElement(By.id("searchButton")).click();
			Thread.sleep(2000);
			pte.findElement(By.cssSelector(".odd > td:nth-child(3) > a:nth-child(1)")).click();
			Thread.sleep(3000);System.out.println("View Adjustment which is in approved status");
			
		}
		
	}
