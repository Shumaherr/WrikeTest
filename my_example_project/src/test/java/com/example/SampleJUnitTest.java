package com.example;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.commons.lang3;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class SampleJUnitTest {
	public WebDriver driver;

	public static String generateString(int length) {

		String email = RandomStringUtils.randomAlphabetic(length);
		email += "@";
		email += RandomStringUtils.randomAlphabetic(5);
		email += "qw";
		email += ".ru";
		return email.toLowerCase();
	}

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void testGoogle() {
		try {
			System.out.println("Starting test " + new Object() {
			}.getClass().getEnclosingMethod().getName());
			driver.get("http://www.wrike.com/");
			driver.findElement(By.linkText("Войти")).click();
			driver.findElement(By.linkText("Создать аккаунт")).click();
			String email = generateString(7);
			driver.findElement(By.name("email")).sendKeys(email);
			driver.findElement(By.name("email")).sendKeys(Keys.ENTER);
			WebElement element = driver.findElement(By.linkText(email));
			String emailString = element.getText();
			assertEquals(email, emailString);
			driver.findElement(By.id("resendEmail")).click();
			driver.findElement(By.linkText("Тарифные планы")).click();
			driver.findElement(By.xpath("(//a[@id='start-free-trial-professional'])[2]")).click();
			driver.switchTo().frame("ajaxIframeFeatures");
			email = generateString(7);
			driver.findElement(By.name("email")).sendKeys(email);
			driver.findElement(By.name("email")).sendKeys(Keys.ENTER);
			element = driver.findElement(By.linkText(email));
			emailString = element.getText();
			assertEquals(email, emailString);
			driver.switchTo().defaultContent();
			driver.findElement(By.id("resendEmail")).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(16000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Ending test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
	}

	@After
	public void tearDown() {
		driver.close();
	}
}
