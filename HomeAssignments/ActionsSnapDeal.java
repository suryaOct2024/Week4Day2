package week4.day2.HomeAssignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsSnapDeal {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		/* Precondition: 
 			- Initialize ChromeDriver 
 			- Load the URL (https://www.snapdeal.com/) 
 			- Maximize the browser window 
 			- Add an implicit wait to ensure the webpage elements are fully loaded 
		*/
				
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		
		// Go to "Men's Fashion". 
		
		WebElement menFashionWE = driver.findElement(By.xpath("(//span[contains(text(),'Men')])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(menFashionWE).perform();
		
		// Go to "Sports Shoes".
		
		driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]")).click();
		
		//Get the count of sports shoes. 
		
		String shoeCount = driver.findElement(By.xpath("//h1[@category='Sports Shoes for Men']/following::span[1]")).getText();
		System.out.println("Men's Sports Shoes Count:"+shoeCount);
		
		// Click on "Training Shoes". 
		
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
		//Sort the products by "Low to High". 
		
		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		driver.findElement(By.xpath("//div[@class='sorting-sec animBounce']/ul/li[@data-index='1']")).click();
						
		//Check if the displayed items are sorted correctly. 
		
		// Select any price range ex:(500-700).
		
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("500");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("700");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
		
		// Filter by any color
		
		Thread.sleep(2000);
		WebElement colorFilterWE = driver.findElement(By.xpath("(//a[@class='filter-name'])[5]"));
		Actions filterAction = new Actions(driver);
		filterAction.scrollToElement(colorFilterWE).click(colorFilterWE).perform();
		
		//Verify all the applied filters. 
		
		String price = driver.findElement(By.xpath("(//a[@data-key='Price|Price'])[1]")).getText();
		if(price.equals("Rs. 500 - Rs. 700"))
			System.out.println("Price - filter applied:"+price);
		else
			System.out.println("Price - filter not applied");
		
		Thread.sleep(2000);
		String color = driver.findElement(By.xpath("(//a[@data-key='Color_s|Color'])[1]")).getText();
		if(color.equals("White & Blue"))
			System.out.println("Color - filter applied:"+color);
		else
			System.out.println("Color - filter not applied");
		
		String sort = driver.findElement(By.xpath("//div[@class='sort-selected']")).getText();
		if(sort.equals("Price Low To High"))
			System.out.println("Sort By - filter applied:"+sort);
		else
			System.out.println("Sort By - filter not applied");
		
		//Mouse hover on the first resulting "Training Shoes". 
		
		WebElement shoesWE = driver.findElement(By.xpath("(//img[@class='product-image wooble'])[1]"));
		Actions shoeAction = new Actions(driver);
		shoeAction.moveToElement(shoesWE).perform();
		
		// Click the "Quick View" button. 
		
		driver.findElement(By.xpath("(//div[contains(text(),'Quick View')])[1]")).click();
		
		//Print the cost and the discount percentage.
				
		String priceStr = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("Price:"+priceStr);
		String discount = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Discount:"+discount);
		
		//Take a snapshot of the shoes. 
		
		Thread.sleep(2000);
		File snapshotSrc = driver.getScreenshotAs(OutputType.FILE);
		File snapshotDest = new File("./snaps/snapDealShoes.png");
		FileUtils.copyFile(snapshotSrc, snapshotDest);
		
		//Close the current window.  
		
		driver.findElement(By.xpath("//div[@class='close close1 marR10']/i[@class='sd-icon sd-icon-delete-sign']")).click();
		
		//Close the main window.
		
		driver.close();
		
	}

}
