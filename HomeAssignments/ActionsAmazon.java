package week4.day2.HomeAssignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsAmazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		/*Precondition: 
 			- Initialize ChromeDriver 
 			- Load the URL (https://www.amazon.in/) - Maximize the browser window 
 			- Add an implicit wait to ensure the webpage elements are fully loaded
 		*/
		
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(20000));
				
		//Search for "oneplus 9 pro". 
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-text")).click();
		
		//Get the price of the first product. 
		
		String price = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		System.out.println("Price of the first product: Rs."+price);
		
		// Print the number of customer ratings for the first displayed product. 
		
		WebElement ratingWE = driver.findElement(By.xpath("(//i[@data-cy='reviews-ratings-slot'])[1]"));
		Actions action = new Actions(driver);
		action.click(ratingWE).perform();
		String rating = driver.findElement(By.id("acr-popover-title")).getText();
		System.out.println("Rating for the first product:"+rating);
		
		// Click the first text link of the first image. 
		
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).click();
		Set<String> windows = driver.getWindowHandles();
		List<String> listOfWindows = new ArrayList<String>(windows);
		driver.switchTo().window(listOfWindows.get(1));
				
		//Take a screenshot of the product displayed. 
		
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/AmazonProd.png");
		FileUtils.copyFile(src, dest);
				
		//Click the 'Add to Cart' button. 
		
		WebElement element = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		driver.executeScript("arguments[0].click()", element);
		
		// Get the cart subtotal and verify if it is correct.
		
		WebElement cartWE = driver.findElement(By.xpath("//div[@class='a-row a-spacing-none']/descendant::span[@class='a-size-base-plus a-color-price a-text-bold']"));
		String subTotal = cartWE.getText();
		System.out.println("Cart SubTotal:"+subTotal);
		
		price = price.replaceAll("[^0-9]", "");
		subTotal = subTotal.replaceAll(".00", ""); 
		subTotal = subTotal.replaceAll("[^0-9]", "");
				
		if(price.equals(subTotal))
			System.out.println("Verification successful!");
		else
			System.out.println("Check the Cart!");
		
		//Close the browser. 
		
		driver.quit();
				
	}

}
