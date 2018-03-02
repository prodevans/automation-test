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
		driver.get("http://52.172.33.72:8080/ServicePortal");
	}

	/*@Test(priority = 1, description="Performs an unsuccessful login and checks the resulting error message (passes)")
	public void unsuccessfullogin() throws InterruptedException, IOException {
		lib.jsSendKeysForID("uname", "anand", "login", "ID");
		lib.jsSendKeysForID("pass", "123456", "login", "ID");
		Assert.assertTrue(lib.waitAndClickForID("login", "login", "ID"));
		Thread.sleep(2000);
		Assert.assertTrue(lib.getText("error", "login", "ID").contains("login fail"));	
		
	}*/
	@Test(priority = 2, description="Performs an unsuccessful login and checks the resulting error message (passes)")
	public void trylogin() throws InterruptedException, IOException {
		Assert.assertTrue(lib.waitAndClickForID("login1", "login", "ID"));
		Thread.sleep(2000);
		Assert.assertTrue(lib.getText("error", "login", "ID").contains("login fail"));	
		
	}
	
	@Test(priority = 2, description="Performs an successful login and checks the dashboard url (passes)")
	public void login() throws InterruptedException, IOException {
		lib.jsSendKeysForID("username", "anand", "login", "ID");
		lib.jsSendKeysForID("password", "1234", "login", "ID");
		Assert.assertTrue(lib.waitAndClickForID("login", "login", "ID"));
		Thread.sleep(2000);
		Assert.assertEquals(lib.getCurrentUrl(), "\r\n" + 
				"http://52.172.33.72:8080/ServicePortal/success.jsp");					
	}
	
	@Test(priority = 3, description="Logs out (passes)")
	public void logout() throws InterruptedException, IOException {
		lib.waitAndClickForID("logoutlink", "dashboard", "linktext");
	}
	
	@AfterTest()
	public void afterMethod() throws IOException {
		driver.quit();		
	}	
}
