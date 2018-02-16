package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class SearchWiki {
	
	public static void main(String[] args) throws InterruptedException  {
			
			//Setup Webdriver
			System.setProperty("webdriver.chrome.driver", "C:\\DRIVERS\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();    
			options.addArguments("disable-infobars");     //disables warning message on browser
			WebDriver driver = new ChromeDriver(options);
			
			// Initialise Strings for searching
			String firstURL = "https://www.google.com.au/";
			String secondURL = "https://www.wikipedia.org/";
			String searchItem = "ResMed";
			String text = "History";
			String firstElement = "sleep-disordered breathing";
			System.out.println("Strings Declared");
			
			//Call methods to execute tests
			boolean test1 = testBrowser(driver, firstURL);
			boolean test2 = testBrowser(driver, secondURL);
			boolean test3 = testTitleSearch(driver, searchItem);
			boolean test4 = testElement(driver, firstElement);
			boolean test5 = testGetText(driver, text);
			
			Thread.sleep(3000);
			driver.quit();
			
			
			//print results
			printResult(test1, test2, test3, test4, test5);
			
			

			}
			
	
	
	//This method will open a browser and go to the given url
	public static boolean testBrowser(WebDriver driver, String url) throws InterruptedException {
		System.out.println("Test Browser Called");
		System.out.println(url);
		driver.get(url);
		Thread.sleep(1000);
		String Title = driver.getCurrentUrl();
		String siteAbbrev;
		Thread.sleep(1000);
		Title = Title.split("\\?")[0];
		siteAbbrev = Title.split("\\.")[1];
		if (Title.equals(url)) {
			System.out.println("Website successfully opened for " + siteAbbrev);
			return true;
		}
		else {
			System.out.println("Error opening website");
			return false;
			
		}
		

}
	
	//This method will search using a searchbox on the website
	public static boolean testTitleSearch(WebDriver driver, String searchItem) throws InterruptedException {
			Thread.sleep(1000);
			WebElement searchBox;
			System.out.println(searchItem);
			searchBox = driver.findElement(By.id("search-form"));
			EnterSearch(driver, searchBox, searchItem);
			searchBox.submit();
			Thread.sleep(1000);

			String answer = driver.getTitle();
			System.out.println(answer);
			
			if (answer.equals(searchItem + " - Wikipedia")) {
				System.out.println("Search successful for:" +searchItem );
				return true;
	
			}
			else {
				System.out.println("Title Search failed");
				return false;

			}

	
	}
	
	//This method will get the title of the current page
	public static boolean testElement(WebDriver driver, String Element) throws InterruptedException {
		System.out.println("Inside TestElement");

		WebElement element = driver.findElement(By.linkText(Element));
	
		Thread.sleep(1000);
		element.click();
		
		String Title = driver.getTitle();
		

		if (Title.equals("Sleep and breathing - Wikipedia")) {
			System.out.println("Search Successful");
			return true;
		}
		else {
			System.out.println("Search failed");
			System.out.println("Expected result:" + Element );
			System.out.println("Actual result:" + Title );
			return false;
			
		}
	}
		
	//This method is used to lock onto an element (e.g. searchbox)
	public static void EnterSearch(WebDriver driver, WebElement SearchBox, String searchItem) {
			
			Actions actions = new Actions(driver);
			actions.moveToElement(SearchBox);
			actions.click();

			actions.sendKeys(searchItem);
			actions.build().perform();
			
			
		}
	
	//This element will get text from a webelement specified using its id
	public static boolean testGetText(WebDriver driver, String Text) throws InterruptedException {
		System.out.println("Inside testGetText");
		
		WebElement textGet = driver.findElement(By.id("toc"));
		String chapters = textGet.getText();
				

		Thread.sleep(1000);
		if (chapters.length() > 0) {
			System.out.println("Search successful - text printed below");
			System.out.println(chapters);
			return true;
		
		}
		
		else {
			System.out.println("Something went wrong when retrieving text");
			return false;
		}

		
		
	}
	
	//This method prints the overall result of the test
	public static void printResult(boolean test1, boolean test2, boolean test3, boolean test4, boolean test5) {
		
		boolean testResult = test1 & test2 & test3 & test4 & test5;
		
		if (testResult = true) {
			
			System.out.println("Overall Test Result Pass");

		}
		
		else {
			System.out.println("Overall Test Result Fail");
		
		
	}
	}


	
}