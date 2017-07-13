package BIH;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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

//Check negative balance in invoice for  over payment & Distribution of negative balance against charges by billing process.

public class BIH_ST044 {

	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		// TODO Auto-generated method stub

		try {
			  WindowsUtils.killByName("firefox.exe");
			  System.out.println("Browser Closed. \n Opening New Browser to check negative balance in invoice for  over payment...");
			  }catch(org.openqa.selenium.os.WindowsRegistryException ignored){
			   System.out.println("No Browser Opened. \n Opening New Browser to check negative balance in invoice for  over payment...");
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
		
		System.out.println("To test this testcase, we need to do a excess payment in SRM to get negative balance in invoice for the account.");

		
		bih.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(1)")).click();

		XSSFSheet sheet3 = wb.getSheet("Account_ID");
		int lastRow2 = sheet3.getLastRowNum();
		System.out.println("There are "+lastRow2+" NETS accounts for invoice generation.");
		for (int i=1;i<=lastRow2;i++) 
		{
		bih.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1)")).click();
		
		XSSFRow row = sheet3.getRow(i);
		XSSFCell ocmaccCell = row.getCell(0);
		int OCMAccountNo = (int) ocmaccCell.getNumericCellValue();
		bih.findElement(By.id("OCMAccountId")).sendKeys(OCMAccountNo+"");
		
		WebElement autocom = bih.findElement(By.id("autocompleteOCMAccountId"));
		WebElement mainList = autocom.findElement(By.tagName("ul"));
		List<WebElement> OCM_id = mainList.findElements(By.tagName("li"));
		WebElement selectOCM= null;
		for (WebElement temp : OCM_id) 
			{
			System.out.println(temp.getText()+" account selected for invoice generation");
			
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
		bih.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
		//bih.findElement(By.cssSelector("tr.even:nth-child(2) > td:nth-child(2) > div:nth-child(1)")).click();
		bih.switchTo().parentFrame();
		Thread.sleep(2000);
		bih.findElement(By.id("submitButton")).click();
		System.out.println("Invoice generation is initiated for Account id:"+OCMAccountNo );
		Thread.sleep(5000);
		}
		System.out.println("Invoice generation completed for all "+lastRow2+" NETS account ID.");
		
		//------------------------******************************-------------------------------
		
		
		bih.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(3) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		bih.findElement(By.xpath(".//div[@id='leftMenuContainer']/table/tbody/tr/td/div[3]/div[2]/div[1]")).click();
		Thread.sleep(2000);
		XSSFSheet sheet4 = wb.getSheet("Account_ID");
		int lastRow3 = sheet4.getLastRowNum();
		System.out.println("\nThere are "+lastRow3+" NETS accounts for download invoice pdf to verify tax.");
		//--------
		for (int i=1;i<=lastRow2;i++) 
		{

		bih.findElement(By.id("viewButton")).click();
		Thread.sleep(2000);
			
		XSSFRow row = sheet4.getRow(i);
		XSSFCell ocmaccCell = row.getCell(1);
		int AccountNo = (int) ocmaccCell.getNumericCellValue();
		System.out.println("Account id from excel is : "+AccountNo);
		bih.findElement(By.id("accountId")).sendKeys(AccountNo+"");
		
		WebElement autocom = bih.findElement(By.id("spanaccountId"));
		WebElement mainList = autocom.findElement(By.tagName("ul"));
		List<WebElement> OCM_id = mainList.findElements(By.tagName("li"));
		
		WebElement selectOCM = null;
		
		for (WebElement temp : OCM_id) 
		{
		System.out.println(temp.getText()+" account selected to view generated invoice");
		
		if(temp.getText().contains(AccountNo+""))
			{
			selectOCM = temp; 
			}
		}
	System.out.println(selectOCM);
	
	selectOCM.click();
	
	Thread.sleep(2000);
	bih.findElement(By.cssSelector(".fa-search")).click();
	Thread.sleep(3000);
	WebElement BillCycComp = bih.findElement(By.xpath("//iframe[contains(@id,'window')]"));
	bih.switchTo().frame(BillCycComp);
	bih.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
	//bih.findElement(By.cssSelector("tr.even:nth-child(2) > td:nth-child(3) > div:nth-child(1)")).click();
	//bih.findElement(By.cssSelector("tr.odd:nth-child(3) > td:nth-child(3) > div:nth-child(1)")).click();
	bih.switchTo().parentFrame();
	System.out.println("Bill Cycle Component Selected");
	Thread.sleep(2000);
	bih.findElement(By.cssSelector("button.formButton:nth-child(1)")).click();

	Thread.sleep(2000);

	if(! bih.findElement(By.cssSelector("#accountInvoicesList > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(1)")).isDisplayed() )                                                                                                       
		{         
		System.out.println("Picking next account, as pdf not available for account id: "+AccountNo);
		
		continue;	
		}    
	
	bih.findElement(By.cssSelector(".odd > td:nth-child(4) > a:nth-child(1)")).click();
	System.out.println("Invoice pdf downloaded for Account id:"+AccountNo );
	Thread.sleep(2000);
	WebElement pdfdownld = bih.findElement(By.id("pdfDialogBox_content"));
	bih.switchTo().frame(pdfdownld);
	Thread.sleep(4000);
	
	bih.findElement(By.id("download")).click();
	Thread.sleep(3000);
    Robot r = new Robot();
    r.keyPress(KeyEvent.VK_ENTER);
    r.keyRelease(KeyEvent.VK_ENTER);  
	Thread.sleep(1000);
    r.keyPress(KeyEvent.VK_ESCAPE);
    r.keyRelease(KeyEvent.VK_ESCAPE);
	bih.switchTo().parentFrame();

    Thread.sleep(1000);
	WebElement pdfclose = bih.findElement(By.id("pdfDialogBox_close"));
	if (pdfclose.isDisplayed()==true) {
	}
	pdfclose.click();
	Thread.sleep(2000);
	}

		
		System.out.println("\nInvoice pdf downloaded for "+lastRow2+" account id");
		Thread.sleep(2000);
		System.out.println("Check negative balance in invoice for  over payment & Distribution of negative balance against charges by billing process..");


	}
	
}
