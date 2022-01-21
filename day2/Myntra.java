package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		// Closing the popups
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// MouseHover to Men
		Actions build = new Actions(driver);
		WebElement men = driver.findElement(By.xpath("//a[text()='Men']"));
		build.moveToElement(men).perform();
		driver.findElement(By.linkText("Jackets")).click();
		String count = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		System.out.println("The total number of Jackets are : " + count);
		String replace = count.replaceAll("[^0-9]", "");
		int totalCount = Integer.parseInt(replace);
		System.out.println(totalCount);
//	String count1 = driver.findElement(By.xpath("//ul[@class='categories-list']//label//span")).getText();
		int length = driver
				.findElements(By.xpath("//ul[@class='categories-list']//label/input/following-sibling::span")).size();
		System.out.println("Number of Categories : " +length);
		int totalJacCount = 0;
		// List<Integer> categories = new ArrayList<Integer>();
		for (int i = 1; i <= length; i++) {
			String categoriesName = driver
					.findElement(By
							.xpath("(//ul[@class='categories-list']//label/input/following-sibling::span)[" + i + "]"))
					.getText();
			String replace1 = categoriesName.replaceAll("[^0-9]", "");
			int totalJacCount1 = Integer.parseInt(replace1);
			totalJacCount = totalJacCount + totalJacCount1;
		}
		System.out.println(totalJacCount);
		if (totalJacCount == totalCount) {
			System.out.println("The count matches");
		} else {
			System.out.println("Sometimes the count mismatches, not an error");
		}
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		driver.findElement(By.xpath("//div[@class='FilterDirectory-titleBar']/input")).sendKeys("Duke");
		WebElement duke = driver.findElement(By.xpath("//ul[@class='FilterDirectory-list']//label/input"));
		String text = duke.getAttribute("value");
		System.out.println(text);
		if (text.equals("Duke")) {
			driver.findElement(By.xpath("//ul[@class='FilterDirectory-list']//label/div")).click();
		} else {
			System.out.println("Item not found");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='FilterDirectory-titleBar']/ul/following-sibling::span")).click();
		int total = driver.findElements(By.xpath("//div[@class='product-productMetaInfo']/h3")).size();
		int check = 0;
		List<String> jacketBrand = new ArrayList<String>();
		for (int j = 1; j <= total; j++) {
			String jackets = driver.findElement(By.xpath("((//div[@class='product-productMetaInfo']/h3))[" + j + "]"))
					.getText();
			jacketBrand.add(jackets);
			int k = j - 1;
			if (jacketBrand.get(k).equalsIgnoreCase("Duke")) {
				check = check + 1;
			} else {
				System.out.println("Brand Mismatched");
				break;
			}
		}
		// Checking count to make sure the jackets selected are the same brand Duke
		if (check == total) {
			System.out.println("The Jackets filtered are from the same brand 'Duke'");
		} else {
			System.out.println("Other brand Jackets are also available");
		}
		// Sorting By Better discount
		WebElement sort = driver.findElement(By.xpath("//div[@class='sort-sortBy']/span[2]"));
		build.moveToElement(sort).perform();
        driver.findElement(By.xpath("//div[@class='sort-sortBy']//li[3]")).click();
        Thread.sleep(1000);
		// Printing the price of the first displayed item
		String discountedPrice = driver.findElement(By.xpath("//div[@class='product-price']//span/span")).getText();
		System.out.println(discountedPrice);
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@class='product-productMetaInfo']")).click();
		Set<String> windowHandle = driver.getWindowHandles();
		List<String> window1 = new ArrayList<String>(windowHandle);
		driver.switchTo().window(window1.get(1));
		// Taking the screenshot of the page
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/SortByLowToHigh.png");
		FileUtils.copyFile(source, destination);
		//Click to add in wishlist
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		driver.close();
		driver.switchTo().window(window1.get(0));
		driver.close();
	}
}
