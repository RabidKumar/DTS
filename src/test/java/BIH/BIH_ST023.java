package BIH;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
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

public class BIH_ST023 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		try {
			  WindowsUtils.killByName("firefox.exe");
			  System.out.println("Browser Closed. \n Opening New Browser for: \nSingle Invoice Reverse Posting...");
			  }catch(org.openqa.selenium.os.WindowsRegistryException ignored){
			   System.out.println("No Browser Opened. \n Opening New Browser for: \nSingle Invoice Reverse Posting...");
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
				
		bih.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(5) > div:nth-child(1) > span:nth-child(1)")).click();

		XSSFSheet sheet3 = wb.getSheet("Account_ID");
		int lastRow2 = sheet3.getLastRowNum();
		System.out.println("There are "+lastRow2+" NETS accounts for invoice generation.");
		for (int i=1;i<=lastRow2;i++) 
		{
		bih.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(5) > div:nth-child(2) > div:nth-child(1)")).click();
		
		XSSFRow row = sheet3.getRow(i);
		XSSFCell ocmaccCell = row.getCell(1);
		int OCMAccountNo = (int) ocmaccCell.getNumericCellValue();
		bih.findElement(By.id("accountId")).sendKeys(OCMAccountNo+"");
		
		WebElement autocom = bih.findElement(By.id("autocompleteaccountId"));
		WebElement mainList = autocom.findElement(By.tagName("ul"));
		List<WebElement> OCM_id = mainList.findElements(By.tagName("li"));
		WebElement selectOCM= null;
		for (WebElement temp : OCM_id) 
			{
			System.out.println(temp.getText()+" account selected for Reverse posting");
			
			if(temp.getText().contains(OCMAccountNo+""))
				{
				selectOCM = temp; 
				}
			}
		selectOCM.click();
		
		Thread.sleep(2000);
		bih.findElement(By.cssSelector(".fa-search")).click();
		Thread.sleep(3000);
		WebElement BillCycComp = bih.findElement(By.xpath("//iframe[contains(@id,'window')]"));
		bih.switchTo().frame(BillCycComp);
		System.out.println("Bill Cycle Component Selected");
		
		bih.findElement(By.cssSelector("tr.even:nth-child(2) > td:nth-child(2) > div:nth-child(1)")).click();
		Thread.sleep(2000);

		//bih.findElement(By.cssSelector("button.btn:nth-child(1)")).click();

		bih.switchTo().parentFrame();
		Thread.sleep(2000);
		bih.findElement(By.id("submitButton")).click();
		Thread.sleep(3000);

		System.out.println("Invoice posted for Account id:"+OCMAccountNo );
		Thread.sleep(5000);
		}
		
		System.out.println("Invoice posting completed for all "+lastRow2+" account ID.");
	
	}
	
}
