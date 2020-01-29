package ocrdemo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Readimages {

	public static void main(String[] args) throws IOException, TesseractException, InterruptedException {
		ChromeOptions op=new ChromeOptions();
		op.addArguments("--disable-notifications");
		WebDriver driver=new ChromeDriver(op);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@id,'loginText')]"))).click();
		//driver.findElement(By.xpath(xpathExpression)("//a[contains(@id,'loginText')]")).click();
		Thread.sleep(5000);
//		WebDriverWait wait1=new WebDriverWait(driver, 300);
//		File src = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("captchaImg"))).getScreenshotAs(OutputType.FILE);
		File from = driver.findElement(By.id("captchaImg")).getScreenshotAs(OutputType.FILE);
		//String path = System.getProperty("user.dir")+"/photo/captcha.png";
		String path = System.getProperty("user.dir")+"/photo/captcha.png";
		File to=new File(path);
		FileHandler.copy(from, to);
		ITesseract image=new Tesseract();//For this we need to add tess4j maven repasitory(pom.xml ) and  tess data in english as well 
		String imagetext = image.doOCR(new File(path));
		System.out.println(imagetext);
		//driver.close();
		//Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		
	}

}
