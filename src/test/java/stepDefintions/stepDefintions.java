package stepDefintions;
import io.cucumber.java.After;
import org.junit.jupiter.api.Test;
import static java.awt.SystemColor.text;
import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class stepDefintions {
     WebDriver driver;
     WebDriverWait wait;
    @Given("I am on the page")
    public void iAmOnThePage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
         wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }
    @When("I fill in all required fields correctly")
    public void iFillInAllRequiredFieldsCorrectly() {

        driver.findElement(By.cssSelector("#member_firstname")).sendKeys("Test");
        driver.findElement(By.cssSelector("#member_lastname")).sendKeys("User");
        driver.findElement(By.cssSelector("#member_emailadress")).sendKeys("testuser1234@example.com");
        driver.findElement(By.cssSelector("#signup_password")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("#signup_confirmpassword")).sendKeys("Password123!");
    }
    @When("I fill in the fields without a last name")
    public void iFillInTheFieldsWithoutALastName() {
        driver.findElement(By.cssSelector("#member_firstname")).sendKeys("Test");
        driver.findElement(By.cssSelector("#member_emailadress")).sendKeys("testuser1234@example.com");
        driver.findElement(By.cssSelector("#signup_password")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("#signup_confirmpassword")).sendKeys("Password123!");
    }
    @When("I fill in the fields with mismatched passwords")
    public void iFillInTheFieldsWithMismatchedPasswords() {
        driver.findElement(By.cssSelector("#member_firstname")).sendKeys("Test");
        driver.findElement(By.cssSelector("#member_lastname")).sendKeys("User");
        driver.findElement(By.cssSelector("#member_emailadress")).sendKeys("testuser1234@example.com");
        driver.findElement(By.cssSelector("#signup_password")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("#signup_confirmpassword")).sendKeys("Wrong123!");
    }
    @And("I accept the terms and conditions")
    public void iAcceptTheTermsAndConditions() {
        driver.findElement(By.cssSelector("#consent-confirm")).click();
    }
    @And("I click  {string}")
    public void iClick(String button) {
        driver.findElement(By.cssSelector("input[value='" + button + "']")).click();
    }
    @Then("I should see the message {string}")
    public void iShouldSeeTheMessage(String message) {
        String body = driver.findElement(By.tagName("body")).getText().toLowerCase();
        assertTrue(body.contains(message.toLowerCase()), "Expected message not found: " + message);
        driver.quit();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    }