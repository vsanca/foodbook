package e2.isa.grupa5.integration.guest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import e2.isa.grupa5.model.users.Guest;
import e2.isa.grupa5.repository.guest.GuestRepository;

/**
 * 
 * @author Korisnik
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumRegisterLoginTest {

	@Autowired 
	GuestRepository guestRepository;
	
	@Test
	public void registerAndLoginFailTest() throws InterruptedException {

		// Create a new instance of the Firefox driver
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.
		WebDriver driver = new FirefoxDriver();

		driver.get("http://localhost:8080/#/guest/register");
		Thread.sleep(5000);

		enterTextContent(driver, "txtName", "Selenium Name");
		enterTextContent(driver, "txtSurname", "Selenium Surname");
		
		enterTextContent(driver, "txtAddress", "Selenium Address");
		enterTextContent(driver, "txtPassword", "SeleniumPassword");
		enterTextContent(driver, "txtPasswordConfirm", "SeleniumPassword");
		enterTextContent(driver, "txtEmail", "Selenium@Selenium.com");
		WebElement btnRegister = driver.findElement(By.id("btnRegister"));
		btnRegister.click();
		Thread.sleep(10000);
		
		// attempt to log in
		enterTextContent(driver, "txtEmail", "Selenium@Selenium.com");
		enterTextContent(driver, "txtPassword", "SeleniumPassword");
		WebElement btnLogin = driver.findElement(By.id("btnLogin"));
		btnLogin.click();
		// SHOULD STAY ON SAME PAGE
		Thread.sleep(5000);
		assert(driver.getCurrentUrl().contains("login"));

		// Close the browser
		driver.quit();
		
		// verify that Guest is stored in DB
		Guest guest = guestRepository.findByEmail("Selenium@Selenium.com");

		assertNotNull(guest);

	}

	private void enterTextContent(WebDriver driver, String id, String content) throws InterruptedException {
		WebElement txtElement = driver.findElement(By.id(id));
		txtElement.sendKeys(content);
		Thread.sleep(1000);
	}
}
