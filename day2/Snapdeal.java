package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		// Closing the popups
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Actions builder = new Actions(driver);
		// MouseHover to Men's Fashion
		WebElement men = driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]"));
		builder.moveToElement(men).perform();
		driver.findElement(By.xpath("//span[text()=\"Sports Shoes\"]")).click();
		Thread.sleep(1000);
		// Get the count of the spotrs shoes
		String count = driver.findElement(By.xpath("//ul[@id='js-46105686-nav']//a/div[2]")).getText();
		System.out.println("Number of Sports shoes for Men : " + count);
		// Click Training Shoes
		driver.findElement(By.xpath("//ul[@id='js-255-nav']//a/div")).click();
		// Select Price Low To High from the dropdown
		driver.findElement(By.xpath("//span[text()='Sort by:']")).click();
		driver.findElement(By.xpath("//ul[@class='sort-value']/li[2]")).click();
		// Checking the items sorted correctly by taking screenshot
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/SortByLowToHigh.png");
		FileUtils.copyFile(source, destination);
		WebElement fromVal = driver.findElement(By.name("fromVal"));
		fromVal.clear();
		fromVal.sendKeys("900");
		WebElement toVal = driver.findElement(By.name("toVal"));
		toVal.clear();
		toVal.sendKeys("1200");
		driver.findElement(By.xpath("//div[starts-with(normalize-space(text()),'GO')]")).click();
		Thread.sleep(2000);
		// Selecting Color Navy
		driver.findElement(By.xpath("//div/button[text()='View More ']")).click();
		driver.findElement(By.xpath("//input[@id='Color_s-Navy']/following-sibling::label")).click();
		Thread.sleep(500);
		// Verifying the all applied filters
		String priceFilter = driver.findElement(By.xpath("//div[text()='Price: ']/a")).getText();
		String colorFilter = driver.findElement(By.xpath("//div[text()='Color: ']/a")).getText();
		if ((priceFilter.contains("1200")) && (colorFilter.contains("Navy"))) {
			System.out.println("The filters are applied");
		} else {
			System.out.println("The filters are missing");
		}
		Thread.sleep(2000);
		// Clicking QuickView of the first resulting product
		WebElement firstEle = driver.findElement(By.xpath("//div[contains(@class,'product-tuple-image')]"));
		builder.moveToElement(firstEle).perform();// MouseHover to the first element so that the QuickView Appears
		driver.findElement(By.xpath(
				"//div[contains(@class,'product-tuple-image')]/div/div[starts-with(normalize-space(text()),'Quick View')]"))
				.click();
		// Print the price and the discount percentage
		String price = driver.findElement(By.xpath("//div[@class='lfloat']/div[2]/span")).getText();
		String discount = driver.findElement(By.xpath("//div[@class='lfloat']/div[2]/span[2]")).getText();
		System.out.println("The price of the product is : " + price);
		System.out.println("The discount of the product is : " + discount);
		// Take the Snapshot
		File source1 = driver.getScreenshotAs(OutputType.FILE);
		File destination1 = new File("./images/QuickView.png");
		FileUtils.copyFile(source1, destination1);
		driver.findElement(By.xpath("//div[@id='sidebar_modal_right']/div[2]//i")).click();
		driver.close();
	}
}
