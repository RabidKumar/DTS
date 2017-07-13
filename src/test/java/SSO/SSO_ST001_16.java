package SSO;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.os.WindowsUtils;
	
	public class SSO_ST001_16 
	{

		public static void main(String[] args) throws IOException, InterruptedException, AWTException 
		{
			// TODO Auto-generated method stub

			try 
			{
			WindowsUtils.killByName("firefox.exe");
			System.out.println("Browser Closed. \nOpening DTS SSO...");
			}
			catch(org.openqa.selenium.os.WindowsRegistryException ignored)
			{
			System.out.println("No Browser Opened. \nOpening DTS SSO...");
			}

			WebDriver sso = new FirefoxDriver();
			sso.manage().window().maximize();
			sso.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			System.out.println("\nOpening...http://58.68.10.76:8082/SSO/login");
			sso.get("http://58.68.10.76:8082/SSO/login");
			Thread.sleep(3000);
			
			//------------------------------SSO_ST001-(Login into DTS SSO)------------------------------------------------
			/*
			String username="RDas";
			sso.findElement(By.id("username")).sendKeys(username);
			Thread.sleep(1000);
			sso.findElement(By.id("password")).sendKeys("Daemon@76");
			Thread.sleep(1000);
			sso.findElement(By.cssSelector(".imageButton")).click();
			Thread.sleep(2000);
			System.out.println("\nLogged in to DTS SSO, from User_id: "+username);
			*/
			//------------------------------SSO_ST002-(Reset By Forgot Password)------------------------------------------
			/*			
			String username="test1";
			sso.findElement(By.cssSelector("#login > a:nth-child(5)")).click();
			Thread.sleep(2000);
			System.out.println("\nChanging the password, through 'Forgot password.'");
			WebElement resetpassword = sso.findElement(By.id("reset_password_content"));
			sso.switchTo().frame(resetpassword);
			sso.findElement(By.id("username")).sendKeys(username);
			Thread.sleep(1000);
			sso.findElement(By.cssSelector("#userform > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > input:nth-child(1)")).click();
			System.out.println("\nChanging the password of User_id: "+username);
			Thread.sleep(1000);
			System.out.println("Pasword Change initiated, A High Security key has been sent to your Email ID");
			System.out.println("Enter the High Security key & Click on Submit");
			//sso.findElement(By.cssSelector("#highSecurity > input:nth-child(2)")).click();
			//sso.findElement(By.id("reset_password_close")).click();
			sso.switchTo().parentFrame();
			*/
			//------------------------------SSO_ST008-(Adding Photo For A User)-------------------------------------------
/*				
			String username="RDas";
			sso.findElement(By.id("username")).sendKeys(username);
			Thread.sleep(1000);
			sso.findElement(By.id("password")).sendKeys("Daemon@76");
			Thread.sleep(1000);
			sso.findElement(By.cssSelector(".imageButton")).click();
			Thread.sleep(2000);
			System.out.println("\nLogged in to DTS SSO, from User_id: "+username);
			
			sso.findElement(By.id("userProfilePhoto")).click();
			Thread.sleep(2000);
			System.out.println("\nUploading Photo.");
			//WebElement userAccessDialogBox = sso.findElement(By.id("userAccessDialogBox_content"));
			WebElement userAccessDialogBox = sso.findElement(By.xpath("//iframe[contains(@id,'userAccessDialogBox_content')]"));
			sso.switchTo().frame(userAccessDialogBox);
			Thread.sleep(2000);
			sso.findElement(By.id("profilePhoto")).click();
			Thread.sleep(1000);
			sso.findElement(By.id("editImage")).click();
			Thread.sleep(2000);			
			System.out.println("Selecting the photo to upload");
			
			StringSelection file1 = new StringSelection("D:\\DTS_Workspace\\DTS\\src\\test\\resources\\Photos\\B.png");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file1, null);
			
            StringSelection filepath = new StringSelection("D:\\DTS_Workspace\\DTS\\src\\test\\resources\\Photos\\B.png");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath, null);
			
			
			WebElement element= sso.findElement(By.id("editImage"));
			element.sendKeys("D:\\DTS_Workspace\\DTS\\src\\test\\resources\\Photos\\B.png");
			
			sso.switchTo().activeElement().sendKeys("D:\\DTS_Workspace\\DTS\\src\\test\\resources\\Photos\\B.png");
			sso.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
		
			
			StringSelection filepath = new StringSelection("D:\\DTS_Workspace\\DTS\\src\\test\\resources\\Photos\\B.png");
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath, null);
			System.out.println("Photo Selected");
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
			sso.findElement(By.id("userAccessDialogBox_close")).click();
			sso.switchTo().parentFrame();
			System.out.println("Photo Updated");
*/
			//------------------------------SSO_ST009-(Verify user access details)----------------------------------------
			
/*			String username="RDas";
			sso.findElement(By.id("username")).sendKeys(username);
			Thread.sleep(1000);
			sso.findElement(By.id("password")).sendKeys("Daemon@76");
			Thread.sleep(1000);
			sso.findElement(By.cssSelector(".imageButton")).click();
			Thread.sleep(2000);
			System.out.println("\nLogged in to DTS SSO, from User_id: "+username);
			Thread.sleep(3000);
			sso.findElement(By.cssSelector("a.account:nth-child(2) > img:nth-child(1)")).click();
			Thread.sleep(1000);
			sso.findElement(By.cssSelector(".root > li:nth-child(1) > a:nth-child(1)")).click();
			System.out.println("\nVerify user access details");
*/



			//       ---------         overlay_modal     -----
			//
			//
			//         --------- Reset password   			sso.findElement(By.cssSelector("#container > a:nth-child(3)")).click();

			//reset_password_close
			
			//------------------------------SSO_ST010-(Reset Password)----------------------------------------------------
			
			String username="RDas";
			String oldPassword="Daemon@76";
			String newPassword="Daemon@76";
			
			
			sso.findElement(By.id("username")).sendKeys(username);
			Thread.sleep(1000);
			sso.findElement(By.id("password")).sendKeys(oldPassword);
			Thread.sleep(1000);
			sso.findElement(By.cssSelector(".imageButton")).click();
			Thread.sleep(2000);
			System.out.println("\nLogged in to DTS SSO, from User_id: "+username);
			Thread.sleep(3000);
			sso.findElement(By.cssSelector("a.account:nth-child(2) > img:nth-child(1)")).click();
			Thread.sleep(1000);
			sso.findElement(By.cssSelector(".root > li:nth-child(1) > a:nth-child(1)")).click();
			System.out.println("\nVerify user access details");
			Thread.sleep(3000);
			WebElement ResetPasswordContent = sso.findElement(By.xpath("//iframe[contains(@id,'reset_password_content')]"));
			sso.switchTo().frame(ResetPasswordContent);
			sso.findElement(By.cssSelector("#container > a:nth-child(3)")).click();
			System.out.println("Reset Password Content Opened");
			//IFrame: reset_password_content
			sso.findElement(By.id("oldPassword")).sendKeys(oldPassword);
			sso.findElement(By.id("newPassword")).sendKeys(newPassword);
			sso.findElement(By.id("confirmPassword")).sendKeys(newPassword);
			//sso.findElement(By.cssSelector("#container > input:nth-child(9)")).click();
			Thread.sleep(2000);
			sso.switchTo().parentFrame();

			System.out.println("\nReset Password Done.");
			
			
			
			//------------------------------SSO_ST011- (Logging Out From SSO)---------------------------------------------
			/*
			System.out.println("\nLogging Out from "+username+" user.");
			sso.findElement(By.cssSelector(".account > b:nth-child(2)")).click();
			Thread.sleep(1000);
			sso.findElement(By.cssSelector(".loginDropdown > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1) > span:nth-child(2)")).click();
			Thread.sleep(3000);
			sso.findElement(By.cssSelector("button.btn:nth-child(1)")).click();
			System.out.println("\nLogged Out from "+username+" user");
			Thread.sleep(2000);
			*/
			
	
		}
	}
