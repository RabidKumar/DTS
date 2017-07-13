package SRM;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.os.WindowsUtils;



	public class SRM_ST051 {

		public static void main(String[] args) throws IOException, InterruptedException, AWTException {
			// TODO Auto-generated method stub

			try {
				  WindowsUtils.killByName("firefox.exe");
				  System.out.println("Browser Closed. \nOpening New Browser for: \nBulk Payment...");
				  }catch(org.openqa.selenium.os.WindowsRegistryException ignored){
				   System.out.println("No Browser Opened. \nOpening New Browser for: \nBulk Payment...");
				  }
			WebDriver srm = new FirefoxDriver();
			srm.manage().window().maximize();
			srm.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			srm.get("http://58.68.10.76:8200/SRM/jsp/main.jsp");
			File srmexcel = new File("D:\\DTS_Workspace\\DTS\\src\\test\\resources\\SRM.xlsx");
			FileInputStream Fis = new FileInputStream(srmexcel);
			XSSFWorkbook wb = new XSSFWorkbook(Fis);
			XSSFSheet sheet1 = wb.getSheet("Login");
			int lastRow = sheet1.getLastRowNum();
			String username="";
			for (int i=1;i<=lastRow;) 
			{
			XSSFRow row = sheet1.getRow(i);
			XSSFCell userCell = row.getCell(0);
			username= userCell.getStringCellValue();
			srm.findElement(By.id("username")).sendKeys(username);
			XSSFCell passCell = row.getCell(1);
			String password = passCell.getStringCellValue();
			srm.findElement(By.id("password")).sendKeys(password);
			srm.findElement(By.cssSelector(".imageButton")).click();
			break;
			}
			
			int DTSAccountID=129221;

			srm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(1)")).click();
			Thread.sleep(2000);
			srm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2)")).click();
			Thread.sleep(2000);
			System.out.println("Selecting the file for Bulk Payment");

			srm.findElement(By.id("file-1")).click();
            StringSelection filepath = new StringSelection("D:\\DTS_Workspace\\DTS\\src\\test\\resources\\BulkPayment_76-2017060604.csv");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath, null);
			Robot r = new Robot();
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_C);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_V);
			Thread.sleep(4000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			srm.findElement(By.id("uploadButton")).click();
			Thread.sleep(3000);

			srm.findElement(By.id("InfoBoxButton")).click();

			System.out.println("Bulk Payment File Uploaded Successfully, it will be automatically approved in PTE");
			System.out.println("View the payment done through Bulk Paymant");
			Thread.sleep(10000);

			srm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1)")).click();
			Thread.sleep(2000);
			srm.findElement(By.id("account.id")).sendKeys(DTSAccountID+"");
			Thread.sleep(2000);
		
			srm.findElement(By.id("searchButton")).click();
			Thread.sleep(2000);
			srm.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(3) > a:nth-child(1)")).click();
			Thread.sleep(2000);

			System.out.println("View the Bulk Payment which is in approved status");

			
			
			
		}
		
	}
