package com.johnsontraining.ngwebdriver_examples;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NgWebdriverExample {
	
	private WebDriver driver;
	private NgWebDriver ngDriver;
	private String baseUrl = "http://www.way2automation.com/angularjs-protractor/banking/#/login";
	
	@BeforeClass
	@Parameters({"browser"})
	public void setup(String browser) throws MalformedURLException {
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			ngDriver = new NgWebDriver((ChromeDriver) driver);
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			ngDriver = new NgWebDriver((FirefoxDriver) driver);
		} else {
			System.out.println("Using default browser as chrome");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			ngDriver = new NgWebDriver((ChromeDriver) driver);
		}

		driver.manage().window().maximize();
		driver.get(baseUrl);
		ngDriver.waitForAngularRequestsToFinish();
	}

	@Test
	public void loginAsCustomerTest() throws InterruptedException {
		
		driver.findElement(ByAngular.buttonText("Customer Login")).click();
		Thread.sleep(3000);
		
		Select select = new Select(driver.findElement(By.xpath("//select[@id='userSelect']")));
		select.selectByVisibleText("Albus Dumbledore");
		Thread.sleep(3000);
		
		driver.findElement(ByAngular.buttonText("Login")).click();
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
	}
}
