package stepDefintions;
import io.cucumber.java.After;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.Random;

public class stepDefintions {
    WebDriver driver;

    @Given("I am on the registration page")
    public void iAmOnThePage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @Given("I am on the registration page using {string}")
    public void iAmOnTheRegistrationPageUsing(String browser) {
        Object WebDriverManager;
        if (browser.equalsIgnoreCase("chrome"))
            driver = new ChromeDriver();
        else if  (browser.equalsIgnoreCase("firefox"))
            driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("I fill in all required member details fields correctly")
    public void iFillInAllRequiredFieldsCorrectly() {
        driver.findElement(By.cssSelector("#dp")).sendKeys("01/01/1990");
        driver.findElement(By.cssSelector("#member_firstname")).sendKeys("Test");
        driver.findElement(By.cssSelector("#member_lastname")).sendKeys("User");
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);
        driver.findElement(By.id("member_emailaddress")).sendKeys("testuser" + randomNumber + "@example.com");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("testuser" + randomNumber + "@example.com");
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("Password123!");
    }

    @When("I fill in the form with {}, {}, {}, {}, {}")
    public void iFillInTheFormWith(String arg0, String arg1, String arg2, String arg3, String arg4) {
        driver.findElement(By.cssSelector("#dp")).sendKeys(arg0);
        driver.findElement(By.cssSelector("#member_firstname")).sendKeys(arg1);
        driver.findElement(By.cssSelector("#member_lastname")).sendKeys(arg2);
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);
        driver.findElement(By.id("member_emailaddress")).sendKeys("testuser" + randomNumber + "@example.com");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("testuser" + randomNumber + "@example.com");
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(arg3);
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(arg4);
    }

    @When("I fill in all required member details fields correctly except last name")
    public void iFillInTheFieldsWithoutALastName() {
        driver.findElement(By.cssSelector("#dp")).sendKeys("01/01/1990");
        driver.findElement(By.cssSelector("#member_firstname")).sendKeys("Test");
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);
        driver.findElement(By.id("member_emailaddress")).sendKeys("testuser" + randomNumber + "@example.com");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("testuser" + randomNumber + "@example.com");
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("Password123!");
    }

    @When("I fill in all required member details fields with mismatched passwords")
    public void iFillInTheFieldsWithMismatchedPasswords() {
        driver.findElement(By.cssSelector("#dp")).sendKeys("01/01/1990");
        driver.findElement(By.cssSelector("#member_firstname")).sendKeys("Test");
        driver.findElement(By.cssSelector("#member_lastname")).sendKeys("User");
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);
        driver.findElement(By.id("member_emailaddress")).sendKeys("testuser" + randomNumber + "@example.com");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("testuser" + randomNumber + "@example.com");
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("Password456!");
    }

    @And("I accept the terms and conditions")
    public void iAcceptTheTermsAndConditions() {
        driver.findElement(By.cssSelector("label[for='sign_up_25'] span[class='box']\n")).click();
    }

    @And("I confirm I am aged over {int}")
    public void iConfirmIAmAgedOver(int arg0) {
        driver.findElement(By.cssSelector("label[for='sign_up_26'] span[class='box']\n")).click();
    }

    @And("I agree on the code of ethics")
    public void iAgreeOnTheCodeOfEthics() {
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] span[class='box']\n")).click();
    }

    @And("I click {string}")
    public void iClick(String button) {
        driver.findElement(By.cssSelector("input[value='" + button + "']")).click();
    }

    @Then("I should see the message \"THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND\"")
    public void verifySuccessMessage() {
        WebElement message = driver.findElement(By.cssSelector(".page-content-wrapper h2"));
        assertEquals("THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND", message.getText());
    }

    @Then("I should see the message \"Last Name is required\"")
    public void verifyLastNameRequiredMessage() {
        WebElement message = driver.findElement(By.cssSelector("span[for='member_lastname']"));
        assertEquals("Last Name is required", message.getText());
    }

    @Then("I should see the message \"Password did not match\"")
    public void iShouldSeeTheMessage() {
        WebElement message = driver.findElement(By.cssSelector("span[for='signupunlicenced_confirmpassword"));
        assertEquals("Password did not match", message.getText());
    }

    @Then("I should see the message \"You must confirm that you have read and accepted our Terms and Conditions\"")
    public void verifyTermsNotAcceptedMessage() {
        WebElement message = waitForElementToBeDisplayed("#signup_form span span");
        assertEquals("You must confirm that you have read and accepted our Terms and Conditions", message.getText().trim());

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebElement waitForElementToBeDisplayed(String css) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
    }
}