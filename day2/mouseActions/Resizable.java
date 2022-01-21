package week4.day2.mouseActions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resizable {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/resizable");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.switchTo().frame(0);
		//Creating Actions Object 
		Actions builder = new Actions(driver);
		WebElement icon = driver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
		builder.clickAndHold(icon)
		.moveByOffset(100, 50)
		.perform();
		driver.close();
	}
}
