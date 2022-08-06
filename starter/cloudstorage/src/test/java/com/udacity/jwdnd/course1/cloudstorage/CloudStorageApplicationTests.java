package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		
		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	private void doLogOut(){
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait((driver), 2);
		WebElement logoutButton = driver.findElement(By.id("buttonLogout"));
		logoutButton.click();
		webDriverWait.until(ExpectedConditions.titleContains("Login"));

	}

	private void moveHome(){
		driver.get("http://localhost:" + this.port + "/home");
	}
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");
		
		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");
		
		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}






	private void moveNoteTab(){
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		WebElement notetab = driver.findElement(By.id("nav-notes-tab"));
		notetab.click();
		String visible = notetab.getAttribute("aria-selected");
		Assertions.assertEquals("true", visible);
	}

	private void createNote(String title, String description){
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		moveNoteTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-note")));
		WebElement addNote = driver.findElement(By.id("add-note"));
		addNote.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		WebElement noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.sendKeys(title);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.sendKeys(description);

		WebElement noteSubmit = driver.findElement(By.id("noteSubmitProxy"));
		noteSubmit.click();

		Assertions.assertTrue(driver.getPageSource().contains("Success"));
	}

	private void checkNoteInfo(String title, String description){
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		moveNoteTab();

		Assertions.assertTrue(driver.getPageSource().contains(title));
		Assertions.assertTrue(driver.getPageSource().contains(description));
	}

	private void editNoteInfo(String addTitle, String addDesc){
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		moveNoteTab();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("note-edit")));
		List<WebElement> list =  driver.findElements(By.name("note-edit"));

		Assertions.assertTrue(0 < list.size());

		WebElement element = list.get(0);
		element.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		WebElement noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.sendKeys(addTitle);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.sendKeys(addDesc);

		WebElement noteSubmit = driver.findElement(By.id("noteSubmitProxy"));
		noteSubmit.click();

		Assertions.assertTrue(driver.getPageSource().contains("Success"));
	}


	private void deleteNote(){
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		moveNoteTab();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Delete")));
		List<WebElement> list =  driver.findElements(By.linkText("Delete"));
		int size = list.size();

		Assertions.assertTrue(0 < list.size());

		WebElement element = list.get(0);
		element.click();

		moveNoteTab();

		list =  driver.findElements(By.name("Delete"));
		Assertions.assertEquals(size-1,list.size());

	}
	private void moveCredentialsTab(){
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
		WebElement notetab = driver.findElement(By.id("nav-credentials-tab"));
		notetab.click();
		String visible = notetab.getAttribute("aria-selected");
		Assertions.assertEquals("true", visible);
	}

	private void createCredentials(String url, String username, String password){
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		moveCredentialsTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addCredential")));
		WebElement addCredential = driver.findElement(By.id("addCredential"));
		addCredential.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		WebElement urlElem = driver.findElement(By.id("credential-url"));
		urlElem.sendKeys(url);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
		WebElement usernameElem = driver.findElement(By.id("credential-username"));
		usernameElem.sendKeys(username);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
		WebElement passElem = driver.findElement(By.id("credential-password"));
		passElem.sendKeys(password);

		WebElement submit = driver.findElement(By.id("cred-save"));
		submit.click();

		Assertions.assertTrue(driver.getPageSource().contains("Success"));
	}

	private void checkCredentialsInfo(String url, String username, String password){
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		moveCredentialsTab();

		Assertions.assertTrue(driver.getPageSource().contains(url));
		Assertions.assertTrue(driver.getPageSource().contains(username));
//		Assertions.assertTrue(driver.getPageSource().contains(password));
	}

	private void editCredentialsInfo(String url, String username, String password){
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		moveCredentialsTab();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cred-edit")));
		List<WebElement> list =  driver.findElements(By.name("cred-edit"));

		Assertions.assertTrue(0 < list.size());

		WebElement element = list.get(0);
		element.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		WebElement urlElem = driver.findElement(By.id("credential-url"));
		urlElem.sendKeys(url);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
		WebElement usernameElem = driver.findElement(By.id("credential-username"));
		usernameElem.sendKeys(username);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
		WebElement passElem = driver.findElement(By.id("credential-password"));
		passElem.sendKeys(password);

		WebElement submit = driver.findElement(By.id("cred-save"));
		submit.click();

		Assertions.assertTrue(driver.getPageSource().contains("Success"));
	}


	private void deleteCredentials(){
		driver.get("http://localhost:" + this.port + "/home");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		moveCredentialsTab();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Delete")));
		List<WebElement> list =  driver.findElements(By.linkText("Delete"));
		int size = list.size();

		Assertions.assertTrue(0 < list.size());

		WebElement element = list.get(0);
		element.click();

		moveNoteTab();

		list =  driver.findElements(By.name("Delete"));
		Assertions.assertEquals(size-1,list.size());

	}

	/*
		Write a Selenium test that logs in an existing user, creates a credential and verifies that the credential details are visible in the credential list.

		Write a Selenium test that logs in an existing user with existing credentials, clicks the edit credential button on an existing credential, changes the credential data, saves the changes, and verifies that the changes appear in the credential list.

		Write a Selenium test that logs in an existing user with existing credentials, clicks the delete credential button on an existing credential, and verifies that the credential no longer appears in the credential list.
     */
	@Test
	public void testAddEditDeleteCredential(){
		String url = "https://www.naver.com";
		String username = "theyoung";
		String password = "1234";
		String addStr = "a";

		// sign in
		doMockSignUp("Steven2", "na2", "stevenna2", "1");
		doLogIn("stevenna2", "1");

		//create credential
		createCredentials(url, username, password);

		//check credential
		checkCredentialsInfo(url, username, password);

		//edit credential
		editCredentialsInfo(url+addStr, username+addStr, password+addStr);

		//check credential
		checkCredentialsInfo(url+addStr, username+addStr, password+addStr);

		//delete credential
		deleteCredentials();
	}


	/*
		 Write a Selenium test that logs in an existing user, creates a note and verifies that the note details are visible in the note list.
		 Write a Selenium test that logs in an existing user with existing notes, clicks the edit note button on an existing note, changes the note data, saves the changes, and verifies that the changes appear in the note list.
		 Write a Selenium test that logs in an existing user with existing notes, clicks the delete note button on an existing note, and verifies that the note no longer appears in the note list.
		 */
	@Test
	public void testAddEditDeleteNote(){
		String title = "Note Test Title";
		String desc = "Note Test Description";
		String addTitle = "added Title";
		String addDesc = "added Desc";

		// sign in
		doMockSignUp("Steven1", "na1", "stevenna1", "1");
		doLogIn("stevenna1", "1");

		//create note
		createNote(title, desc);

		//check note
		checkNoteInfo(title, desc);

		//edit note
		editNoteInfo(addTitle, addDesc);

		//check note
		checkNoteInfo((title + addTitle).substring(0,20), desc + addDesc);

		//delete note
		deleteNote();
	}

	/**
	 * Write a Selenium test that verifies that the home page is not accessible without logging in.
	 *
	 * Write a Selenium test that signs up a new user, logs that user in, verifies that they can access the home page, then logs out and verifies that the home page is no longer accessible.
	 */
	@Test
	public void testSignupAndLoginFlow(){
		doMockSignUp("Steven", "na", "stevenna", "1");
		doLogIn("stevenna", "1");
		doLogOut();
		moveHome();
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

}
