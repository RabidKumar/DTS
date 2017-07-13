package BIH;


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

public class BIH_ST014 {

	public static void main(String[] args) throws IOException, InterruptedException, Exception {
		try {
			  WindowsUtils.killByName("firefox.exe");
			  System.out.println("Browser Closed. \n\nOpening New Browser for: \nTrack Bill Scheduler.");
			  }catch(org.openqa.selenium.os.WindowsRegistryException ignored){
			   System.out.println("No Browser Opened. \n\nOpening New Browser for: \nTrack Bill Scheduler.");
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
		
		bih.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(3) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		bih.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2)")).click();
		Thread.sleep(2000);
		bih.findElement(By.id("viewAll")).click();
		Thread.sleep(2000);		
		bih.findElement(By.id("refreshTable")).click();
		Thread.sleep(2000);

		System.out.println("\nView all jobs to check the status.");
	}
	
}
