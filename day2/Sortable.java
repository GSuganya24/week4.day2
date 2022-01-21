package week4.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sortable {
public static void main(String[] args) {
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	ChromeDriver driver = new ChromeDriver();
	driver.get("http://www.leafground.com/pages/sorttable.html");
	driver.manage().window().maximize();
	//Get all the names in the table and store it in the list
	int rowsCount = driver.findElements(By.xpath("//table[@id='table_id']//tbody/tr")).size();
	System.out.println(rowsCount);
	//Storing the names in a list
	List<String> names1 = new ArrayList<String>();
	for(int i = 1;i<=rowsCount;i++) {
		String name1 = driver.findElement(By.xpath("//table[@id='table_id']//tr["+ i +"]/td[2]")).getText();
		names1.add(name1);
	}
	System.out.println(names1);
	//Sorting the names stored in a list
	Collections.sort(names1);
	System.out.println(names1);
	driver.findElement(By.xpath("//table[@id='table_id']/thead/tr/th[2]")).click();
	//Getting again after sorting on the page
	List<String> names2 = new ArrayList<String>();
	for(int j = 1;j<=rowsCount;j++) {
		String name2 = driver.findElement(By.xpath("//table[@id='table_id']//tr["+ j +"]/td[2]")).getText();
		names2.add(name2);
	}
	System.out.println(names2);
	//Check both the lists are equal
	if(names1.equals(names2)) {
		System.out.println("Both the lists are sorted equally");
	}else {
		System.out.println("Lists are not sorted properly");
	}
}
}
