package com.pack.home.TestExecution;

import java.io.IOException;


import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;

import com.pack.home.Libraries.DriverLaunch;
import com.pack.home.Libraries.LibraryForGenericFunction;

public class TestRun {
	DriverLaunch Dlib = new DriverLaunch();
	public static WebDriver driver;	
	LibraryForGenericFunction lib = null;

	@BeforeTest
	public void executing() throws InterruptedException, IOException {
		driver = Dlib.openBrowser("chrome");
		lib = new LibraryForGenericFunction(driver);
		// Application link will be opened
		driver.get("http://marcus.oneeight.co.in/zeno/login");
	}

	@Test(priority = 1, description="Performs an unsuccessful login and checks the resulting error message (passes)")
	public void unsuccessfullogin() throws InterruptedException, IOException {
		lib.jsSendKeysForID("username", "ROL000008", "login", "ID");
		lib.jsSendKeysForID("password", "123456", "login", "ID");
		Assert.assertTrue(lib.waitAndClickForID("login", "login", "ID"));
		Thread.sleep(2000);
		Assert.assertTrue(lib.getText("error", "login", "ID").contains("login fail"));					
	}
	
	@Test(priority = 2, description="Performs an successful login and checks the dashboard url (passes)")
	public void login() throws InterruptedException, IOException {
		lib.jsSendKeysForID("username", "ROL000007", "login", "ID");
		lib.jsSendKeysForID("password", "123456", "login", "ID");
		Assert.assertTrue(lib.waitAndClickForID("login", "login", "ID"));
		Thread.sleep(2000);
		Assert.assertEquals(lib.getCurrentUrl(), "http://marcus.oneeight.co.in/zeno/dashboard");					
	}
	
	@Test(priority = 3, description="Checks for the welcome message (passes)")
	public void verifyWelcomeMesg() throws InterruptedException, IOException {
		Assert.assertEquals(lib.getText("welcomemesg", "dashboard", "class"), "Welcome VAMSI, to your One8 page");			
	}	
	
	@Test(priority = 4, description="Tries to navigate to Make Payment (fails)")
	public void clickPayment() throws InterruptedException, IOException {
		lib.waitAndClickForID("paymentlink", "dashboard", "linktext");
	}

	@Test(priority = 5, description="Navigates to Profile page (passes)")
	public void clickProfile() throws InterruptedException, IOException {
		lib.waitAndClickForID("profilelink", "dashboard", "linktext");
	}

	@Test(priority = 6, description="Logs out (passes)")
	public void logout() throws InterruptedException, IOException {
		lib.waitAndClickForID("logoutlink", "dashboard", "linktext");
	}
	
	@AfterTest()
	public void afterMethod() throws IOException {
		driver.quit();		
	}	
}
