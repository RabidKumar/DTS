package BIH;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
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

public class BIH_ST021 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		try {
			  WindowsUtils.killByName("firefox.exe");
			  System.out.println("Browser Closed. \n Opening New Browser for: \nBill Posting for Bill Cycle...");
			  }catch(org.openqa.selenium.os.WindowsRegistryException ignored){
			   System.out.println("No Browser Opened. \n Opening New Browser for: \nBill Posting for Bill Cycle...");
			  }
		WebDriver bih = new FirefoxDriver();
		bih.manage().window().maximize();
		bih.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		bih.get("http://58.68.10.76:8089/BIH/jsp/main.jsp");
			
		File BIHexcel = new File("D:\\DTS_Workspace\\DTS\\src\\test\\resources\\BIH.xlsx");
		FileInputStream Fis = new FileInputStream(BIHexcel);
		XSSFWorkbook wb = new XSSFWorkbook(Fis);
		
		XSSFSheet sheet1 = wb.getSheet("Login");
		int lastRow = sheet1.getLastRowNum();
		for (int i=1;i<=lastRow;) 
		{
		XSSFRow row = sheet1.getRow(i);
		
		XSSFCell userCell = row.getCell(0);
		String username = userCell.getStringCellValue();
		bih.findElement(By.id("username")).sendKeys(username);
		XSSFCell passCell = row.getCell(1);
		String password = passCell.getStringCellValue();
		bih.findElement(By.id("password")).sendKeys(password);
		bih.findElement(By.cssSelector(".imageButton")).click();
		break;
		}
				
		bih.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(4) > div:nth-child(1) > span:nth-child(1)")).click();
		Thread.sleep(2000);
		bih.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(4) > div:nth-child(2) > div:nth-child(2)")).click();
		Thread.sleep(2000);

		bih.findElement(By.cssSelector(".fa-search")).click();
		Thread.sleep(3000);
		WebElement BillCycle = bih.findElement(By.xpath("//iframe[contains(@id,'window')]"));
		bih.switchTo().frame(BillCycle);
		Thread.sleep(3000);
		bih.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
		bih.switchTo().parentFrame();
		System.out.println("Bill Cycle Selected");
		Thread.sleep(3000);

		bih.findElement(By.xpath("//div[@id='spanbillCycleComponent.id']/span[3]")).click();
		Thread.sleep(3000);
		WebElement BillCycComp = bih.findElement(By.xpath("//iframe[contains(@id,'window')]"));
		bih.switchTo().frame(BillCycComp);
		Thread.sleep(3000);
		bih.findElement(By.cssSelector("tr.even:nth-child(2) > td:nth-child(2) > div:nth-child(1)")).click();
		bih.switchTo().parentFrame();
		System.out.println("Bill Cycle Component Selected");
		Thread.sleep(3000);
		
		bih.findElement(By.id("calenderImage")).click();
		Date date = new Date();
		
		int hours = date.getHours();
		int minutes = date.getMinutes()+2;
		int seconds = date.getSeconds();
		
		int day= date.getDate();
		bih.findElement(By.id("calAPIhr")).sendKeys(hours+"");
		
		bih.findElement(By.id("calAPImin")).clear();
		bih.findElement(By.id("calAPImin")).sendKeys(minutes+"");
        bih.findElement(By.id("calAPIsec")).sendKeys(seconds+"");
		bih.findElement(By.id("c"+day)).click();
		
		bih.findElement(By.id("submitButton")).click();

		System.out.println("\nBill Posting for Bill Cycle Scheduled.");
	}
	
}
