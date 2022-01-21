package week4.day2.mouseActions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Droppable {
public static void main(String[] args) {
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://jqueryui.com/droppable");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.switchTo().frame(0);
//	Creating Actions Object 
	Actions builder = new Actions(driver);
	WebElement drag1 = driver.findElement(By.id("draggable"));
	WebElement drop1 = driver.findElement(By.id("droppable"));
	builder.dragAndDrop(drag1, drop1).perform();
	driver.close();
}
}
