package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErailUnique {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://erail.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Enter From Station
		WebElement stn1 = driver.findElement(By.xpath("//input[@id = 'txtStationFrom']"));
		stn1.clear();
		stn1.sendKeys("MS");
		Thread.sleep(500);
		stn1.sendKeys(Keys.TAB);
		//driver.findElement(By.xpath("//div[text()='Chennai Egmore']")).click();
		//Enter To Station
		WebElement stn2 = driver.findElement(By.xpath("//input[@id = 'txtStationTo']"));
		stn2.clear();
	    stn2.sendKeys("MDU");
	    stn2.sendKeys(Keys.TAB);
	    //driver.findElement(By.xpath("//div[text()='Madurai Jn']")).click();
	    //Uncheck the sort on date
	    driver.findElement(By.xpath("//td[@id = 'tdDateOnly']/input")).click();
	    //Getting the number of rows
	    int rowcount = driver.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr")).size();
	    System.out.println("Length : "+rowcount);
	    //Storing the train names in a list
	    List<String> trainNames = new ArrayList<String>();
	    for(int i=2;i<rowcount;i++) {
	    	String trainName = driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader']//tr["+ i +"]/td[2]")).getText();
	    			trainNames.add(trainName);	
	    }
	    Set<String> trains = new HashSet<String>(trainNames);
	    int listSize = trainNames.size();
	    System.out.println("Size of the list : "+listSize);
	    int setSize = trains.size();
	    System.out.println("Size of the set : "+setSize);
		if(listSize == setSize) {
			System.out.println("No Duplicates");
		}else {
			System.out.println("Found Duplicates");
		}
		driver.close();
	}
}
