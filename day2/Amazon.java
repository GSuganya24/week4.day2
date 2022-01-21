package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
public static void main(String[] args) throws InterruptedException, IOException {
	WebDriverManager.chromedriver().setup();
	// Closing the popups
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			ChromeDriver driver = new ChromeDriver(options);
			driver.get("https://www.amazon.in/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			//Searching for 'oneplus 9 pro'
			WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
			search.sendKeys("oneplus 9 pro");
			search.sendKeys(Keys.ENTER);
			//Getting the price of the first element
			Thread.sleep(2000);
			//Get the price of the first displayed product
			WebElement getPrice = driver.findElement(By.xpath("//span[@class='a-price-whole']"));
			String gPrice = getPrice.getText();
			System.out.println("Price of the first element : " +gPrice);
			//Converting to Integer
			String price1 = gPrice.replaceAll("[^0-9]", "");
			int newSubTotal1 = Integer.parseInt(price1);
			System.out.println(newSubTotal1);
			String rating = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-none a-spacing-top-micro'])[5]//span[2]//span")).getText();
			System.out.println("Customer rating for the prodcut is : "+rating);
			driver.findElement(By.xpath("(//div[@class='a-section']/div/div[2]/div//div[2]//span//a)[5]/i")).click();
			//5-star rating percentage
			String starReview = driver.findElement(By.xpath("//table[@id='histogramTable']//tr/td[3]//a")).getText();
			System.out.println("The percentage of '5-star' rating is "+starReview);
			driver.findElement(By.xpath("(//div[@class='sg-col-inner']//h2/a)[3]")).click();
			Thread.sleep(1000);
			Set<String> windowHandles = driver.getWindowHandles();
			List<String> window = new ArrayList<String>(windowHandles);
			driver.switchTo().window(window.get(1));
			//Take the Screenshot of the product
			File source = driver.getScreenshotAs(OutputType.FILE);
			File destination = new File("./images/SortByLowToHigh.png");
			FileUtils.copyFile(source, destination); 
			driver.findElement(By.id("add-to-cart-button")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("attach-view-cart-button-form")).click();
			Thread.sleep(1000);
			WebElement subTotal = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']/span"));
			String stotal = subTotal.getText();
//			String subTotal = driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
			System.out.println("Subtotal : "+stotal);
			String price2 = stotal.replace(".00","").replaceAll("[^0-9]", "");
			int newSubTotal2 = Integer.parseInt(price2);
			System.out.println(newSubTotal2);
			if(newSubTotal1 == newSubTotal2) {
				System.out.println("The Subtotal Value matches");
			}else {
				System.out.println("The Subtotal value mismatched");
			}
			
}
}
