package CLM;

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

	public class CLM_ST012 {

		public static void main(String[] args) throws IOException, InterruptedException {
			// TODO Auto-generated method stub

			try {
				  WindowsUtils.killByName("firefox.exe");
				  System.out.println("Browser Closed. \n Opening New Browser for: \nExtract GL...");
				  }catch(org.openqa.selenium.os.WindowsRegistryException ignored){
				   System.out.println("No Browser Opened. \n Opening New Browser for: \nExtract GL...");
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
			
			String CronExpression="0 0 18 5 6 ? *";
			clm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(7) > div:nth-child(1) > span:nth-child(1)")).click();
			Thread.sleep(2000);
			clm.findElement(By.cssSelector("#leftMenuContainer > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(7) > div:nth-child(2) > div:nth-child(1)")).click();
			Thread.sleep(2000);
			
			clm.findElement(By.id("idicatorForReshedule")).sendKeys("ALL");
			//clm.findElement(By.id("idicatorForReshedule")).sendKeys("BILLED");
			//clm.findElement(By.id("idicatorForReshedule")).sendKeys("UNBILLED");
			clm.findElement(By.cssSelector("#container > div:nth-child(4) > fieldset:nth-child(4) > div:nth-child(1) > input:nth-child(3)")).click();
			Thread.sleep(5000);
			System.out.println("\nPreviously Scheduled GL Extract are Removed.");

			clm.findElement(By.id("frmDate")).sendKeys(CronExpression);
			clm.findElement(By.id("idicator")).sendKeys("ALL");
			//clm.findElement(By.id("idicator")).sendKeys("BILLED");
			//clm.findElement(By.id("idicator")).sendKeys("UNBILLED");
			clm.findElement(By.cssSelector("#container > fieldset:nth-child(3) > table:nth-child(18) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > input:nth-child(1)")).click();
			clm.findElement(By.cssSelector("button.btn:nth-child(1)")).click();
			
			System.out.println("\nNew GL Extract Scheduled.");
		}
		
	}
