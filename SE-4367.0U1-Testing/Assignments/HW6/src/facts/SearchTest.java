package facts;

import java.util.List;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

// SearchTest method
// This class contains the GUI test set for our second IDM

//Daniel Neal & Alex Lundin
//07-05-2018
//SE-4367-Testing

public class SearchTest implements StringConstants
{
	WebDriver driver;
	private static String expectedValue = null;
	@Before
	public void openFactsPage() //throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", chromeExecutable);
		driver = new ChromeDriver();
		driver.get(thisServlet);
		Assert.assertEquals("Software Engineering", driver.getTitle());
	}
	
	//this method automates the base case (D1,E1)
	//searches "automation" with the "text" option selected
	@Test
	public void searchBase() throws InterruptedException
	{
		WebElement searchBox = driver.findElement(By.id("searchText"));
		WebElement type = driver.findElement(By.id("text"));
		type.click();
		Thread.sleep(1000);
		searchBox.sendKeys("automation");
		Thread.sleep(1000);
		searchBox.submit();
		Thread.sleep(1000);
		expectedValue = "Test automation rarely is. Most testing activities cannot be automated.";
	}
	
	//automates test (D1,E2)
	//searches "automation" with the "author" option selected
	@Test
	public void searchD1E2() throws InterruptedException
	{
		WebElement searchBox = driver.findElement(By.id("searchText"));
		WebElement type = driver.findElement(By.id("author"));
		type.click();
		Thread.sleep(1000);
		searchBox.sendKeys("automation");
		Thread.sleep(1000);
		searchBox.submit();
		Thread.sleep(1000);
		expectedValue = "Not Found!";
	}
	
	//automates test (D1,E3)
	//searches "automation" with the "type" option selected
	@Test
	public void searchD1E3() throws InterruptedException
	{
		WebElement searchBox = driver.findElement(By.id("searchText"));
		WebElement type = driver.findElement(By.id("type"));
		type.click();
		Thread.sleep(1000);
		searchBox.sendKeys("automation");
		Thread.sleep(1000);
		searchBox.submit();
		Thread.sleep(1000);
		expectedValue = "Not Found!";
	}
	
	//automates test (D1,E4)
	//searches "automation" with the "all" option selected
	@Test
	public void searchD1E4() throws InterruptedException
	{
		WebElement searchBox = driver.findElement(By.id("searchText"));
		WebElement type = driver.findElement(By.id("all"));
		type.click();
		Thread.sleep(1000);
		searchBox.sendKeys("automation");
		Thread.sleep(1000);
		searchBox.submit();
		Thread.sleep(1000);
		expectedValue = "Test automation rarely is. Most testing activities cannot be automated.";
	}
	
	//automates the test (D2,E1)
	//searches "Glass" with the "text" option selected
	@Test
	public void searchD2E1() throws InterruptedException
	{
		WebElement searchBox = driver.findElement(By.id("searchText"));
		WebElement type = driver.findElement(By.id("text"));
		type.click();
		Thread.sleep(1000);
		searchBox.sendKeys("Glass");
		Thread.sleep(1000);
		searchBox.submit();
		Thread.sleep(1000);
		expectedValue = "Not Found!";
	}
	
	//automates the test (D3,E1)
	//searches "Fact" with the "text" option selected
	@Test
	public void searchD3E1() throws InterruptedException
	{
		WebElement searchBox = driver.findElement(By.id("searchText"));
		WebElement type = driver.findElement(By.id("text"));
		type.click();
		Thread.sleep(1000);
		searchBox.sendKeys("Fact");
		Thread.sleep(1000);
		searchBox.submit();
		Thread.sleep(1000);
		expectedValue = "Quality is NOT: user satisfaction, meeting requirements, achieving cost/schedule, or reliability.";
	}
	
	//automates the test (D4,E1)
	//searches "Fallacy" with the "text" option selected
	@Test
	public void searchD4E1() throws InterruptedException
	{
		WebElement searchBox = driver.findElement(By.id("searchText"));
		WebElement type = driver.findElement(By.id("text"));
		type.click();
		Thread.sleep(1000);
		searchBox.sendKeys("Fallacy");
		Thread.sleep(1000);
		searchBox.submit();
		Thread.sleep(1000);
		expectedValue = "Not Found!";
	}
	
	//automates the test (D5,E1)
	//searches "" with the "text" option selected
	//received error when I tried to send a null character
	@Test
	public void searchD5E1() throws InterruptedException
	{
		WebElement searchBox = driver.findElement(By.id("searchText"));
		WebElement type = driver.findElement(By.id("text"));
		type.click();
		Thread.sleep(1000);
		searchBox.sendKeys("");
		Thread.sleep(1000);
		searchBox.submit();
		Thread.sleep(1000);
		expectedValue = null;
	}
	
	@After
	public void closePage() throws InterruptedException
	{
		assertFixture();
		Thread.sleep(3000);
		driver.close();
		driver.quit();

	}
	
	
	// this method will pass or fail each test depending on if search String matching expected value
	public void assertFixture() throws InterruptedException{
		WebElement paragraph;
		WebElement dt ;
		
		// check for null first to avoid errors
		if (expectedValue == null){
			paragraph = findNotFoundInParagraphs();
			Assert.assertNull(paragraph);			
		}else if (expectedValue.equals("Not Found!")){
			paragraph = findNotFoundInParagraphs();
			System.out.println(paragraph.getText());
			Assert.assertEquals(paragraph.getText(), expectedValue);
			
		}else if(expectedValue != null){
			dt = findTextInDt();
			System.out.println(dt.getText());
			Assert.assertEquals(dt.getText(), expectedValue);
		}
		
		

	}
	

	private static List<WebElement> elements = null;
	// this method looks for the "Not Found!" string inside the body item
	// there are 3 known to appear, the middle p is the one that contains "Not Found!"
	// this method accesses it with get(1)
	public WebElement findNotFoundInParagraphs(){
		
		// top level element to look through
		// in this case it's the body
		elements = driver.findElements(By.cssSelector("body"));

	    for (WebElement element : elements) {
	    	// use the p selector, because the "Not Found!" text is printed into a paragraph
	        List<WebElement> paragraphs = element.findElements(By.cssSelector("p"));
	        
	        WebElement paragraph = paragraphs.get(1);
	        String myText = paragraph.getText();
            if (myText.contains("Not Found!")) {
	            return paragraph;
            }	        
	   
	        
	        
	    }
		return null;	
	    
	}
	
	// this method uses absolute path to find the first element of text in the Dt item
	public WebElement findTextInDt(){
		//html//body//table//tbody//tr//td//dl//dt
		
		WebElement dt = driver.findElement(By.xpath("//html//body//table//tbody//tr//td[1]//dl//dt[1]"));
		return dt;	
	    
	}
	
	
}
