package CLM;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.os.WindowsUtils;

	public class CLM_ST011 {

		public static void main(String[] args) throws IOException, InterruptedException {
			// TODO Auto-generated method stub

			try {
				  WindowsUtils.killByName("firefox.exe");
				  System.out.println("Browser Closed. \n Opening New Browser for: \nGenerate GL Report...");
				  }catch(org.openqa.selenium.os.WindowsRegistryException ignored){
				   System.out.println("No Browser Opened. \n Opening New Browser for: \nGenerate GL Report...");
				  }
			WebDriver clm = new FirefoxDriver();
			clm.manage().window().maximize();
			clm.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			clm.get("http://58.68.10.76:8089/BIH/jsp/main.jsp");
				
			File clmexcel = new File("D:\\DTS_Workspace\\DTS\\src\\test\\resources\\BIH.xlsx");
			FileInputStream Fis = new FileInputStream(clmexcel);
			XSSFWorkbook wb = new XSSFWorkbook(Fis);
			
			XSSFSheet sheet1 = wb.getSheet("Login");
			int lastRow = sheet1.getLastRowNum();
			for (int i=1;i<=lastRow;) 
			{
			XSSFRow row = sheet1.getRow(i);
			
			XSSFCell userCell = row.getCell(0);
			String username = userCell.getStringCellValue();
			clm.findElement(By.id("username")).sendKeys(username);
			XSSFCell passCell = row.getCell(1);
			String password = passCell.getStringCellValue();
			clm.findElement(By.id("password")).sendKeys(password);
			clm.findElement(By.cssSelector(".imageButton")).click();
			break;
			}
			
			clm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(7) > div:nth-child(1) > span:nth-child(1)")).click();
			Thread.sleep(2000);
			clm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(7) > div:nth-child(2) > div:nth-child(3)")).click();
			Thread.sleep(2000);
			
			
			clm.findElement(By.id("frmDate")).click();
			Date date = new Date();
			int hours = date.getHours();
			int minutes = date.getMinutes()+2;
			int seconds = date.getSeconds();
			int day= date.getDate();
			clm.findElement(By.id("calAPIhr")).sendKeys(hours+"");
			clm.findElement(By.id("calAPImin")).clear();
			clm.findElement(By.id("calAPImin")).sendKeys(minutes+"");
	        clm.findElement(By.id("calAPIsec")).sendKeys(seconds+"");
			clm.findElement(By.id("c"+day)).click();
			
			
			//clm.findElement(By.id("idicator")).sendKeys("DAILY");
			clm.findElement(By.id("idicator")).sendKeys("MONTHLY");
			Thread.sleep(2000);
			clm.findElement(By.id("postedId")).click();
			clm.findElement(By.cssSelector("button.btn:nth-child(1)")).click();
			
			System.out.println("\nGenerate GL Report Scheduled.");
		}
		
	}
