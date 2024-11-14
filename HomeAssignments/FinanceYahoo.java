package week4.day2.HomeAssignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FinanceYahoo {

	public static void main(String[] args) {
		
		// Load the url - https://finance.yahoo.com/
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://finance.yahoo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		
		//Click on “Crypto”  tab
		
		driver.findElement(By.xpath("//span[text()='More']")).click();
		driver.findElement(By.xpath("//a[@aria-label='Finance: Crypto']")).click();
		
		//Locate the table in the dom page
		
		driver.findElement(By.xpath("//span[text()='Table View']")).click();
		String tableSelected = driver.findElement(By.xpath("//div[text()='Table View']")).getAttribute("aria-selected");
		if(tableSelected.equals("true"))
			System.out.println("Table View is selected");
		else
			System.out.println("Table view is not selected");
		
		//Locate the cryptocurrency names in the table using specific row and column
		
		List<WebElement> cryptoNamesWE = driver.findElements(By.xpath("//div[contains (@class,'tableContainer')]/div/table/tbody/tr/td[2]"));
		System.out.println("Total Cryptocurrency names in this webpage:"+cryptoNamesWE.size());
		
		//Print the cryptocurrency names
				
		for(WebElement cryptoName : cryptoNamesWE)
		{
			System.out.println(cryptoName.getText());
		}
		
		
	}

}
