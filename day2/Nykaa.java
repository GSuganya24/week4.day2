package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		//Closing the popups
				ChromeOptions options = new ChromeOptions(); 
				options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Actions builder = new Actions(driver);
		//MouseHover to Brands
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		builder.moveToElement(brand).perform();
		driver.findElement(By.xpath("//div/a[text()=\"L'Oreal Paris\"]")).click();
		String title = driver.getTitle();
		System.out.println(title);
		//Checking the page title
		if(title.contains("L'Oreal Paris")) {
			System.out.println("We moved to the right page");
		}else {
			System.out.println("We moved to the wrong page");
		}
		driver.findElement(By.xpath("//span[text()='Sort By : popularity']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		WebElement check = driver.findElement(By.xpath("//input[@id='checkbox_Shampoo_316']/following-sibling::label/div[2]"));
		boolean checked = check.isSelected();
		if(!checked) {
			check.click();
		}else {
			System.out.println("already checked");
		}
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='checkbox_Color Protection_10764']/following-sibling::label/div[2]")).click();
		String text = driver.findElement(By.xpath("//div[@id='filters-listing']//div[2]//span")).getText();
		if(text.equalsIgnoreCase("Shampoo")) {
			System.out.println("Filter applied with Shampoo");
		}else {
			System.out.println("Filter is not applied with shampoo");
		}
		driver.findElement(By.xpath("//div[@class='css-wkluxr']/following-sibling::a")).click();
		//moving to the opened window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window1 = new ArrayList<String>(windowHandles);
		driver.switchTo().window(window1.get(1));
		//Select 175ml from the dropdown
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@class='css-1c1c89s']")));
		dropdown.selectByVisibleText("175ml");
		//Print the MRP
		String mrp = driver.findElement(By.xpath("//span[@class='css-12x6n3h']")).getText();
		System.out.println("MRP of the product is : " +mrp);
		//Click on Add to Bag
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		driver.findElement(By.xpath("//button[@class='css-5s18zx eoh7kvv0']")).click();
		Thread.sleep(2000);
		//Its inside a frame
		driver.switchTo().frame(0);
		String total = driver.findElement(By.xpath("//span[text()='Grand Total']/following-sibling::div")).getText();
		System.out.println("GrandTotal of the cart is : " +total);
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		String grandTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div/span")).getText();
		System.out.println("GrandTotal on the payment page : " +grandTotal);
		if(total.contains(grandTotal)) {
			System.out.println("Both the values are equal");
		}else {
			System.out.println("Both the value are different");
		}
		driver.close();
		
	}


}
