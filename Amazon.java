/* Phase 1 - Session 12 - project 
 * 
 * Create a new Java project from Screatch
 * Add all Selenium Lib
 * Add chrome driver to the project
 * You need to launch AMazon.in
 * Search the product SAmsung and click on go button
 * Find all product search results and their prices and print the console
 * Go inside the first product by clicking the link - product will open in the new tab(get window handler).
 * Validate the product name clicked/opened
 * 
 *  
 * 
 * */

package project;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("Samsung");

		WebElement go = driver.findElement(By.id("nav-search-submit-button"));
		go.click();

		List<WebElement> products = driver
				.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		System.out.println("The listed products are" + products.size());

		List<WebElement> prices = driver
				.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

		
		for(int i = 0; i<products.size();i++) {
			System.out.println(products.get(i).getText()+"  "+prices.get(i).getText());
		}
		
		String selectedproduct = products.get(0).getText();

		products.get(0).click();

		String ParentWH = driver.getWindowHandle();

		Set<String> AllwindowHandler = driver.getWindowHandles();
		for (String win : AllwindowHandler) {
			System.out.println(win);

			if (!win.equals(ParentWH)) {
				driver.switchTo().window(win);

			}
		}

		WebElement product = driver.findElement(By.xpath("//span[@id='productTitle']"));

		String actualproduct = product.getText();

		if (actualproduct.equals(selectedproduct)) {
			System.out.println("Validation passed");
		} else {
			System.out.println("Validation failed");
		}

		driver.quit();

	}

}
