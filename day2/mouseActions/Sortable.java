package week4.day2.mouseActions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sortable {
public static void main(String[] args) {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://jqueryui.com/sortable");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.switchTo().frame(0);
	//Creating Actions Object 
		Actions builder = new Actions(driver);
	WebElement sortItem5 = driver.findElement(By.xpath("//li[text() = 'Item 5']"));
	WebElement sortItem1 = driver.findElement(By.xpath("//li[text() = 'Item 1']"));
	builder.clickAndHold(sortItem5)
	.dragAndDrop(sortItem5, sortItem1)
	.release()
	.perform();
	driver.close();
}
}
