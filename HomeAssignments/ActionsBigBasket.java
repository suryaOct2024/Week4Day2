package week4.day2.HomeAssignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsBigBasket {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		/* Precondition: 
		 * - Initialize ChromeDriver - Load the URL (https://www.bigbasket.com/)  
		 * - Maximize the browser window 
		 * - Add an implicit wait to ensure the webpage elements are fully loaded
		 */
		
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		
		//Click on "Shop by Category"
		
		driver.findElement(By.xpath("(//span[text()='Shop by'])[2]")).click();
		
		// Mouse over "Foodgrains, Oil & Masala". 
		
		Thread.sleep(1000);
		WebElement category = driver.findElement(By.xpath("(//a[@href='/cl/foodgrains-oil-masala/?nc=nb'])[2]"));
		Actions action = new Actions(driver);
		action.moveToElement(category).perform();
		
		//Mouse over "Rice & Rice Products". 
		
		WebElement subCategory = driver.findElement(By.linkText("Rice & Rice Products"));
		action.moveToElement(subCategory).perform();
		
		// Click on "Boiled & Steam Rice"
		
		WebElement supersubCategory = driver.findElement(By.linkText("Boiled & Steam Rice"));
		action.moveToElement(supersubCategory).click().perform();
		
		// Filter the results by selecting the brand "bb Royal". 
		
		Thread.sleep(3000);
		WebElement bbroyalWE=driver.findElement(By.xpath("//label[text()='BB Royal']"));
		driver.executeScript("arguments[0].click()", bbroyalWE);
				
		//Click on "Tamil Ponni Boiled Rice". 
		
		Thread.sleep(2000);
		WebElement scrollProd = driver.findElement(By.xpath("//h3[text()='Tamil Ponni Boiled Rice']"));
		action.scrollToElement(scrollProd).click(scrollProd).perform();
		
		//Select the 5 Kg bag. 
		
		Thread.sleep(2000);
		Set<String> windows = driver.getWindowHandles();
		List<String> listOfWindows = new ArrayList<String>(windows);
		driver.switchTo().window(listOfWindows.get(1));
		WebElement riceSize = driver.findElement(By.xpath("//span[text()='5 kg']"));
		
		driver.executeScript("arguments[0].click()", riceSize);
							
		// Check and note the price of the rice. 
		
		WebElement priceWE = driver.findElement(By.xpath("(//span[@class='Label-sc-15v1nk5-0 PackSizeSelector___StyledLabel5-sc-l9rhbt-5 gJxZPQ bvikaK'])[5]"));		
		driver.executeScript("arguments[0]", priceWE);
		String price = priceWE.getText();
		System.out.println("Price of the Rice:"+price);
		
		//Click "Add" to add the bag to your cart. 
		
		driver.findElement(By.xpath("(//button[text()='Add to basket'])[1]")).click();
				
		// Verify the success message that confirms the item was added to your cart. 
		
		Thread.sleep(2000);
		String expectedMsg = "An item has been added to your basket successfully";
		String msg = driver.findElement(By.xpath("//div[@class='self-center']/following::p")).getText();
		System.out.println("Displayed msg:"+msg);
		
		if(msg.equals(expectedMsg))
				System.out.println("Item Successfully Added to the cart");
		else
			System.out.println("Item Not Added to the cart");
		
		//Take a snapshot of the current page 
		
		File snapshotSrc = driver.getScreenshotAs(OutputType.FILE);
		File snapshotDest = new File("./snaps/bigBasketprod.png");
		FileUtils.copyFile(snapshotSrc, snapshotDest);
		
		//Close the current window. 
		driver.close();
		
		//Close the main window. 
		driver.switchTo().window(listOfWindows.get(0));
		driver.close();
	}

}
