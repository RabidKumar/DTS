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

public class BIH_ST005 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		try {
			  WindowsUtils.killByName("firefox.exe");
			  System.out.println("Browser Closed. \n Opening New Browser to Create BillCycle...");
			  }catch(org.openqa.selenium.os.WindowsRegistryException ignored){
			   System.out.println("No Browser Opened. \n Opening New Browser to Create BillCycle...");
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
		

		bih.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1)")).click();
		bih.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1)")).click();
		
		bih.findElement(By.id("addNewEntityButton")).click();
		Thread.sleep(5000);
		
		XSSFSheet sheet2 = wb.getSheet("BillCycle");
		int lastRow2 = sheet1.getLastRowNum();
		for (int i=1;i<=lastRow2;i++) 
		{
		XSSFRow row = sheet2.getRow(i);
		
		XSSFCell nameCell = row.getCell(0);
		String name = nameCell.getStringCellValue();
		bih.findElement(By.id("name")).sendKeys(name);
		XSSFCell descCell = row.getCell(1);
		String desc = descCell.getStringCellValue();
		bih.findElement(By.id("description")).sendKeys(desc);
		XSSFCell bcdCell = row.getCell(2);
		int billCycleDate = (int) bcdCell.getNumericCellValue();
		bih.findElement(By.id("billCycleDate")).sendKeys(billCycleDate+"");
		XSSFCell billfreCell = row.getCell(3);
		String billFrequency = billfreCell.getStringCellValue();
		bih.findElement(By.id("billFrequency")).sendKeys(billFrequency);
		XSSFCell yearCell = row.getCell(4);
		int year = (int) yearCell.getNumericCellValue();
		bih.findElement(By.id("year")).sendKeys(year+"");
		XSSFCell nobcCell = row.getCell(5);
		int numberOfBillCycle = (int) nobcCell.getNumericCellValue();
		bih.findElement(By.id("numberOfBillCycle")).sendKeys(numberOfBillCycle+"");
		XSSFCell bdiCell = row.getCell(6);
		int billDateInterval = (int) bdiCell.getNumericCellValue();
		bih.findElement(By.id("billDateInterval")).sendKeys(billDateInterval+"");
		//bih.findElement(By.id("billcycle")).click();
		System.out.println("BillCycle Created");
		}		
	}

}
