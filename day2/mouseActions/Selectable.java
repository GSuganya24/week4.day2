package week4.day2.mouseActions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selectable {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/selectable");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.switchTo().frame(0);
		//Creating Actions Object 
		Actions builder = new Actions(driver);
		WebElement item1 = driver.findElement(By.xpath("//li[text() = 'Item 1']"));
		WebElement item3 = driver.findElement(By.xpath("//li[text() = 'Item 3']"));
		WebElement item5 = driver.findElement(By.xpath("//li[text() = 'Item 5']"));
		WebElement item7 = driver.findElement(By.xpath("//li[text() = 'Item 7']"));
		builder.keyDown(Keys.CONTROL)
		.click(item1)
		.click(item3)
		.click(item5)
		.click(item7)
		.perform();
		driver.close();
	}
}
